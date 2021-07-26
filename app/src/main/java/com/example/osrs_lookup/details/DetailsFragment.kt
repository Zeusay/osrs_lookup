package com.example.osrs_lookup.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.osrs_lookup.R
import com.example.osrs_lookup.databinding.DetailsFragmentBinding
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {
    private val TAG = "DetailsFragment"
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: DetailsFragmentBinding
    private var gameMode: String = "Standard"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.details_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.searchPlayerBtn.setOnClickListener { onPlayerSubmit() }

    }

    private fun isAlphaNumeric(chars: String): Boolean {
        return chars.all { it.isLetterOrDigit() }
    }

    private fun onPlayerSubmit() {

        val playerName = binding.playerNameInput.text.toString()
        if (playerName != "" && isAlphaNumeric(playerName)) {
            Log.d(TAG, "Valid Player Name")
            gameMode = when {
                binding.gmHardcore.isChecked -> {
                    "Hardcore"
                }
                binding.gmIron.isChecked -> {
                    "Iron"
                }
                binding.gmUltimate.isChecked -> {
                    "Ultimate"
                }
                else -> {
                    "Standard"
                }
            }
            Log.d(TAG, "playerName found was: $playerName")
            Log.d(TAG, "playerMode found was: $gameMode")
            val action = DetailsFragmentDirections.actionDetailsToResults()
            NavHostFragment.findNavController(this).navigate(action)

        } else {
            Log.d(TAG, "Invalid Player Name")
            Snackbar.make(
                binding.textView,
                "Invalid Name... Alphanumeric values valid only",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }
}
