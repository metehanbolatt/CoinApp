package com.metehanbolat.coinapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.metehanbolat.coinapp.databinding.CoinRowBinding
import com.metehanbolat.coinapp.model.Coin

class CoinAdapter(val coinList: ArrayList<Coin>): RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    class CoinViewHolder(val binding: CoinRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = CoinRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.binding.apply {
            coinId.text = coinList[position].id
            coinPrice.text = coinList[position].current_price.toString()
        }
    }

    override fun getItemCount(): Int = coinList.size

    fun updateCoinList(newCoinList: List<Coin>) {
        coinList.clear()
        coinList.addAll(newCoinList)
        notifyDataSetChanged()
    }
}