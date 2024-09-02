package com.example.paymobtaskmoviesapp.domain.model

sealed class CustomApiExceptionDomainModel : Exception() {
    object NoInternetConnectionExceptionDomainModel : CustomApiExceptionDomainModel()
    object TimeoutExceptionDomainModel : CustomApiExceptionDomainModel()
    object NetworkExceptionDomainModel : CustomApiExceptionDomainModel()
    object ServiceNotFoundExceptionDomainModel : CustomApiExceptionDomainModel()
    object AccessDeniedExceptionDomainModel : CustomApiExceptionDomainModel()
    object ServiceUnavailableExceptionDomainModel : CustomApiExceptionDomainModel()
    object UnknownExceptionDomainModel : CustomApiExceptionDomainModel()

}