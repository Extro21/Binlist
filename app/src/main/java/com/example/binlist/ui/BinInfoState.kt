package com.example.binlist.ui

import com.example.binlist.domain.models.BinInfoItem

sealed interface BinInfoState {

    data class Content(
        val binInfo: BinInfoItem
    ) : BinInfoState

    data class Error(
        val message: String
    ) : BinInfoState

}