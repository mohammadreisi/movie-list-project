package ir.cafebazaar.filmbazar.presentation.ui

import android.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import ir.cafebazaar.filmbazar.R

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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

        val fragmentManager = this.fragmentManager
//        val fragmentManager = FragmentActivity().supportFragmentManager
        fragmentManager.beginTransaction().replace(rootView.id, MovieListFragment() as Fragment).commit()
    }
}
