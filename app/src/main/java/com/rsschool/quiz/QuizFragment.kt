package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
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

        if (args.myArg%2!=0){
            activity?.setTheme(R.style.Theme_Quiz_First)
            activity?.window?.statusBarColor = resources.getColor(R.color.deep_orange_100_dark)
        }
        else
        {
            activity?.setTheme(R.style.Theme_Quiz_Second)
            activity?.window?.statusBarColor = resources.getColor(R.color.yellow_100_dark)
        }
        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val amount = args.myArg
        val host =
            activity?.supportFragmentManager?.findFragmentById(R.id.container) as NavHostFragment
        val navController = host.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.toolbar.title = "Question ${amount+1}"

        if (model.list[amount]!="")
        {
            model.idlist[amount]?.let { binding.radioGroup.check(it) }
            binding.nextButton.isEnabled=true
        }
        if (amount!=0)
            binding.previousButton.isEnabled=true

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val rb = view.findViewById(checkedId) as RadioButton
            model.select(rb.text.toString(),amount,checkedId)
            binding.nextButton.isEnabled=true
        }

        binding.previousButton.setOnClickListener { actInt?.backPressed() }

        when (amount)
        {
            0-> navTo(view, listOf("Дал Дал","a","Не ушёл","Отошёл","Перешёл","Кринж"),R.id.one_to_two)

            1-> navTo(view, listOf("Дал Дал","a","b","c","d","e"),R.id.two_to_three)

            2->navTo(view, listOf("Дал Дал","a","g","h","i","g"),R.id.three_to_four)

            3->navTo(view, listOf("Дал Дал","a","l","m","n","o"),R.id.to_end)

            4->navTo(view, listOf("Дал Дал","a","l","m","n","o"),R.id.to_end)


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