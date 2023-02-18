package com.example.informed.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.informed.Repositery.NewsRepositery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewmodel @Inject constructor(private val newsRepositery: NewsRepositery) :ViewModel(){

    val breakingNewsList = newsRepositery.getBreakingNews().cachedIn(viewModelScope)

    fun searchForNews(query:String) =  newsRepositery.searchForNews(query).cachedIn(viewModelScope)



}