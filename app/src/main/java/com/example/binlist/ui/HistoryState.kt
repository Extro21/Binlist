package com.example.binlist.ui

import com.example.binlist.domain.models.BinInfoItem

sealed interface HistoryState {

    data object Empty : HistoryState

    data class Content(
        val items: List<BinInfoItem>
    ) : HistoryState

}