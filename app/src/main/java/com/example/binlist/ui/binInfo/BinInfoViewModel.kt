package com.example.binlist.ui.binInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.domain.api.BinInfoInteractor
import com.example.binlist.domain.models.BinInfoItem
import com.example.binlist.ui.BinInfoState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BinInfoViewModel(private val interactor: BinInfoInteractor) : ViewModel() {

    private val stateLiveData = MutableLiveData<BinInfoState>()

    fun observeState(): LiveData<BinInfoState> = stateLiveData

    private var searchJob: Job? = null


    private fun processResult(findBin: BinInfoItem?, errorMessage: String?) {
        when {
            findBin != null -> stateLiveData.postValue(BinInfoState.Content(findBin))
            errorMessage != null -> stateLiveData.postValue(BinInfoState.Error(errorMessage))
        }
    }

    fun searchDebounce(binNumber: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(2000L)
            searchBinInfoByBin(binNumber)
        }
    }

    fun searchBinInfoByBin(binNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.searchBinInfoByBin(binNumber).collect {
                processResult(it.first, it.second)
            }
        }
    }

}