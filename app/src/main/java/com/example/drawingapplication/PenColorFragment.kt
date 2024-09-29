package com.example.drawingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapplication.databinding.FragmentPenColorBinding
import com.example.drawingapplication.databinding.FragmentPenSizeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PenColorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PenColorFragment : DialogFragment() {

    private lateinit var binding : FragmentPenColorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentPenColorBinding.inflate(inflater, container, false)
        val myViewModel : DrawViewModel by activityViewModels()

        binding.colorRed.setOnClickListener {
            myViewModel.updateColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
        }

        binding.colorOrange.setOnClickListener {
            myViewModel.updateColor(ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark))
        }

        binding.colorYellow.setOnClickListener {
            myViewModel.updateColor(ContextCompat.getColor(requireContext(), android.R.color.holo_orange_light))
        }

        binding.colorGreen.setOnClickListener {
            myViewModel.updateColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark))
        }

        binding.colorBlue.setOnClickListener {
            myViewModel.updateColor(ContextCompat.getColor(requireContext(), android.R.color.holo_blue_dark))
        }

        binding.colorPurple.setOnClickListener {
            myViewModel.updateColor(ContextCompat.getColor(requireContext(), android.R.color.holo_purple))
        }

        return binding.root
    }
}