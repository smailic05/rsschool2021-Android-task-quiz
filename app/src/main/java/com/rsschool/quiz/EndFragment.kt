package com.rsschool.quiz

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rsschool.quiz.databinding.FragmentEndBinding


class EndFragment : Fragment() {
    private var _binding: FragmentEndBinding? = null
    private val binding get() = _binding!!
    private val model: AnswerModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEndBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = compareResult()
        "Your result is ${result}%".also { binding.endText.text = it }

        binding.closeBtn.setOnClickListener{
            activity?.finish()
        }
        binding.shareBtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
                .setType("plain/text")
                .putExtra(
                    Intent.EXTRA_TEXT,"Your result is ${result}%\n\n" +
                        "Дал Дал: \n ${model.list[0]}; \n\n" +
                        "Дал Дал: \n ${model.list[1]}; \n\n" +
                        "Дал Дал: \n ${model.list[2]}; \n\n" +
                        "Дал Дал: \n ${model.list[3]}; \n\n" +
                        "Дал Дал: \n ${model.list[4]}; \n\n")
                .putExtra(Intent.EXTRA_EMAIL,"murad0660@gmail.com")
            startActivity(intent)

        }
        binding.restartBtn.setOnClickListener{
            val intent = activity?.intent
            activity?.finish()
            startActivity(intent)
        }
    }


    private fun compareResult():Int {
        var result = 0
        if (model.list[0] == resources.getString(R.string.answer1)) result += 20
        if (model.list[1] == resources.getString(R.string.answer2) )result += 20
        if (model.list[2] == resources.getString(R.string.answer3))result += 20
        if (model.list[3] == resources.getString(R.string.answer4)) result += 20
        if (model.list[4] == resources.getString(R.string.answer5)) result += 20
        return result
    }
}