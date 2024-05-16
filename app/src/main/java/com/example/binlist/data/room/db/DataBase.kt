package com.example.binlist.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.binlist.data.room.entities.HistoryItem

@Database(entities = [HistoryItem::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun historyDao(): Dao

}