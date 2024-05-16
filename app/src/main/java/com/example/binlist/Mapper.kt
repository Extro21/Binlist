package com.example.binlist

import com.example.binlist.data.room.entities.HistoryItem
import com.example.binlist.domain.models.BinInfoItem

class Mapper {
    fun mapBinModuleToBinItem(binItem: BinInfoItem): HistoryItem {
        return HistoryItem(
            id = 0,
            bankName = binItem.bankName,
            bankUrl = binItem.bankUrl,
            bankPhone = binItem.bankPhone,
            countryName = binItem.countryName,
            scheme = binItem.scheme,
            longitude = binItem.longitude,
            latitude = binItem.latitude,
        )
    }

    fun mapBinItemToBinModule(binItem: HistoryItem): BinInfoItem {
        return BinInfoItem(
            id = binItem.id,
            bankName = binItem.bankName,
            bankUrl = binItem.bankUrl,
            bankPhone = binItem.bankPhone,
            countryName = binItem.countryName,
            scheme = binItem.scheme,
            longitude = binItem.longitude,
            latitude = binItem.latitude,
        )
    }
}

