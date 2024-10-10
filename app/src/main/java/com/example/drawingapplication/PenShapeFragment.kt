package com.example.drawingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapplication.databinding.FragmentPenShapeBinding
import com.example.drawingapplication.databinding.FragmentPenSizeBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PenShapeFragment : DialogFragment() {

    private lateinit var binding : FragmentPenShapeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentPenShapeBinding.inflate(inflater, container, false)
        val myViewModel : DrawViewModel by activityViewModels()

        binding.circleButton.setOnClickListener(){
            myViewModel.setShape("circle")
            dismiss()
        }
        binding.squareButton.setOnClickListener(){
            myViewModel.setShape("square")
            dismiss()
        }


        return binding.root
    }
}