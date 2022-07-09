package com.kev.rickmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kev.rickmorty.data.model.CharacterList
import com.kev.rickmorty.network.RetrofitInstance
import com.kev.rickmorty.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersActivityViewModel : ViewModel() {
    private lateinit var characterListData: MutableLiveData<CharacterList>

    init {
        characterListData = MutableLiveData()
    }

    fun getCharactersLiveDataObserver(): MutableLiveData<CharacterList> {
        return characterListData
    }


    //function to query api
    fun makeAPICall() {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.getCharacters()
        call.enqueue(object : Callback<CharacterList?> {
            override fun onResponse(
                call: Call<CharacterList?>,
                response: Response<CharacterList?>
            ) {
                if (response.isSuccessful) {
                    characterListData.postValue(response.body())
                } else {
                    characterListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<CharacterList?>, t: Throwable) {
                characterListData.postValue(null)
            }
        })
    }
}