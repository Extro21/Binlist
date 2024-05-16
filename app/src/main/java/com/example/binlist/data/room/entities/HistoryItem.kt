package com.example.binlist.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "history_item")
data class HistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("bankName")
    val bankName: String?,
    @ColumnInfo("bankUrl")
    val bankUrl: String?,
    @ColumnInfo("bankPhone")
    val bankPhone: String?,
    @ColumnInfo("countryName")
    val countryName: String?,
    @ColumnInfo("latitude")
    val latitude: Int?,
    @ColumnInfo("longitude")
    val longitude: Int?,
    @ColumnInfo("scheme")
    val scheme: String?,
)