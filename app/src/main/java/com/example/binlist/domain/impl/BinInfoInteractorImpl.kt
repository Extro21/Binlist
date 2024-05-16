package com.example.binlist.domain.impl

import com.example.binlist.domain.api.BinInfoInteractor
import com.example.binlist.domain.api.BinInfoRepository
import com.example.binlist.domain.models.BinInfoItem
import com.example.binlist.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BinInfoInteractorImpl(private val repository: BinInfoRepository) : BinInfoInteractor {

    override fun searchBinInfoByBin(binNumber: String): Flow<Pair<BinInfoItem?, String?>> {
        return repository.searchByBin(binNumber).map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.message)
            }
        }
    }

    override suspend fun getHistoryItems(): Flow<List<BinInfoItem>> {
        return repository.getHistoryItems()
    }
}