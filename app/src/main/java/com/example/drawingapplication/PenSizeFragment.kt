package com.example.drawingapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapplication.databinding.FragmentPenSizeBinding


class PenSizeFragment : DialogFragment() {

    private lateinit var binding : FragmentPenSizeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentPenSizeBinding.inflate(inflater, container, false)
        val myViewModel : DrawViewModel by activityViewModels()

        binding.penSizeSeekbar.progress = myViewModel.strokeSize

        binding.penSizeSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update pen size in your drawing view
                myViewModel.updatePenSize(progress)
            }

            //the below aren't needed by us but required to have overridden
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        return binding.root
    }
}