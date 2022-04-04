package uz.usoft.quizapp.presentation.viewmodelsimpl.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.usoft.quizapp.data.domain.usecase.CategoryScreenUseCase
import uz.usoft.quizapp.data.response.category.CategoryResponse
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import uz.usoft.quizapp.presentation.viewmodels.questions.CategoryScreenViewModel
import uz.usoft.quizapp.utils.eventValueFlow
import uz.usoft.quizapp.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class CategoryScreenViewModelImpl @Inject constructor(private val useCaseImpl: CategoryScreenUseCase) :
    ViewModel(),
    CategoryScreenViewModel {
    override val errorFlow = eventValueFlow<String>()
    override val successFlow = eventValueFlow<List<QuestionAnswers>>()
    override val progressFlow = eventValueFlow<Boolean>()

    override fun getQuestions(id: String) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCaseImpl.getLevel(id).onEach {
            it.onSuccess {
                progressFlow.emit(false)
                successFlow.emit(it)
            }
            it.onFailure { throwable ->
                progressFlow.emit(false)
                errorFlow.emit(throwable.message.toString())
            }
        }.launchIn(viewModelScope)
    }

    override fun getPlay() {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCaseImpl.getPlay().onEach { result ->
            result.onSuccess {
                progressFlow.emit(false)
                successFlow.emit(it)
            }
            result.onFailure { throwable ->
                progressFlow.emit(false)
                errorFlow.emit(throwable.message.toString())
            }
        }.launchIn(viewModelScope)
    }


//    fun reformatResponse(array: CategoryResponse): CategoryResponse {
//        var count = 5
//        var helper = 0
//        var counter = 5
//        val list = ArrayList<Data>()
//        for (i in 0 until array.data.size * 2) {
//            if (count == 0) {
//                count = 5
//                counter--
//            }
//            if (counter > 0 && helper < array.data.size) {
//                val value = array.data[helper]
//                list.add(getValue(value))
//                helper++
//            } else {
//                list.add(getValueNull())
//            }
//            count--
//        }
//        return CategoryResponse(list)
//    }


}