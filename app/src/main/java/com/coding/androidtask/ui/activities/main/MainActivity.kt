package com.coding.androidtask.ui.activities.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.coding.androidtask.R
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.coding.androidtask.data.models.categories_response.Category
import com.coding.androidtask.data.models.subcategories_properties.SubcategoryProperties
import com.coding.androidtask.databinding.ActivityMainBinding
import com.coding.androidtask.ui.activities.home.HomeActivity
import com.coding.androidtask.ui.activities.main.adapters.PropertiesAdapter
import com.coding.androidtask.ui.activities.main.models.SelectedProperty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val vm: MainActivityViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    private var propertiesAdapter = PropertiesAdapter(emptyList(), {
        showCustomDialog()
    }, { selectedProperty ->
        saveSelectedData(selectedProperty)
    })


    private var categoriesList: List<Category?> = emptyList()
    private var propertiesList: List<SubcategoryProperties> = emptyList()
    private var selectedPropertiesList: ArrayList<String> = ArrayList<String>()
    private var categoriesNameList: ArrayList<String> = ArrayList<String>()
    private var subcategoriesNameList: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        vm.fetchCategories()
        observeToLivedata()
        onSelectSubcategory()
        binding.nextPageBtn.setOnClickListener { startHomeActivity() }
        binding.submitButton.setOnClickListener {
            showSelectedDataDialog()
        }
    }

    private fun startHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun initRV() {
        binding.recyclerView.adapter = propertiesAdapter
    }

    private fun observeToLivedata() {
        vm.categoriesList.observe(this) { categoriesList ->
            this@MainActivity.categoriesList = categoriesList ?: emptyList()
            prepareMainCategoriesNameList()
            initMainCategorySpinner()
        }
        vm.propertiesList.observe(this) {
            if (it != null)
                this.propertiesList = it as List<SubcategoryProperties>
            propertiesAdapter.updateList(propertiesList)
        }
    }

    private fun prepareMainCategoriesNameList() {
        categoriesList.forEach { categories ->
            categoriesNameList.add(categories?.slug!!)
        }
    }

    private fun prepareSubcategoriesNameList(categoryName: String) {
        subcategoriesNameList.clear()
        categoriesList.forEach { categories ->
            if (categories?.slug == categoryName) {
                categories.children?.forEach { subcategory ->
                    subcategoriesNameList.add(subcategory?.slug!!)
                }
            }
        }
    }

    private fun initMainCategorySpinner() {
        val mainCategoriesAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categoriesNameList)
        binding.categoriesSpinner.setAdapter(mainCategoriesAdapter)
        binding.categoriesSpinner.setDropDownBackgroundResource(R.color.white)
        onSelectCategory()
    }

    private fun initSubcategorySpinner() {
        val subcategoriesAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, subcategoriesNameList)
        Log.e("subcategoriesAdapter", subcategoriesNameList.toString())
        binding.subCategoriesSpinner.setAdapter(subcategoriesAdapter)
        binding.subCategoriesSpinner.setDropDownBackgroundResource(R.color.white)
    }

    private fun onSelectCategory() {
        binding.categoriesSpinner.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            prepareSubcategoriesNameList(selectedItem)
            binding.subCategoriesSpinner.setText("")
            binding.subCategoriesTextInput.visibility = View.VISIBLE
            initSubcategorySpinner()
            propertiesAdapter.updateList(emptyList())
        }
    }

    private fun onSelectSubcategory() {
        binding.subCategoriesSpinner.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            var categoryId = 0
            categoriesList.forEach {
                it?.children?.forEach {
                    if (selectedItem == it?.slug)
                        categoryId = it.id!!
                }
            }
            vm.fetchProperties(categoryId)
            propertiesAdapter.updateList(emptyList())
        }
    }

    private fun saveSelectedData(selectedProperty: SelectedProperty) {
        selectedPropertiesList.add("${selectedProperty.property} : ${selectedProperty.value}")
    }

    private fun showCustomDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.input_dialog, null)

        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("Other")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun showSelectedDataDialog() {
        selectedPropertiesList.add(0, binding.subCategoriesSpinner.text.toString())
        val items = selectedPropertiesList.toTypedArray()

        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle(binding.categoriesSpinner.text)
            .setItems(items) { _, _ -> }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        dialogBuilder.create().show()
    }
}

