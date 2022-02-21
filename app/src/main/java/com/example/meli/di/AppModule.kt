package com.example.meli.di

import android.content.Context
import com.example.meli.data.local.AppDatabase
import com.example.meli.data.local.ArticleDao
import com.example.meli.data.remote.ArticleRemoteDataSource
import com.example.meli.data.remote.ArticleService
import com.example.meli.data.repository.ArticleRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()
        return retrofit
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): ArticleService = retrofit.create(ArticleService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(articleService: ArticleService) = ArticleRemoteDataSource(articleService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.articleDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ArticleRemoteDataSource,
                          localDataSource: ArticleDao) =
        ArticleRepository(remoteDataSource, localDataSource)
}