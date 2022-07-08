package com.kev.rickmorty.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kev.rickmorty.R
import com.kev.rickmorty.data.model.CharacterModel

class CharacterAdapter(val context: Context) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    var items = ArrayList<CharacterModel>()

    fun setDataList(data: ArrayList<CharacterModel>) {
        this.items = data
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterImageView: ImageView = view.findViewById(R.id.character_image_view)
        val characterNameTextView: TextView = view.findViewById(R.id.character_name_tv)
        val characterGenderTextView: TextView = view.findViewById(R.id.character_gender_tv)
        val characterSpeciesTextView: TextView = view.findViewById(R.id.character_species_tv)
        val characterStatusTextView: TextView = view.findViewById(R.id.character_status_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {

        val item = items[position]
        holder.characterNameTextView.text = item.name
        holder.characterGenderTextView.text = item.gender
        holder.characterSpeciesTextView.text = item.species
        holder.characterStatusTextView.text = item.status

        Glide.with(context).load(item.image).fitCenter().placeholder(R.mipmap.ic_launcher)
            .into(holder.characterImageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}