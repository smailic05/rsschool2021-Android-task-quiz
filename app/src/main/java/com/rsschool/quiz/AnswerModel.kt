package com.rsschool.quiz

import androidx.lifecycle.ViewModel

class AnswerModel:ViewModel() {

    val list= mutableListOf("","","","","")
    val idlist= arrayOfNulls<Int>(5)
    fun select(item: String,i:Int,checked:Int) {
        list[i] = item
        idlist[i]=checked
    }
}