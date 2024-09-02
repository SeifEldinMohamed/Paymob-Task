package com.example.paymobtaskmoviesapp.presentation.model

sealed class CustomApiExceptionUiModel {

    object NoInternetConnection : CustomApiExceptionUiModel()

    object Network : CustomApiExceptionUiModel()

    object Timeout : CustomApiExceptionUiModel()

    object ServiceUnreachable : CustomApiExceptionUiModel()

    object Unknown : CustomApiExceptionUiModel()


}