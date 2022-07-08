package com.kev.rickmorty.network

import android.telecom.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("character")
    fun getCharacters() : Call
}