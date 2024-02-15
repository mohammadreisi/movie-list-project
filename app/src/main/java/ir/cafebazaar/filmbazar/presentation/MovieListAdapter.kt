package ir.cafebazaar.filmbazar.presentation

import android.annotation.SuppressLint
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
        val currentItem = movieItemList[position]
        movieItemCell.setItemDetails(currentItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addMovieItems(movieItems: List<MovieItem>) {
        val firstIndex = movieItemList.size
        movieItemList.addAll(movieItems)
        notifyItemRangeInserted(
            firstIndex,
            movieItemList.size - firstIndex
        )
    }

    fun getCurrentAdapterSize(): Int {
        return movieItemList.size
    }
}