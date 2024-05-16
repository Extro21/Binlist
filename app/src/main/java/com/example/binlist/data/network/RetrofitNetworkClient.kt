package com.example.binlist.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.binlist.data.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient(private val context: Context) : NetworkClient {

    private val binInfoUrl = "https://lookup.binlist.net"

    private val retrofitBinInfo =
        Retrofit.Builder().baseUrl(binInfoUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    private val binInfoService = retrofitBinInfo.create(BinInfoApiService::class.java)

    override suspend fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }

        return try {
            when (dto) {
                is BinInfoRequest -> {
                    val resp = binInfoService.getBinInfoByBin(dto.bin)
                    resp.apply { resultCode = 200 }
                }

                else -> {
                    Response().apply { resultCode = 500 }
                }
            }

        } catch (e: Exception) {
            Response().apply { resultCode = 500 }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}