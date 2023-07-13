package com.example.nicehashtest.di

import android.content.Context
import com.example.nicehashtest.api.AccumulatorRepository
import com.example.nicehashtest.api.ReadFileRepository
import com.example.nicehashtest.data.repository.AccumulatorRepositoryImpl
import com.example.nicehashtest.data.repository.ReadFileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideReadFileRepository(
        @ApplicationContext context: Context,
    ): ReadFileRepository = ReadFileRepositoryImpl(context)

    @Provides
    fun provideAccumulatorRepository(): AccumulatorRepository = AccumulatorRepositoryImpl()
}
