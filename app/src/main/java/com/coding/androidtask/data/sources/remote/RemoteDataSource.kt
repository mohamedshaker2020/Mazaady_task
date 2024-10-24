package com.coding.androidtask.data.sources.remote

import com.coding.androidtask.data.models.categories_response.CategoriesResponse
import com.coding.androidtask.data.models.subcategories_properties.SubcategoryPropertiesResponse

interface RemoteDataSource {
    suspend fun getCategories() : CategoriesResponse

    suspend fun getSubCategoryProperties(categoryID : Int) : SubcategoryPropertiesResponse
}