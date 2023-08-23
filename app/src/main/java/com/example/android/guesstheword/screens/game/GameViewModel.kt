package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel(){


    // The current word
    //encapsulate
    private val _word= MutableLiveData<String>()
   //  var word = MutableLiveData<String>()

    //only readable
    val word:LiveData<String>
       get () =_word

    // The current score
    // var only for one time
   //  var score = MutableLiveData<Int>()

    private val _score=MutableLiveData<Int>()
    val score:LiveData<Int>
        get()=_score

    // Event which triggers the end of the game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {

            if (wordList.isEmpty()) {
                onGameFinish()
            } else {
                //Select and remove a _word from the list
                _word.value = wordList.removeAt(0)
            }


    }
    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
    init {
        _word.value=""
        _score.value=0
        resetList()
        nextWord()
        Log.i("GameViewMdel","GameViewModel Created")
    }


   fun onSkip() {
    //    score--  change to below code
       _score.value= (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        //score++
        _score.value=(score.value)?.plus(1)
        nextWord()
    }

    /** Method for the game completed event **/
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    /** Method for the game completed event **/

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}