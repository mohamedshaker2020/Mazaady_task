package com.coding.androidtask.ui.activities.home.adapter.view_pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.coding.androidtask.ui.util.models.Course
import com.coding.androidtask.R
import com.coding.androidtask.databinding.CourseItemLeftBinding
import com.coding.androidtask.databinding.CourseItemRightBinding


class CoursesViewPagerAdapter() : RecyclerView.Adapter<ViewHolder>() {
    private val offersList = listOf(
        Course(1, R.drawable.courses_image,"1"),
        Course(2, R.drawable.courses_image,"2"),
        Course(3, R.drawable.courses_image,"3")
    )

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0)
            CourseViewType.LeftCourseView.value
        else
            CourseViewType.LeftCourseView.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == CourseViewType.LeftCourseView.value) {
            val binding = CourseItemLeftBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            LeftOfferViewHolder(binding)
        } else {
            val binding = CourseItemRightBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            RightOfferViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is LeftOfferViewHolder -> holder.binding.offer = offersList[position]
            is RightOfferViewHolder -> holder.binding.offer = offersList[position]
        }
    }

    class RightOfferViewHolder(val binding: CourseItemRightBinding) : ViewHolder(binding.root)

    class LeftOfferViewHolder(val binding: CourseItemLeftBinding) : ViewHolder(binding.root)

    override fun getItemCount(): Int = offersList.size
}