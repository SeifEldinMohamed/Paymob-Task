package com.example.paymobtaskmoviesapp.data.data_sources.local.room.type_converter

import androidx.room.TypeConverter
import com.example.paymobtaskmoviesapp.data.data_sources.remote.retrofit.datamodel.movie_details.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoriesListTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun fromCategoriesListToJson(categories: List<Genre>):String {
        return gson.toJson(categories)
    }

    @TypeConverter
    fun fromJsonToCategoriesList(categoryString:String):List<Genre> {
         val listType = object : TypeToken<Genre>(){}.type // replace FoodRecipe::class.java
        return gson.fromJson(categoryString, listType)
    }
}