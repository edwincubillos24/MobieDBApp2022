package com.cubidevs.mobiedbapp2022.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cubidevs.mobiedbapp2022.R
import com.cubidevs.mobiedbapp2022.databinding.FragmentFavoritesBinding
import com.cubidevs.mobiedbapp2022.local.LocalMovie
import com.cubidevs.mobiedbapp2022.server.model.Movie

class FavoritesFragment : Fragment() {

    private lateinit var favoritesBinding: FragmentFavoritesBinding
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var moviesFavoritesAdapter: MoviesFavoritesAdapter
    private var moviesList: ArrayList<LocalMovie> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        favoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false)

        favoritesViewModel.loadMovies()

        moviesFavoritesAdapter = MoviesFavoritesAdapter(moviesList,
            onItemClicked = {onItemClicked(it)},
        onItemLongClicked = {onItemLongClicked(it)})

        favoritesBinding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavoritesFragment.context)
            adapter = moviesFavoritesAdapter
            setHasFixedSize(false)
        }

        favoritesViewModel.moviesLoaded.observe(viewLifecycleOwner,{ moviesFavoritesAdapter.appendItems(it)})

        val root: View = favoritesBinding.root
        return root
    }

    private fun onItemLongClicked(localMovie: LocalMovie) {
        val alertDialog: AlertDialog? = activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("¿Desea Eliminar ${localMovie.name} de sus favoritos?")
                setPositiveButton(R.string.accept) { dialog, id ->
                    favoritesViewModel.deleteMovie(localMovie)
                    favoritesViewModel.loadMovies()
                }
                setNegativeButton(R.string.cancel) {  dialog, id ->
                }
            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun onItemClicked(localMovie: LocalMovie) {

    }
}