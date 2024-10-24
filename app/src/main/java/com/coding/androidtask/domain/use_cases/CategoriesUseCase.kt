package com.coding.androidtask.domain.use_cases

import com.coding.androidtask.data.models.categories_response.CategoriesResponse
import com.coding.androidtask.data.models.subcategories_properties.SubcategoryPropertiesResponse
import com.coding.androidtask.domain.repositories.CategoriesRepo
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(private val categoriesRepo: CategoriesRepo) {
    suspend fun fetchCategories(): CategoriesResponse = categoriesRepo.getCategories()

    suspend fun fetchProperties(categoryID: Int): SubcategoryPropertiesResponse =
        categoriesRepo.getSubCategoryProperties(categoryID)
}