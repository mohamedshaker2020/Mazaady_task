package com.coding.androidtask.domain.repositories

import com.coding.androidtask.data.models.categories_response.CategoriesResponse
import com.coding.androidtask.data.models.subcategories_properties.SubcategoryPropertiesResponse

interface CategoriesRepo {
    suspend fun getCategories(): CategoriesResponse
    suspend fun getSubCategoryProperties(categoryID: Int): SubcategoryPropertiesResponse

}