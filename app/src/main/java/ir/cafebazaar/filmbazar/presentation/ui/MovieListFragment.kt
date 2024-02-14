package ir.cafebazaar.filmbazar.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.GONE
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.cafebazaar.filmbazar.Extensions.addConstraintSet
import ir.cafebazaar.filmbazar.Extensions.dp
import ir.cafebazaar.filmbazar.R
import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.presentation.MovieListAdapter
import ir.cafebazaar.filmbazar.presentation.viewmodel.MovieListViewModel
import org.w3c.dom.Text

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    //Root view
    private lateinit var rootView: ConstraintLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var logo: ImageView
    private lateinit var title: TextView

    //Loading screen
    private lateinit var loadingScreenRoot: ConstraintLayout
    private lateinit var loadingScreenLogo: ImageView
    private lateinit var loadingScreenProgressBar: ProgressBar

    //Error screen
    private lateinit var errorScreenRoot: ConstraintLayout
    private lateinit var errorScreenIcon: ImageView
    private lateinit var errorScreenTitle: TextView
    private lateinit var errorScreenDescription: TextView
    private lateinit var errorScreenButton: Button

    //Bottom strip screen
    private lateinit var bottomStripScreenRoot: ConstraintLayout
    private lateinit var bottomStripScreenProgressBar: ProgressBar
    private lateinit var bottomStripScreenText: TextView
    private lateinit var bottomStripScreenTryAgainButton: Button

    private lateinit var recyclerViewAdapter: MovieListAdapter

    private var currentPageNumber: Int = 0

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerViewAdapter = MovieListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initViews()
        setViews()
        setListeners()
        viewModel.getMovieItems(currentPageNumber++)
        return rootView
    }

    private fun initViews() {

        //Root view
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
            adapter = recyclerViewAdapter
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
            textSize = 24f
        }

        //Loading screen
        loadingScreenRoot = ConstraintLayout(requireContext()).apply {
            id = R.id.movie_list_fragment_loading_screen_root
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            visibility = GONE
        }

        loadingScreenLogo = ImageView(requireContext()).apply {
            id = R.id.movie_list_fragment_loading_screen_logo
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.cafe_bazaar_logo,
                    null
                )
            )
        }

        loadingScreenProgressBar = ProgressBar(requireContext()).apply {
            id = R.id.movie_list_fragment_loading_screen_progress_bar
        }

        //Error screen
        errorScreenRoot = ConstraintLayout(requireContext()).apply {
            id = R.id.movie_list_fragment_error_screen_root
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            visibility = GONE
        }

        errorScreenIcon = ImageView(requireContext()).apply {
            id = R.id.movie_list_fragment_error_screen_icon
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.sad_face_logo,
                    null
                )
            )
            setPadding(16.dp(), 16.dp(), 16.dp(), 16.dp())
            background = ResourcesCompat.getDrawable(resources, R.drawable.error_icon_background, null)
        }

        errorScreenTitle = TextView(requireContext()).apply {
            id = R.id.movie_list_fragment_error_screen_title
            text = context.getString(R.string.connection_glitch)
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            textSize = 24f
        }

        errorScreenDescription = TextView(requireContext()).apply {
            id = R.id.movie_list_fragment_error_screen_description
            text = context.getString(R.string.connection_problem_hint)
            setTextColor(ResourcesCompat.getColor(resources, R.color.text_description, null))
            gravity = Gravity.CENTER
            textSize = 18f
        }

        errorScreenButton = Button(requireContext()).apply {
            id = R.id.movie_list_fragment_error_screen_button
            text = context.getString(R.string.retry)
            textSize = 18f
            setPadding(8.dp(), 8.dp(), 8.dp(), 8.dp())
            setTextColor(Color.WHITE)
            background = ResourcesCompat.getDrawable(resources, R.drawable.error_button_background, null)
        }

        //Bottom strip screen
        bottomStripScreenText = TextView(requireContext()).apply {
            id = R.id.movie_list_fragment_Bottom_view_text
            text = context.getString(R.string.something_went_wrong)
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            textSize = 12f
        }

        bottomStripScreenProgressBar = ProgressBar(requireContext()).apply {
            id = R.id.movie_list_fragment_Bottom_view_progress_bar
        }

        bottomStripScreenTryAgainButton = Button(requireContext()).apply {
            id = R.id.movie_list_fragment_Bottom_view_button
            text = context.getString(R.string.try_again)
            textSize = 12f
            setTextColor(Color.RED)
            setBackgroundColor(Color.TRANSPARENT)
        }

        bottomStripScreenRoot = ConstraintLayout(requireContext()).apply {
            id = R.id.movie_list_fragment_Bottom_view_root
            visibility = GONE
        }
    }

    private fun setViews() {

        loadingScreenRoot.apply {
            addView(loadingScreenLogo)
            addView(loadingScreenProgressBar)
        }

        addConstraintSet(
            parentView = loadingScreenRoot,
            childViewId = loadingScreenLogo.id,
            width = 84.dp(),
            height = 84.dp(),
            topToTop = loadingScreenRoot.id,
            startToStart = loadingScreenRoot.id,
            endToEnd = loadingScreenRoot.id
        )
        addConstraintSet(
            parentView = loadingScreenRoot,
            childViewId = loadingScreenProgressBar.id,
            width = 36.dp(),
            height = 36.dp(),
            topToBottom = loadingScreenLogo.id,
            startToStart = loadingScreenRoot.id,
            endToEnd = loadingScreenRoot.id,
            marginTop = 4.dp()
        )

        errorScreenRoot.apply {
            addView(errorScreenIcon)
            addView(errorScreenTitle)
            addView(errorScreenDescription)
            addView(errorScreenButton)
        }
        addConstraintSet(
            parentView = errorScreenRoot,
            childViewId = errorScreenIcon.id,
            width = 96.dp(),
            height = 96.dp(),
            topToTop = errorScreenRoot.id,
            startToStart = errorScreenRoot.id,
            endToEnd = errorScreenRoot.id
        )
        addConstraintSet(
            parentView = errorScreenRoot,
            childViewId = errorScreenTitle.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            topToBottom = errorScreenIcon.id,
            startToStart = errorScreenRoot.id,
            endToEnd = errorScreenRoot.id,
            marginTop = 16.dp()
        )
        addConstraintSet(
            parentView = errorScreenRoot,
            childViewId = errorScreenDescription.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            topToBottom = errorScreenTitle.id,
            startToStart = errorScreenRoot.id,
            endToEnd = errorScreenRoot.id,
            marginTop = 16.dp()
        )

        addConstraintSet(
            parentView = errorScreenRoot,
            childViewId = errorScreenButton.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            topToBottom = errorScreenDescription.id,
            startToStart = errorScreenRoot.id,
            endToEnd = errorScreenRoot.id,
            marginTop = 16.dp()
        )

        bottomStripScreenRoot.apply {
            addView(bottomStripScreenText)
            addView(bottomStripScreenProgressBar)
            addView(bottomStripScreenTryAgainButton)
        }

        addConstraintSet(
            parentView = bottomStripScreenRoot,
            childViewId = bottomStripScreenText.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            startToStart = bottomStripScreenRoot.id,
            topToTop = bottomStripScreenRoot.id,
            bottomToBottom = bottomStripScreenRoot.id,
            marginStart = 8.dp()
        )
        addConstraintSet(
            parentView = bottomStripScreenRoot,
            childViewId = bottomStripScreenTryAgainButton.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            endToEnd = bottomStripScreenRoot.id,
            topToTop = bottomStripScreenRoot.id,
            bottomToBottom = bottomStripScreenRoot.id,
            marginEnd = 8.dp()
        )
        addConstraintSet(
            parentView = bottomStripScreenRoot,
            childViewId = bottomStripScreenProgressBar.id,
            width = 24.dp(),
            height = 24.dp(),
            startToStart = bottomStripScreenRoot.id,
            endToEnd = bottomStripScreenRoot.id,
            topToTop = bottomStripScreenRoot.id,
            bottomToBottom = bottomStripScreenRoot.id,
        )

        rootView.apply {
            addView(title)
            addView(logo)
            addView(recyclerView)
            addView(loadingScreenRoot)
            addView(errorScreenRoot)
            addView(bottomStripScreenRoot)
        }

        addConstraintSet(
            parentView = rootView,
            childViewId = title.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            startToStart = rootView.id,
            endToEnd = rootView.id,
            topToTop = rootView.id,
            marginTop = 8.dp()
        )
        addConstraintSet(
            parentView = rootView,
            childViewId = logo.id,
            width = 36.dp(),
            height = 36.dp(),
            endToEnd = rootView.id,
            topToTop = rootView.id,
            marginTop = 8.dp(),
            marginEnd = 8.dp()
        )
        addConstraintSet(
            parentView = rootView,
            childViewId = recyclerView.id,
            width = 0.dp(),
            height = 0.dp(),
            startToStart = rootView.id,
            endToEnd = rootView.id,
            topToBottom = title.id,
            bottomToTop = bottomStripScreenRoot.id,
            marginTop = 8.dp(),
            marginEnd = 8.dp(),
            marginStart = 8.dp()
        )
        addConstraintSet(
            parentView = rootView,
            childViewId = loadingScreenRoot.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            topToTop = rootView.id,
            bottomToBottom = rootView.id,
            startToStart = rootView.id,
            endToEnd = rootView.id
        )
        addConstraintSet(
            parentView = rootView,
            childViewId = errorScreenRoot.id,
            width = LayoutParams.WRAP_CONTENT,
            height = LayoutParams.WRAP_CONTENT,
            topToTop = rootView.id,
            bottomToBottom = rootView.id,
            startToStart = rootView.id,
            endToEnd = rootView.id
        )
        addConstraintSet(
            parentView = rootView,
            childViewId = bottomStripScreenRoot.id,
            width = 0.dp(),
            height = 40.dp(),
            startToStart = rootView.id,
            endToEnd = rootView.id,
            topToBottom = recyclerView.id,
            bottomToBottom = rootView.id,
            marginTop = 8.dp(),
            marginEnd = 8.dp(),
            marginStart = 8.dp()
        )
    }

    private fun setListeners() {
        viewModel.movieItemsObserver.observe(viewLifecycleOwner) { response ->
            when (response) {
                is DataState.Success -> {
                    recyclerViewAdapter.addMovieItems(response.data)

                    loadingScreenRoot.visibility = GONE
                    errorScreenRoot.visibility = GONE
                    bottomStripScreenRoot.visibility = GONE
                }

                is DataState.Loading -> {
                    if (recyclerViewAdapter.getCurrentAdapterSize() == 0) {
                        loadingScreenRoot.visibility = VISIBLE
                        recyclerView.visibility = GONE
                        bottomStripScreenRoot.visibility = GONE
                        errorScreenRoot.visibility = GONE
                    } else {
                        bottomStripScreenRoot.visibility = VISIBLE
                        bottomStripScreenProgressBar.visibility = VISIBLE
                        bottomStripScreenText.visibility = GONE
                        bottomStripScreenTryAgainButton.visibility = GONE

                        loadingScreenRoot.visibility = GONE
                        recyclerView.visibility = GONE
                        errorScreenRoot.visibility = GONE
                    }
                }

                is DataState.Error -> {
                    if (recyclerViewAdapter.getCurrentAdapterSize() == 0) {
                        errorScreenRoot.visibility = VISIBLE

                        bottomStripScreenRoot.visibility = GONE
                        loadingScreenRoot.visibility = GONE
                        recyclerView.visibility = GONE
                    } else {
                        bottomStripScreenRoot.visibility = VISIBLE
                        bottomStripScreenText.visibility = VISIBLE
                        bottomStripScreenTryAgainButton.visibility = VISIBLE
                        bottomStripScreenProgressBar.visibility = GONE

                        loadingScreenRoot.visibility = GONE
                        recyclerView.visibility = GONE
                        errorScreenRoot.visibility = GONE
                    }
                }
            }
        }

        errorScreenButton.setOnClickListener {
            viewModel.getMovieItems(currentPageNumber++)
        }

        bottomStripScreenTryAgainButton.setOnClickListener {
            viewModel.getMovieItems(currentPageNumber++)
        }
    }
}