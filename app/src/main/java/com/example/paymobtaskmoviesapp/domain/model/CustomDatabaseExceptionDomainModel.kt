package com.example.paymobtaskmoviesapp.domain.model

sealed class CustomDatabaseExceptionDomainModel: Exception() {
    // Room-related exceptions
    object DatabaseConstraintExceptionDomainModel : CustomDatabaseExceptionDomainModel()
    object DatabaseCorruptExceptionDomainModel : CustomDatabaseExceptionDomainModel()
    object DatabaseDiskIOExceptionDomainModel : CustomDatabaseExceptionDomainModel()
    object DatabaseFullExceptionDomainModel : CustomDatabaseExceptionDomainModel()
    object DatabaseAccessPermExceptionDomainModel : CustomDatabaseExceptionDomainModel()
    object DatabaseReadOnlyExceptionDomainModel : CustomDatabaseExceptionDomainModel()
    object DatabaseDatatypeMismatchExceptionDomainModel : CustomDatabaseExceptionDomainModel()
    object DatabaseMisuseExceptionDomainModel : CustomDatabaseExceptionDomainModel()
    object DatabaseOperationExceptionDomainModel : CustomDatabaseExceptionDomainModel()
    object UnknownExceptionDomainModel : CustomDatabaseExceptionDomainModel()

}