package com.example.binlist.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface BinInfoApiService {

    @GET("/{bin}")
    suspend fun getBinInfoByBin(@Path("bin") bin: String): BinInfoResponse

}