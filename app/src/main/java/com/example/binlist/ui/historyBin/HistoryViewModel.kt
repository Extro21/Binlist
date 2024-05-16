package com.example.binlist.ui.historyBin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.domain.api.BinInfoInteractor
import com.example.binlist.domain.models.BinInfoItem
import com.example.binlist.ui.HistoryState
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: BinInfoInteractor) : ViewModel() {

    private val historyLivaData = MutableLiveData<HistoryState>()
    fun observeState(): LiveData<HistoryState> = historyLivaData

    fun getHistory() {
        viewModelScope.launch {
            interactor.getHistoryItems().collect { items ->
                processResult(items.reversed())
            }
        }
    }

    private fun processResult(bins: List<BinInfoItem>) {
        if (bins.isEmpty()) {
            historyLivaData.postValue(HistoryState.Empty)
        } else {
            historyLivaData.postValue(HistoryState.Content(bins))
        }
    }


}