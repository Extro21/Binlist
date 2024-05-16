package com.example.binlist.domain.models

data class BinInfoItem(
    val id: Int,
    val bankName: String?,
    val bankUrl: String?,
    val bankPhone: String?,
    val countryName: String?,
    val latitude: Int?,
    val longitude: Int?,
    val scheme: String?,
)

