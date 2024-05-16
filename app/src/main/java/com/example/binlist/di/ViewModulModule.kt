package com.example.binlist.di

import com.example.binlist.ui.binInfo.BinInfoViewModel
import com.example.binlist.ui.historyBin.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {

    viewModel {
        BinInfoViewModel(get())
    }

    viewModel {
        HistoryViewModel(get())
    }

}