package ir.cafebazaar.filmbazar.presentation

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginTop
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ir.cafebazaar.filmbazar.Extensions.addConstraintSet
import ir.cafebazaar.filmbazar.Extensions.dp
import ir.cafebazaar.filmbazar.R
import ir.cafebazaar.filmbazar.domain.MovieItem

class MovieItemCell(context: Context) : ConstraintLayout(context) {
    private var moviePoster: ImageView
    private var movieTitle: TextView

    init {
        moviePoster = ImageView(context).apply {
            id = R.id.movie_item_cell_poster
        }
        movieTitle = TextView(context).apply {
            id = R.id.movie_item_cell_title
            textSize = 12f
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.BLACK)
        }

        this.apply {
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            setPadding(4.dp(), 4.dp(), 4.dp(), 4.dp())
            addView(moviePoster)
            addView(movieTitle)
        }
        addConstraintSet(
            parentView = this,
            childViewId = moviePoster.id,
            topToTop = this.id,
            startToStart = this.id,
            endToEnd = this.id,
            width = 120.dp(),
            height = 180.dp(),
            marginTop = 16.dp()
        )
        addConstraintSet(
            parentView = this,
            childViewId = movieTitle.id,
            topToBottom = moviePoster.id,
            startToStart = moviePoster.id,
            endToEnd = moviePoster.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            marginTop = 8.dp()
        )
    }

    fun setItemDetails(movieItem: MovieItem) {
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/original${movieItem.poster_path}")
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .into(moviePoster)
        movieTitle.text = movieItem.title
    }

}