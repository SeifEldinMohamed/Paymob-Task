package com.example.paymobtaskmoviesapp.presentation.model

sealed class CustomDatabaseExceptionUiModel {
    object DatabaseError : CustomDatabaseExceptionUiModel()
    object Unknown : CustomDatabaseExceptionUiModel()

}