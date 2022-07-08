package com.kev.rickmorty.data.model

data class CharacterList(val results: ArrayList<CharacterData>)
data class CharacterData(val id: Int, val name: String, val species: String, val image: String, val gender : String, val status : String)

