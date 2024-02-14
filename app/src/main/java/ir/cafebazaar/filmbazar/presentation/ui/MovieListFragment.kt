package ir.cafebazaar.filmbazar.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.GONE
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.cafebazaar.filmbazar.Extensions.addConstraintSet
import ir.cafebazaar.filmbazar.R
import ir.cafebazaar.filmbazar.presentation.MovieListAdapter


class MovieListFragment : Fragment() {

    lateinit var rootView: ConstraintLayout
    lateinit var recyclerView: RecyclerView
    lateinit var logo: ImageView
    lateinit var title: TextView

    lateinit var bottomLoadingViewRoot: ConstraintLayout
    lateinit var bottomLoadingViewProgressBar: ProgressBar
    lateinit var bottomLoadingViewText: TextView
    lateinit var bottomLoadingViewTryAgainButton: Button

    lateinit var recyclerViewAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initViews()
        setViews()
        return rootView
    }

    private fun initViews() {
        rootView = ConstraintLayout(requireContext()).apply {
            id = R.id.movie_list_fragment_root_view
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        }

        recyclerView = RecyclerView(requireContext()).apply {
            id = R.id.movie_list_fragment_recycler_view
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        logo = ImageView(requireContext()).apply {
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

        title = TextView(requireContext()).apply {
            id = R.id.movie_list_fragment_title
            text = context.getString(R.string.discover)
            setTextColor(Color.RED)
            gravity = Gravity.CENTER
            textSize = 16f
        }

        bottomLoadingViewText = TextView(requireContext()).apply {
            id = R.id.movie_list_fragment_Bottom_view_text
            text = context.getString(R.string.something_went_wrong)
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            textSize = 12f
        }

        bottomLoadingViewProgressBar = ProgressBar(requireContext()).apply {
            id = R.id.movie_list_fragment_Bottom_view_progress_bar
        }

        bottomLoadingViewTryAgainButton = Button(requireContext()).apply {
            id = R.id.movie_list_fragment_Bottom_view_button
            text = context.getString(R.string.try_again)
            textSize = 12f
            setTextColor(Color.RED)
            setBackgroundColor(Color.TRANSPARENT)
        }

        bottomLoadingViewRoot = ConstraintLayout(requireContext()).apply {
            id = R.id.movie_list_fragment_Bottom_view_root
            visibility = GONE
        }
    }

    private fun setViews() {

        bottomLoadingViewRoot.apply {
            addView(bottomLoadingViewText)
            addView(bottomLoadingViewProgressBar)
            addView(bottomLoadingViewTryAgainButton)
        }

        addConstraintSet(
            parentView = bottomLoadingViewRoot,
            childViewId = bottomLoadingViewText.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            startToStart = bottomLoadingViewRoot.id,
            topToTop = bottomLoadingViewRoot.id,
            bottomToBottom = bottomLoadingViewRoot.id,
            marginStart = 8
        )
        addConstraintSet(
            parentView = bottomLoadingViewRoot,
            childViewId = bottomLoadingViewTryAgainButton.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            endToEnd = bottomLoadingViewRoot.id,
            topToTop = bottomLoadingViewRoot.id,
            bottomToBottom = bottomLoadingViewRoot.id,
            marginEnd = 8
        )
        addConstraintSet(
            parentView = bottomLoadingViewRoot,
            childViewId = bottomLoadingViewProgressBar.id,
            width = 24,
            height = 24,
            startToStart = bottomLoadingViewRoot.id,
            endToEnd = bottomLoadingViewRoot.id,
            topToTop = bottomLoadingViewRoot.id,
            bottomToBottom = bottomLoadingViewRoot.id,
        )

        rootView.apply {
            addView(title)
            addView(logo)
            addView(recyclerView)
            addView(bottomLoadingViewRoot)
        }

        addConstraintSet(
            parentView = rootView,
            childViewId = title.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            startToStart = rootView.id,
            endToEnd = rootView.id,
            topToTop = rootView.id,
            marginTop = 8
        )
        addConstraintSet(
            parentView = rootView,
            childViewId = logo.id,
            width = 24,
            height = 24,
            endToEnd = rootView.id,
            topToTop = rootView.id,
            marginTop = 8,
            marginEnd = 8
        )
        addConstraintSet(
            parentView = rootView,
            childViewId = recyclerView.id,
            width = 0,
            height = 0,
            startToStart = rootView.id,
            endToEnd = rootView.id,
            topToBottom = title.id,
            bottomToTop = bottomLoadingViewRoot.id,
            marginTop = 8,
            marginEnd = 8,
            marginStart = 8
        )
        addConstraintSet(
            parentView = rootView,
            childViewId = bottomLoadingViewRoot.id,
            width = 0,
            height = 40,
            startToStart = rootView.id,
            endToEnd = rootView.id,
            topToBottom = recyclerView.id,
            bottomToBottom = rootView.id,
            marginTop = 8,
            marginEnd = 8,
            marginStart = 8
        )
    }
}