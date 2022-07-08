package com.kev.rickmorty.network

import com.kev.rickmorty.data.model.CharacterList
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("character")
    fun getCharacters(): Call<CharacterList>
}