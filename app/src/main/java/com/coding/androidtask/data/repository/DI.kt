package com.coding.androidtask.data.repository

import com.coding.androidtask.domain.repositories.CategoriesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DI {
    @Provides
    fun provideNewsRepo(categoriesRepoImpl: CategoriesRepoImpl): CategoriesRepo = categoriesRepoImpl
}