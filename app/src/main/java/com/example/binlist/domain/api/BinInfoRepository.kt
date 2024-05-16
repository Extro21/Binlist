package com.example.binlist.domain.api

import com.example.binlist.domain.models.BinInfoItem
import com.example.binlist.util.Resource
import kotlinx.coroutines.flow.Flow

interface BinInfoRepository {

    fun searchByBin(binNumber: String): Flow<Resource<BinInfoItem>>

    suspend fun getHistoryItems(): Flow<List<BinInfoItem>>
}