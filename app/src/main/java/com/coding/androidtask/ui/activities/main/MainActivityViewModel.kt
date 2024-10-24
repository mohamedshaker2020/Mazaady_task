package com.coding.androidtask.ui.activities.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.androidtask.data.models.categories_response.Category
import com.coding.androidtask.data.models.subcategories_properties.SubcategoryProperties
import com.coding.androidtask.domain.use_cases.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val categoriesUseCase: CategoriesUseCase) :
    ViewModel() {

    val categoriesList = MutableLiveData<List<Category?>?>()
    val propertiesList = MutableLiveData<List<SubcategoryProperties?>?>()

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = categoriesUseCase.fetchCategories()
                categoriesList.value = response.data?.categories
            } catch (e: Throwable) {
                Log.e("ViewModel - fetchCategories", e.localizedMessage ?: "Unknown Error")
            }
        }
    }

    fun fetchProperties(categoryId: Int) {
        viewModelScope.launch {
            try {
                val response = categoriesUseCase.fetchProperties(categoryId)
                propertiesList.value = response.data
            } catch (e: Throwable) {
                Log.e("ViewModel - fetchProperties", e.localizedMessage ?: "Unknown Error")
            }
        }
    }
}