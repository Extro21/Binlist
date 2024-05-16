package com.example.binlist.di

import com.example.binlist.domain.api.BinInfoInteractor
import com.example.binlist.domain.impl.BinInfoInteractorImpl
import org.koin.dsl.module

val domainModule = module {

    factory<BinInfoInteractor> {
        BinInfoInteractorImpl(get())
    }

}