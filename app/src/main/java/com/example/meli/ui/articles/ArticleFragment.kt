package com.example.meli.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.meli.R
import com.example.meli.databinding.ArticleFragmentBinding
import com.example.meli.utils.Resource
import com.example.meli.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment(), CharactersAdapter.CharacterItemListener, SearchView.OnQueryTextListener {

    private var binding: ArticleFragmentBinding by autoCleared()
    private val viewModel: ArticleViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArticleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CharactersAdapter(this)
        binding.charactersRv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.charactersRv.adapter = adapter
    }

    private fun setupSearchView() {
        with(binding) {
            searchView.setOnQueryTextListener(this@ArticleFragment)
            searchView.isFocusable = false
            searchView.isIconified = false
            searchView.clearFocus()
        }
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(characterId: String) {
        findNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundleOf("id" to characterId)
        )
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        onTextSearched(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        onTextSearched(newText)
        return true
    }

    private fun onTextSearched(text: String?) {
        if (text != null)
            viewModel.searchGif(text)
    }

}
