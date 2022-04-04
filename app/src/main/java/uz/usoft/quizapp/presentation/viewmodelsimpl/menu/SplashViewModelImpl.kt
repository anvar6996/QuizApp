package uz.usoft.quizapp.presentation.viewmodelsimpl.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor() : ViewModel() {
    private var _liveData = MutableLiveData<Unit>()
    val liveData: LiveData<Unit> get() = _liveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            _liveData.postValue(Unit)
        }
    }

}