package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private val model: AnswerModel by activityViewModels()
    private var actInt:ActivityInterface?=null
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val args: QuizFragmentArgs by navArgs()



    override fun onAttach(context: Context) {
        super.onAttach(context)
        actInt  =context as ActivityInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val amount = args.myArg

        if (model.list[amount]!="")
            model.idlist[amount]?.let { binding.radioGroup.check(it) }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val rb = view.findViewById(checkedId) as RadioButton
            model.select(rb.text.toString(),amount,checkedId)
        }
        binding.previousButton.setOnClickListener { actInt?.BackPressed() }

        when (amount)
        {
            0-> navTo(view, listOf("Дал Дал","Ушёл","Не ушёл","Отошёл","Перешёл","Кринж"),R.id.one_to_two)

            1-> navTo(view, listOf("Дал Дал","a","b","c","d","e"),R.id.two_to_three)

            2->navTo(view, listOf("Дал Дал","f","g","h","i","g"),R.id.three_to_four)

            3->navTo(view, listOf("Дал Дал","k","l","m","n","o"),R.id.to_end)
        }


    }

    private fun navTo(view: View, list:List<String>, route:Int) {
        binding.question.text = list[0]
        binding.optionOne.text = list[1]
        binding.optionTwo.text = list[2]
        binding.optionThree.text = list[3]
        binding.optionFour.text = list[4]
        binding.optionFive.text = list[5]
        binding.nextButton.setOnClickListener {
            view.findNavController().navigate(route)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}