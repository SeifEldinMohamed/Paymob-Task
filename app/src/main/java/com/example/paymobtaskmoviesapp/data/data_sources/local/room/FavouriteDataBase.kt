package com.example.paymobtaskmoviesapp.data.data_sources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.paymobtaskmoviesapp.data.data_sources.local.room.entities.MovieDetailsEntity
import com.example.paymobtaskmoviesapp.data.data_sources.local.room.type_converter.CategoriesListTypeConverter

@Database(
    entities = [MovieDetailsEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(CategoriesListTypeConverter::class)
abstract class FavouriteDataBase: RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
}