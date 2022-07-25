package com.kev.ricknmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.kev.ricknmorty.R
import com.kev.ricknmorty.databinding.CharcterItemBinding
import com.kev.ricknmorty.model.Result


class RickMortyPagedAdapter : PagingDataAdapter<Result, RickMortyPagedAdapter.MyViewHolder>(diffCallback) {


    inner class MyViewHolder(val binding: CharcterItemBinding ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        /*The above method is from PagingAdapter*/

        val currentItem  = getItem(position)

        holder.binding.apply {
            characterNameTv.text = currentItem?.name
            characterGenderTv.text = currentItem?.gender
            characterSpeciesTv.text = currentItem?.species
            characterStatusTv.text = currentItem?.status

            //imageloading
            characterImageView.load(currentItem?.image){
                crossfade(true)
                crossfade(1000)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(CharcterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}