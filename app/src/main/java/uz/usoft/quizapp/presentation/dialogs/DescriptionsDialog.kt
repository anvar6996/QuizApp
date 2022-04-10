package uz.usoft.quizapp.presentation.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers

@AndroidEntryPoint
class DescriptionsDialog : DialogFragment(R.layout.dialog_description) {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val value = it.getSerializable("value") as QuestionAnswers
            view.findViewById<TextView>(R.id.question_text).text = value.questionData.titleEn
            view.findViewById<TextView>(R.id.description_text).text =
                value.questionData.titleRu

        }
//        var
//        view.findViewById<TextView>(R.id.question_text).text=
//        view.findViewById<TextView>(uz.softkomunal.a24seven.R.id.no)
//            .setOnClickListener {
//                no?.invoke(Unit)
//            }
    }


}