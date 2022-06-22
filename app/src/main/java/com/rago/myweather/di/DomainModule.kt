package com.rago.myweather.di

import com.rago.domain.repositories.RemoteRepo
import com.rago.domain.usecases.GetMyWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetMyWeatherUseCase(
        repo: RemoteRepo
    ): GetMyWeatherUseCase = GetMyWeatherUseCase(repo)
}