package com.kev.rickmorty.data.model

data class CharacterList(val results: ArrayList<CharacterModel>)
data class CharacterModel(val id: Int, val name: String, val species: String, val image: String)

