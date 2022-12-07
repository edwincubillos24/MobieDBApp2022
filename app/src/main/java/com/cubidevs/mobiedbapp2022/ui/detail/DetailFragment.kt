package com.cubidevs.mobiedbapp2022.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.cubidevs.mobiedbapp2022.R
import com.cubidevs.mobiedbapp2022.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private var movieExistAux = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
          return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie

        detailViewModel.searchMovie(movie.id)

        detailViewModel.movieExist.observe(viewLifecycleOwner) { movieExist ->
            if (movieExist){
                detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_exist))
                movieExistAux = true
            }
            else{
                detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorites))
                movieExistAux = false
            }
        }

        with(detailBinding){
            movieTitleTextView.text = movie.title
            releaseDateTextView.text = movie.releaseDate
            voteAverageTextView.text = movie.voteAverage.toString()
            summaryTextView.text = movie.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.posterPath).into(posterImageView)

            favoritesImageView.setOnClickListener {
                if (movieExistAux)
                    Toast.makeText(context, "${movie.title} ya esta en favoritos", Toast.LENGTH_LONG).show()
                else {
                    detailBinding.favoritesImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_exist))
                    movieExistAux = true
                    detailViewModel.addMovieToFavorites(movie)
                }
            }
        }
    }
}