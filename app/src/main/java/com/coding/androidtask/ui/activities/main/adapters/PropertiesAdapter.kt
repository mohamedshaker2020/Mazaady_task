package com.coding.androidtask.ui.activities.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coding.androidtask.R
import com.coding.androidtask.data.models.subcategories_properties.SubcategoryProperties
import com.coding.androidtask.databinding.SubcategoryPropertiesBinding
import com.coding.androidtask.ui.activities.main.models.SelectedProperty

class PropertiesAdapter(
    private var subcategoriesProperties: List<SubcategoryProperties?>,
    private val onClick: () -> Unit,
    private val onSelectData: (SelectedProperty) -> Unit,
) : RecyclerView.Adapter<PropertiesAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val binding =
            SubcategoryPropertiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int
    ) {
        holder.bind(subcategoriesProperties[position]!!)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<SubcategoryProperties>) {
        this.subcategoriesProperties = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = subcategoriesProperties.size

    inner class MainViewHolder(val binding: SubcategoryPropertiesBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        val list: ArrayList<String> = ArrayList<String>()
        fun bind(subcategoriesProperties: SubcategoryProperties) {
            preparePropertiesListName(subcategoriesProperties)
            val propertiesAdapter =
                ArrayAdapter(
                    context,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )
            binding.subcategoryItemTextInput.hint = subcategoriesProperties.slug
            binding.subcategoryItemSpinner.setAdapter(propertiesAdapter)
            binding.subcategoryItemSpinner.setDropDownBackgroundResource(R.color.white)
            binding.subcategoryItemSpinner.setOnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                onSelectData.invoke(
                    SelectedProperty(
                        property = subcategoriesProperties.slug!!,
                        value = selectedItem
                    )
                )
                if (selectedItem == context.getString(R.string.other))
                    onClick.invoke()

            }
        }

        fun preparePropertiesListName(subcategoriesProperties: SubcategoryProperties) {
            subcategoriesProperties.options?.forEach {
                list.add(it?.slug!!)
            }
            list.add(context.getString(R.string.other))
        }
    }

}