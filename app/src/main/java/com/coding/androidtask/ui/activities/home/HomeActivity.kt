package com.coding.androidtask.ui.activities.home

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import com.coding.androidtask.databinding.ActivityHomeBinding
import com.coding.androidtask.ui.activities.home.adapter.FriendsAdapter
import com.coding.androidtask.ui.activities.home.adapter.TabsAdapter
import com.coding.androidtask.ui.activities.home.adapter.view_pager.CoursesViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val tabsAdapter = TabsAdapter()
    val friendsAdapter = FriendsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tabsRecyclerView.adapter = tabsAdapter
        binding.onlineFriendsRecyclerView.adapter = friendsAdapter
        initOffersViewPager()
        tabMargin()
    }

    private fun initOffersViewPager() {
        val offersAdapter = CoursesViewPagerAdapter()
        binding.coursesViewPager.adapter = offersAdapter
        TabLayoutMediator(binding.tabLayout, binding.coursesViewPager) { _, _ -> }.attach()
    }

    private fun tabMargin() {
        val tabs = binding.tabLayout.getChildAt(0) as ViewGroup
        tabs.forEach {
            val layoutParams = it.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginStart = 16
            it.layoutParams = layoutParams
            binding.tabLayout.requestLayout()
        }
    }

}