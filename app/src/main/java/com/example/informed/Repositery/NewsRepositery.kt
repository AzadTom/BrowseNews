package com.example.informed.Repositery

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.informed.API.NewsApi
import com.example.informed.Paging.NewsPagingSourceByDefault
import com.example.informed.Paging.NewsPagingSourceSearchForNews
import javax.inject.Inject

class NewsRepositery @Inject constructor( private val newsApi: NewsApi) {


    fun getBreakingNews() = Pager(
        config = PagingConfig(10 , maxSize = 50),
        pagingSourceFactory = { NewsPagingSourceByDefault(newsApi) }

    ).liveData


    fun searchForNews(query:String) = Pager(
        config = PagingConfig(20,100),
        pagingSourceFactory = {NewsPagingSourceSearchForNews(newsApi,query)}

    ).liveData









}