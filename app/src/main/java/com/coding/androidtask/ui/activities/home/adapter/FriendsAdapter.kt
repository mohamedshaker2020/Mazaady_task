package com.coding.androidtask.ui.activities.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.androidtask.R
import com.coding.androidtask.databinding.OnlineFriendsItemBinding
import com.coding.androidtask.ui.activities.home.adapter.FriendsAdapter.FriendsViewHolder

class FriendsAdapter() : RecyclerView.Adapter<FriendsViewHolder>() {
    val images: List<Int> = listOf(
        R.drawable.online_friend_image,
        R.drawable.online_friend_image,
        R.drawable.online_friend_image,
        R.drawable.online_friend_image,
        R.drawable.online_friend_image,
        R.drawable.online_friend_image
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendsViewHolder {
        val binding = OnlineFriendsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FriendsViewHolder,
        position: Int
    ) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size


    class FriendsViewHolder(val binding: OnlineFriendsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image:Int) {
            binding.imageView.setImageResource(image)
        }
    }
}