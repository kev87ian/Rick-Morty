package com.kev.ricknmorty.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.kev.ricknmorty.api.ApiService
import com.kev.ricknmorty.paging.CharactersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val apiService: ApiService): ViewModel(), LifecycleObserver{

    val listData = Pager(PagingConfig(pageSize = 1)){
        CharactersPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}