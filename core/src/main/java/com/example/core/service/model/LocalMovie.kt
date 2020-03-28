package com.example.core.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movies")
data class LocalMovie(
    @ColumnInfo(name = "id") @PrimaryKey @NotNull val id: Long = 0L,
    @ColumnInfo(name = "title") val title: String? ,
    @ColumnInfo(name = "overview") val overview: String? ,
    @ColumnInfo(name = "posterPath") val posterPath: String?,
    @ColumnInfo(name = "backdropPath") val backdropPath: String? ,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?,
    @ColumnInfo(name = "voteAverage") val voteAverage: Float,
    val isBookmarked: Boolean = false


)