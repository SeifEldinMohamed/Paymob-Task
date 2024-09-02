package com.example.paymobtaskmoviesapp.presentation.mapper

import com.example.paymobtaskmoviesapp.domain.model.CustomDatabaseExceptionDomainModel
import com.example.paymobtaskmoviesapp.presentation.model.CustomDatabaseExceptionUiModel

fun CustomDatabaseExceptionDomainModel.toCustomDatabaseExceptionUiModel(): CustomDatabaseExceptionUiModel {
    return when (this) {
        is CustomDatabaseExceptionDomainModel.DatabaseConstraintExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseCorruptExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseDiskIOExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseFullExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseAccessPermExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseReadOnlyExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseDatatypeMismatchExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseMisuseExceptionDomainModel,
        CustomDatabaseExceptionDomainModel.DatabaseOperationExceptionDomainModel -> CustomDatabaseExceptionUiModel.DatabaseError
        
        is CustomDatabaseExceptionDomainModel.UnknownExceptionDomainModel -> CustomDatabaseExceptionUiModel.Unknown
    }
}