package com.example.binlist.data.network

import com.example.binlist.data.Response

interface NetworkClient {

    suspend fun doRequest(dto: Any): Response

}