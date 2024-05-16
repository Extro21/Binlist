package com.example.binlist.data.network

import com.example.binlist.data.Response

data class BinInfoResponse(
    val bank: Bank?,
    val brand: String?,
    val country: Country?,
    val scheme: String?,
) : Response()

data class Bank(
    val name: String?,
    val phone: String?,
    val url: String?,
)

data class Country(
    val alpha2: String?,
    val currency: String?,
    val emoji: String?,
    val latitude: Int?,
    val longitude: Int?,
    val name: String?,
    val numeric: String?,
)