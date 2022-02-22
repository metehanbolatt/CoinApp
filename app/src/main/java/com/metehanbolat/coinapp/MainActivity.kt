package com.metehanbolat.coinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.metehanbolat.coinapp.adapter.CoinAdapter
import com.metehanbolat.coinapp.databinding.ActivityMainBinding
import com.metehanbolat.coinapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter : CoinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adapter = CoinAdapter(arrayListOf())

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.refreshData()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.coinError.visibility = View.GONE
            binding.coinLoading.visibility = View.VISIBLE
            mainViewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        mainViewModel.coins.observe(this) { coins ->
            coins?.let {
                binding.recyclerView.visibility = View.VISIBLE
                adapter.updateCoinList(it)
            }
        }

        mainViewModel.coinError.observe(this) { error ->
            error?.let {
                if (it) binding.coinError.visibility = View.VISIBLE else binding.coinError.visibility = View.GONE
            }
        }

        mainViewModel.coinLoading.observe(this) { loading ->
            loading?.let {
                if (it) {
                    binding.coinError.visibility = View.GONE
                    //binding.recyclerView.visibility = View.GONE
                    binding.coinLoading.visibility = View.VISIBLE
                }else {
                    binding.coinLoading.visibility = View.GONE
                }
            }
        }
    }
}