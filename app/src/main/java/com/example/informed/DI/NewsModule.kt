package com.example.informed.DI

import com.example.informed.API.NewsApi
import com.example.informed.Utility.Constant.BASE_URL
import com.example.informed.Repositery.NewsRepositery
import com.example.informed.ViewModel.NewsViewmodel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NewsModule {


    @Singleton
    @Provides
    fun providesRetrofitInstance() :Retrofit{

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    }


    @Singleton
    @Provides
    fun  providesNewsApi(retrofit: Retrofit):NewsApi{

        return retrofit.create(NewsApi::class.java)


    }


    @Singleton
    @Provides
    fun providesNewsRepositeryInstace(newsApi: NewsApi): NewsRepositery {

        return  NewsRepositery(newsApi)

    }


    @Singleton
    @Provides
    fun providesNewsViewmodalInstance(newsRepositery: NewsRepositery):NewsViewmodel{

        return NewsViewmodel(newsRepositery)

    }



}