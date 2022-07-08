package com.kev.rickmorty

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kev.rickmorty.data.adapter.CharacterAdapter
import com.kev.rickmorty.data.model.CharacterList
import com.kev.rickmorty.viewmodel.CharactersActivityViewModel

class CharactersActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var characterAdapter: CharacterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        initViews()
        apiCall()
    }
    private fun initViews() {
        recyclerView = findViewById(R.id.characters_recycler_view)
        val gridLayoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.layoutManager = gridLayoutManager
        characterAdapter = CharacterAdapter(this)
        recyclerView.adapter = characterAdapter

    }



    private fun apiCall() {

        val viewModel = ViewModelProvider(this)[CharactersActivityViewModel::class.java]
        viewModel.getCharactersLiveDataObserver().observe(this, Observer<CharacterList> {
            if (it != null) {
                characterAdapter.setDataList(it.results)
                characterAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(baseContext, "Error fetching data", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.makeAPICall()
    }

}