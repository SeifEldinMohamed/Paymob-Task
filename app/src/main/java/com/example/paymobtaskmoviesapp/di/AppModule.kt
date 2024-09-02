package com.example.paymobtaskmoviesapp.di

import com.example.paymobtaskmoviesapp.presentation.utils.DispatcherProvider
import com.example.paymobtaskmoviesapp.presentation.utils.StandardDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return StandardDispatcherProvider()
    }

}