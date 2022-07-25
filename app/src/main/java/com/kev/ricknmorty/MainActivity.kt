package com.kev.ricknmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kev.ricknmorty.R
import com.kev.ricknmorty.adapter.RickMortyPagedAdapter
import com.kev.ricknmorty.databinding.ActivityMainBinding
import com.kev.ricknmorty.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter : RickMortyPagedAdapter
    private val viewModel  : CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecylcerView()
        loadingData()
    }

    private fun loadingData() {
        lifecycleScope.launch{
            viewModel.listData.collect{pagingData ->

                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun setUpRecylcerView() {
    mAdapter  = RickMortyPagedAdapter()
        binding.charactersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}