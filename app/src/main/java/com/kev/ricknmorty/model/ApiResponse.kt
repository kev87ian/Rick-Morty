package com.kev.ricknmorty.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("results")
    val results: List<Result>
)