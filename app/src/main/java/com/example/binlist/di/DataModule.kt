package com.example.binlist.di

import androidx.room.Room
import com.example.binlist.Mapper
import com.example.binlist.data.impl.BinInfoRepositoryImpl
import com.example.binlist.data.network.NetworkClient
import com.example.binlist.data.network.RetrofitNetworkClient
import com.example.binlist.data.room.db.DataBase
import com.example.binlist.domain.api.BinInfoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        Mapper()
    }

    single<BinInfoRepository> {
        BinInfoRepositoryImpl(get(), get(), get(), get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get())
    }

}

val baseModule = module {
    single {
        Room.databaseBuilder(androidContext(), DataBase::class.java, "favoriteDataBase.db").build()
    }
}