package com.example.informed.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.informed.API.NewsApi
import com.example.informed.Model.Article

class NewsPagingSourceByDefault(private val newsApi: NewsApi):PagingSource<Int,Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {


        return state.anchorPosition?.let {

            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)

        }



    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        try {

            val position  = params.key ?:1
            val response = newsApi.getBreakingNews(pagenumber = position)

            return LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = if (position==1) null else position-1,
                nextKey = if (position== 500) null else position+1

            )

        }catch (e:Exception){


            return LoadResult.Error(e)


        }

    }


}