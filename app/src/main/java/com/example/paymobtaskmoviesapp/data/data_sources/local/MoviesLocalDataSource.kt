package com.example.paymobtaskmoviesapp.data.data_sources.local

import android.util.Log
import com.example.paymobtaskmoviesapp.data.data_sources.local.room.FavouriteDao
import com.example.paymobtaskmoviesapp.data.data_sources.local.room.entities.MovieDetailsEntity
import com.example.paymobtaskmoviesapp.data.mapper.toCustomDatabaseExceptionDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val favouriteDao: FavouriteDao
) {
    suspend fun insertFavouriteMovie(movieDetailsEntity: MovieDetailsEntity) {
        try {
            favouriteDao.insertFavouriteMovie(movieDetailsEntity)
        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }

    suspend fun deleteFavouriteMovie(movieDetailsEntity: MovieDetailsEntity) {
        try {
            favouriteDao.deleteFavouriteMovie(movieDetailsEntity)
        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }

    fun isFavouriteMovie(movieId: Int): Flow<Boolean> {
        return try {
            favouriteDao.isFavouriteMovie(movieId)
        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }
}