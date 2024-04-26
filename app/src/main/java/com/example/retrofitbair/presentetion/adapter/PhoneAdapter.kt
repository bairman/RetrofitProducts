package com.example.retrofitbair.presentetion.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitbair.presentetion.data.Product
import com.example.retrofitbair.R
import com.example.retrofitbair.databinding.ListItemBinding

class PhoneAdapter: ListAdapter<Product, PhoneAdapter.Holder>(Comparator()) {

    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ListItemBinding.bind(view)

        fun bind(product: Product) = with(binding){
            tittle.text = product.title
            description.text = product.description

        }
    }

    class Comparator: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return  oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return Holder(view)
        Log.d("TAG", "LoGGGGGGGGG")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
        Log.d("TAG", "LoGGGGGGGGG")
    }


}