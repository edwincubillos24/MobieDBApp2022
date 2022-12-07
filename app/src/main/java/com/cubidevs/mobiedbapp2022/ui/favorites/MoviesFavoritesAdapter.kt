package com.cubidevs.mobiedbapp2022.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cubidevs.mobiedbapp2022.R
import com.cubidevs.mobiedbapp2022.databinding.CardViewItemMovieBinding
import com.cubidevs.mobiedbapp2022.local.LocalMovie
import com.squareup.picasso.Picasso

class MoviesFavoritesAdapter(
    private val moviesList: ArrayList<LocalMovie>,
    private val onItemClicked: (LocalMovie) -> Unit,
    private val onItemLongClicked: (LocalMovie) -> Unit,
) : RecyclerView.Adapter<MoviesFavoritesAdapter.MoviesFavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesFavoritesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_movie, parent, false)
        return MoviesFavoritesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesFavoritesViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bindMovie(movie)
        holder.itemView.setOnClickListener { onItemClicked(moviesList[position]) }
        holder.itemView.setOnLongClickListener { onItemLongClicked(moviesList[position])
            true
        }
    }

    override fun getItemCount(): Int = moviesList.size

    fun appendItems(newList: ArrayList<LocalMovie>) {
        moviesList.clear()
        moviesList.addAll(newList)
        notifyDataSetChanged()
    }

    class MoviesFavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewItemMovieBinding.bind(itemView)

        fun bindMovie(movie: LocalMovie) {
            with(binding) {
                movieTitleTextView.text = movie.name
                releaseDateTextView.text = movie.releaseDate
                voteAverageTextView.text = movie.voteAverage.toString()
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.posterPath).into(posterImageView)
            }
        }
    }
}