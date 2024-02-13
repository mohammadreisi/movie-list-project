package ir.cafebazaar.filmbazar.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.cafebazaar.filmbazar.R


class MovieListFragment : android.app.Fragment() {

    lateinit var rootView: ConstraintLayout
    lateinit var recyclerView: RecyclerView
    lateinit var logo: ImageView
    lateinit var title: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = ConstraintLayout(activity).apply {
            id = R.id.movie_list_fragment_root_view
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        }

        recyclerView = RecyclerView(activity).apply {
            id = R.id.movie_list_fragment_recycler_view
            layoutManager = GridLayoutManager(activity, 3)
        }

        logo = ImageView(activity).apply {
            id = R.id.movie_list_fragment_logo
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.cafe_bazaar_logo,
                    null
                )
            )
        }
        return rootView
    }
}