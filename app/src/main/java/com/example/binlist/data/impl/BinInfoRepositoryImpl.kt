package com.example.binlist.data.impl

import android.content.Context
import com.example.binlist.Mapper
import com.example.binlist.R
import com.example.binlist.data.network.BinInfoRequest
import com.example.binlist.data.network.BinInfoResponse
import com.example.binlist.data.network.NetworkClient
import com.example.binlist.data.room.db.DataBase
import com.example.binlist.data.room.entities.HistoryItem
import com.example.binlist.domain.api.BinInfoRepository
import com.example.binlist.domain.models.BinInfoItem
import com.example.binlist.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BinInfoRepositoryImpl(
    private val networkClient: NetworkClient,
    private val context: Context,
    private val dataBase: DataBase,
    private val map: Mapper
) : BinInfoRepository {

    override fun searchByBin(binNumber: String): Flow<Resource<BinInfoItem>> = flow {
        val response = networkClient.doRequest(BinInfoRequest(binNumber))
        when (response.resultCode) {
            -1 -> emit(Resource.Error(context.getString(R.string.nothing_internet)))
            200 -> {
                with(response as BinInfoResponse) {
                    val data = BinInfoItem(
                        id = 0,
                        bankName = bank?.name,
                        bankPhone = bank?.phone,
                        bankUrl = bank?.url,
                        countryName = country?.name,
                        latitude = country?.latitude,
                        longitude = country?.longitude,
                        scheme = scheme,
                    )
                    if (checkIfAllNullOrEmpty(data)) {
                        emit(Resource.Error(context.getString(R.string.nothing_found)))
                    } else {
                        dataBase.historyDao().insertBinFavorite(map.mapBinModuleToBinItem(data))
                        emit(Resource.Success(data = data))
                    }
                }
            }

            else -> emit(Resource.Error(context.getString(R.string.requests_are_throttled)))
        }
    }

    override suspend fun getHistoryItems(): Flow<List<BinInfoItem>> =
        dataBase.historyDao().getBinHistory().map {
            mapBinItemsToBinModules(it)
        }

    private fun mapBinItemsToBinModules(item: List<HistoryItem>): List<BinInfoItem> {
        return item.map { itemMap -> map.mapBinItemToBinModule(itemMap) }
    }


    private fun checkIfAllNullOrEmpty(binInfoModule: BinInfoItem): Boolean {
        return binInfoModule.bankName.isNullOrEmpty() && binInfoModule.bankUrl.isNullOrEmpty() &&
                binInfoModule.bankPhone.isNullOrEmpty() && binInfoModule.countryName.isNullOrEmpty() &&
                binInfoModule.latitude == null && binInfoModule.longitude == null && binInfoModule.scheme.isNullOrEmpty()
    }

}