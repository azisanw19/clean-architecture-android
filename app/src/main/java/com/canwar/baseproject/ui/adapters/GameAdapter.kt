package com.canwar.baseproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.canwar.baseproject.R
import com.canwar.baseproject.databinding.ItemRowGameBinding
import com.canwar.baseproject.model.responseModel.Game

class GameAdapter : PagingDataAdapter<Game, GameAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    class ViewHolder(private var binding: ItemRowGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(game: Game) {
            binding.apply {
                tvTitle.text = game.name
                tvReleaseDate.text = itemView.context.getString(R.string.release_date_x, game.released)
                tvRating.text = game.rating.toString()
                /*Glide.with(this.root)
                    .load(game.backgroundImage)
                    .into(ivGame)*/
                root.setOnClickListener {
                    Toast.makeText(itemView.context, "Game Clicked ${game.backgroundImage}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = getItem(position)
        if (game != null) {
            holder.setData(game)
        }
    }
}