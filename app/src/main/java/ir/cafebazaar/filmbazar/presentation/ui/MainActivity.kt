package ir.cafebazaar.filmbazar.presentation.ui

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ir.cafebazaar.filmbazar.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = FrameLayout(this).apply {
            id = R.id.main_activity_root_view
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(rootView)

        this.supportFragmentManager.beginTransaction()
            .replace(rootView.id, MovieListFragment() as Fragment).commit()

    }
}
