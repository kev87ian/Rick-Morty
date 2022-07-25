package com.kev.ricknmorty.api

import com.kev.ricknmorty.model.ApiResponse
import com.kev.ricknmorty.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.END_POINT)
   suspend fun getAllCharacters(
        @Query("page") page:Int
   ) : Response<ApiResponse>
}