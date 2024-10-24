package com.coding.androidtask.ui.activities.home.adapter

import com.coding.androidtask.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.coding.androidtask.databinding.TabItemBinding
import com.coding.androidtask.ui.activities.home.adapter.TabsAdapter.TabsViewHolder

class TabsAdapter() : RecyclerView.Adapter<TabsViewHolder>() {
    val tabs: List<Tab> = listOf(
        Tab("All", true),
        Tab("Ui/Ux", false),
        Tab("Illustration", false),
        Tab("3D Animation", false)
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TabsViewHolder {
        val binding = TabItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TabsViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(
        holder: TabsViewHolder,
        position: Int
    ) {
        holder.bind(tabs[position])
    }

    override fun getItemCount(): Int = tabs.size


    class TabsViewHolder(val binding: TabItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tab: Tab) {
            binding.textView.text = tab.title
            if (tab.selectedState) {
                binding.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
            } else {
                binding.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.gray
                    )
                )
                binding.textView.setTextColor(ContextCompat.getColor(
                    context,
                    R.color.black
                ))
            }
        }
    }
}

data class Tab(val title: String, val selectedState: Boolean)