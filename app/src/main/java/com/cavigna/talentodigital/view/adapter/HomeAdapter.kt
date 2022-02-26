package com.cavigna.talentodigital.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cavigna.talentodigital.R
import com.cavigna.talentodigital.databinding.ItemRowBinding
import com.cavigna.talentodigital.databinding.ItemRowHorizontalBinding
import com.cavigna.talentodigital.model.models.Coin
import com.cavigna.talentodigital.utils.*


class HomeAdapter(private val extraerMoneda: ExtraerMoneda) : ListAdapter<Coin, MyViewHolder>(
    Comparador()
) {

    interface ExtraerMoneda {
        fun extraerId(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val coin = getItem(position)


        holder.binding.apply {
            imageViewRow.loadSvg(coin.logoUrl)
            tvNombreRow.text = coin.name
            tvPrecio.text = redondeo(coin.price)
            tvSymbolRow.text = coin.symbol
            ratingBar.rating = (16/coin.rank.toFloat())

            cardView.setOnClickListener {
                extraerMoneda.extraerId(coin.id)
                Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_homeFragment_to_detailsFragment)
            }
        }
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ItemRowHorizontalBinding = ItemRowHorizontalBinding.bind(itemView)

    companion object {
        fun create(parent: ViewGroup): MyViewHolder {
            val layoutInflaterB = LayoutInflater.from(parent.context)
            val binding = ItemRowHorizontalBinding.inflate(layoutInflaterB, parent, false)

            return MyViewHolder(binding.root)
        }
    }

}

class Comparador : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }


}


