package com.example.meli.ui.articledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.meli.R
import com.example.meli.data.entities.DataItem
import com.example.meli.databinding.ArticleDetailFragmentBinding
import com.example.meli.utils.Resource
import com.example.meli.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailFragment : Fragment() {

    private var binding: ArticleDetailFragmentBinding by autoCleared()
    private val viewModel: ArticleDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArticleDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let { viewModel.start(it) }
        subscribeUi(binding)
    }

    private fun subscribeUi(binding: ArticleDetailFragmentBinding) {
        viewModel.character.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.infoItem.visibility = View.VISIBLE
                    result.data?.let { bindCharacter(binding, it) }
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.infoItem.visibility = View.GONE
                }
                Resource.Status.ERROR ->
                    Toast.makeText(activity, result.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindCharacter(binding: ArticleDetailFragmentBinding,dataItem: DataItem ) {
        dataItem.apply {
        binding.idtitle.text = title
        binding.idprice.text = "$"+price
        binding.idCondition.text = condition
        binding.idSale.text = soldQuantity
            try {
                Glide.with(binding.root)
                    .load(pictures[1].url)
                    .error(R.drawable.imagenotfound)
                    .fallback(R.drawable.imagenotfound)
                    .into(binding.idImage)
            }catch(e: Exception) {}
        }
    }
}
