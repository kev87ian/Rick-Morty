package com.kev.rickmorty

import android.app.ProgressDialog
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
    lateinit var mDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        initViews()
        apiCall()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.characters_recycler_view)
//        val gridLayoutManager = GridLayoutManager(applicationContext, 2)
//        recyclerView.layoutManager = gridLayoutManager

        recyclerView.layoutManager = LinearLayoutManager(this)
        characterAdapter = CharacterAdapter(this)
        recyclerView.adapter = characterAdapter
        mDialog = ProgressDialog(this)
        mDialog.setMessage("Please wait...")
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        mDialog.isIndeterminate = true
        mDialog.setCancelable(false)
        mDialog.show()
    }


    private fun apiCall() {

        val viewModel = ViewModelProvider(this)[CharactersActivityViewModel::class.java]
        viewModel.getCharactersLiveDataObserver().observe(this, Observer<CharacterList> {
            if (it != null) {
                characterAdapter.setDataList(it.results)
                characterAdapter.notifyDataSetChanged()
                mDialog.hide()
            } else {
                Toast.makeText(baseContext, "Error fetching data", Toast.LENGTH_LONG).show()
                mDialog.hide()
            }
        })

        viewModel.makeAPICall()
    }

}