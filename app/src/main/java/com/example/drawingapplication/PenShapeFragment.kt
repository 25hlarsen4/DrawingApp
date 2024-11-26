package com.example.drawingapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapplication.databinding.FragmentPenShapeBinding

class PenShapeFragment : DialogFragment() {

    private lateinit var binding : FragmentPenShapeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentPenShapeBinding.inflate(inflater, container, false)
        val myViewModel: DrawViewModel by activityViewModels{
            DrawViewModelFactory((requireActivity().application as FileApplication).fileRepository, requireContext())}

        //sets the shape to be circle
        binding.circleButton.setOnClickListener(){
            myViewModel.setShape("circle")
            dismiss()
        }
        //sets the shape to be square/line
        binding.squareButton.setOnClickListener(){
            myViewModel.setShape("square")
            dismiss()
        }


        return binding.root
    }
}