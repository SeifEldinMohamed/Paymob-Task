package com.example.paymobtaskmoviesapp.data.data_sources.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.Genre
import com.example.paymobtaskmoviesapp.presentation.utils.Constants.Companion.MOVIE_FAVOURITE_ENTITY

@Entity(tableName = MOVIE_FAVOURITE_ENTITY)
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val details: String,
    val image: String,
    val categories: List<Genre>,
    val rating: Double,
    val releaseDate: String,
    val isFavourite: Boolean
)
