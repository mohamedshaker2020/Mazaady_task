package com.coding.androidtask.data.sources

import com.coding.androidtask.data.sources.remote.RemoteDataSource
import com.coding.androidtask.data.sources.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DI {
    @Provides
    fun provideRemoteDS(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource =
        remoteDataSourceImpl
}