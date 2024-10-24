package com.coding.androidtask.data.repository

import com.coding.androidtask.data.models.categories_response.CategoriesResponse
import com.coding.androidtask.data.models.subcategories_properties.SubcategoryPropertiesResponse
import com.coding.androidtask.data.sources.remote.RemoteDataSource
import com.coding.androidtask.domain.repositories.CategoriesRepo
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CategoriesRepo {
    override suspend fun getCategories(): CategoriesResponse {
        return remoteDataSource.getCategories()
    }

    override suspend fun getSubCategoryProperties(categoryID: Int): SubcategoryPropertiesResponse =
        remoteDataSource.getSubCategoryProperties(categoryID)


}