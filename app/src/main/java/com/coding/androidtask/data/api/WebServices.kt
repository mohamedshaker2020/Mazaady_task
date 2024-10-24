package com.coding.androidtask.data.api

import com.coding.androidtask.data.models.categories_response.CategoriesResponse
import com.coding.androidtask.data.models.subcategories_properties.SubcategoryPropertiesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET(value = "get_all_cats")
    suspend fun getAllCategories(): CategoriesResponse

    @GET(value = "properties")
    suspend fun getSubCategoryProperties(@Query(value = "cat") subcategoryID: Int): SubcategoryPropertiesResponse

}