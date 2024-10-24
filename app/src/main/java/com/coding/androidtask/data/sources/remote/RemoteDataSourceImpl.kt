package com.coding.androidtask.data.sources.remote

import com.coding.androidtask.data.api.WebServices
import com.coding.androidtask.data.models.categories_response.CategoriesResponse
import com.coding.androidtask.data.models.categories_response.Category
import com.coding.androidtask.data.models.subcategories_properties.SubcategoryPropertiesResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    RemoteDataSource {
    override suspend fun getCategories(): CategoriesResponse = webServices.getAllCategories()


    override suspend fun getSubCategoryProperties(categoryID : Int): SubcategoryPropertiesResponse =
        webServices.getSubCategoryProperties(categoryID)

}