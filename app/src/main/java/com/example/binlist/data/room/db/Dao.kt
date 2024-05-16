package com.example.binlist.data.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.binlist.data.room.entities.HistoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBinFavorite(bin: HistoryItem)

    @Query("SELECT * FROM history_item")
    fun getBinHistory(): Flow<List<HistoryItem>>

}