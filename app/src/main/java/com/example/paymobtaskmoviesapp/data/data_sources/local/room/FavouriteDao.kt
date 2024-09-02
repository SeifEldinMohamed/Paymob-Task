package com.example.paymobtaskmoviesapp.data.data_sources.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paymobtaskmoviesapp.data.data_sources.local.room.entities.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteMovie(movieDetailsEntity: MovieDetailsEntity)

    @Query("SELECT isFavourite FROM MOVIE_FAVOURITE_ENTITY WHERE id = :movieId")
    fun isFavouriteMovie(movieId: Int): Flow<Boolean>

    @Delete
    suspend fun deleteFavouriteMovie(movieDetailsEntity: MovieDetailsEntity)

}