package ir.cafebazaar.filmbazar.presentation

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import ir.cafebazaar.filmbazar.Extensions.addConstraintSet
import ir.cafebazaar.filmbazar.R

class MovieItemCell(context: Context): ConstraintLayout(context) {
    private var moviePoster: ImageView
    private var movieTitle: TextView

    init {
        moviePoster = ImageView(context).apply {
            id = R.id.movie_item_cell_poster
        }
        movieTitle = TextView(context).apply {
            id = R.id.movie_item_cell_title
            textSize = 12f
            setTextColor(Color.BLACK)
        }

        this.apply {
            layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            setPadding(4,4,4,4)
            addView(moviePoster)
            addView(movieTitle)
        }
        addConstraintSet(
            parentView = this,
            childViewId = moviePoster.id,
            topToTop = this.id,
            startToStart = this.id,
            endToEnd = this.id,
            width = 60,
            height = 100
        )
        addConstraintSet(
            parentView = this,
            childViewId = movieTitle.id,
            topToBottom = moviePoster.id,
            startToStart = this.id,
            endToEnd = this.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            marginTop = 8
        )
    }
}