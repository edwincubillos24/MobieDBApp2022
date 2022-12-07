package com.cubidevs.mobiedbapp2022.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cubidevs.mobiedbapp2022.databinding.FragmentListBinding
import com.cubidevs.mobiedbapp2022.server.model.Movie

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var listViewModel: ListViewModel
    private var moviesList: ArrayList<Movie> = ArrayList()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = listBinding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MoviesAdapter(moviesList, onItemClicked = { onMovieItemClicked(it)})

        listBinding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = moviesAdapter
            setHasFixedSize(false)
        }

        listViewModel.moviesLoaded.observe(viewLifecycleOwner){ result ->
            onMoviesLoadedSubscribe(result)
        }

        listViewModel.getMovies()
    }

    private fun onMovieItemClicked(movie: Movie) {
        findNavController().navigate(ListFragmentDirections.actionNavigationListToDetailFragment(movie))
    }

    private fun onMoviesLoadedSubscribe(moviesList: ArrayList<Movie>?) {
        moviesList?.let { moviesList ->
            moviesAdapter.appendItems(moviesList)
        }

    }
}