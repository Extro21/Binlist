package com.example.binlist.domain.api

import com.example.binlist.domain.models.BinInfoItem
import kotlinx.coroutines.flow.Flow

interface BinInfoInteractor {

    fun searchBinInfoByBin(binNumber: String): Flow<Pair<BinInfoItem?, String?>>

    suspend fun getHistoryItems(): Flow<List<BinInfoItem>>

}