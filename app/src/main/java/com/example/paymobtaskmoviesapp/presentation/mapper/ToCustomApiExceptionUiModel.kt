package com.example.paymobtaskmoviesapp.presentation.mapper

import com.example.paymobtaskmoviesapp.domain.model.CustomApiExceptionDomainModel
import com.example.paymobtaskmoviesapp.presentation.model.CustomApiExceptionUiModel

fun CustomApiExceptionDomainModel.toCustomApiExceptionUiModel(): CustomApiExceptionUiModel {
    return when (this) {
        is CustomApiExceptionDomainModel.NoInternetConnectionExceptionDomainModel -> CustomApiExceptionUiModel.NoInternetConnection
        is CustomApiExceptionDomainModel.TimeoutExceptionDomainModel -> CustomApiExceptionUiModel.Timeout
        is CustomApiExceptionDomainModel.NetworkExceptionDomainModel -> CustomApiExceptionUiModel.Network
        is CustomApiExceptionDomainModel.ServiceNotFoundExceptionDomainModel, CustomApiExceptionDomainModel.AccessDeniedExceptionDomainModel, CustomApiExceptionDomainModel.ServiceUnavailableExceptionDomainModel ->
            CustomApiExceptionUiModel.ServiceUnreachable

        else ->{
            CustomApiExceptionUiModel.Unknown
        }

    }
}