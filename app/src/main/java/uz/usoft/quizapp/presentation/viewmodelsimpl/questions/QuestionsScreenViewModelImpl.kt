package uz.usoft.quizapp.presentation.viewmodelsimpl.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.usoft.quizapp.data.domain.repository.QuestionsRepository
import uz.usoft.quizapp.data.response.level.LevelResponse
import uz.usoft.quizapp.presentation.viewmodels.questions.QuestionsScreenViewModel
import uz.usoft.quizapp.utils.eventValueFlow
import uz.usoft.quizapp.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class QuestionsScreenViewModelImpl @Inject constructor(private val repository: QuestionsRepository) : ViewModel(),QuestionsScreenViewModel{
    override val errorFlow = eventValueFlow<String>()
    override val successFlow = eventValueFlow<LevelResponse>()
    override val progressFlow = eventValueFlow<Boolean>()

    override fun getQuestions(id:String) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        repository.getLevel(id).onEach {
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

}