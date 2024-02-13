package ir.cafebazaar.filmbazar.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.MovieItem
import ir.cafebazaar.filmbazar.usecase.GetMovieItemList
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(val getMovieItemList: GetMovieItemList) : ViewModel() {

    private val _movieItemsObserver = MutableLiveData<DataState<List<MovieItem>>>()
    val movieItemsObserver: LiveData<DataState<List<MovieItem>>> = _movieItemsObserver

    fun getMovieItems(pageNumber: Int) {
        _movieItemsObserver.value = DataState.Loading()
        getMovieItemList(pageNumber).onEach {
            _movieItemsObserver.value = it
        }.launchIn(viewModelScope)
    }
}