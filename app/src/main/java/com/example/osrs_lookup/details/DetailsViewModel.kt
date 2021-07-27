package com.example.osrs_lookup.details

import android.util.Log
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private lateinit var _playerName: String
    private lateinit var _gameMode: String

    fun detailsPass(playerName: String, gameMode: String) {
        _playerName = playerName
        _gameMode = gameMode
        Log.d("DetailsViewModel","Values passed to VM as $_playerName, $_gameMode")
    }


}