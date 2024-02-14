package ir.cafebazaar.filmbazar.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ir.cafebazaar.filmbazar.domain.MovieItem

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var movieItemList = mutableListOf<MovieItem>()

    class MovieListViewHolder(itemView: View) : ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(MovieItemCell(parent.context))
    }

    override fun getItemCount(): Int {
        return movieItemList.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movieItemCell = holder.itemView as MovieItemCell
    }

    fun addMovieItemsWithRange(movieItems: List<MovieItem>){

    }
}