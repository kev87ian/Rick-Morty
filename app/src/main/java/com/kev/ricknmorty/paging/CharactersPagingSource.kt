package com.kev.ricknmorty.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kev.ricknmorty.api.ApiService
import com.kev.ricknmorty.model.Result

class CharactersPagingSource (private val apiService: ApiService) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {

            val currentPage = params.key?:1
            val response = apiService.getAllCharacters(currentPage)
            val data = response.body()?.results?: emptyList()
            val responseData = mutableListOf<Result>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}