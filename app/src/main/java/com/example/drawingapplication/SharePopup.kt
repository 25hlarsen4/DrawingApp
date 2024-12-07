package com.example.drawingapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapplication.databinding.FragmentSharePopUpBinding

class SharePopup : DialogFragment() {

    private lateinit var binding: FragmentSharePopUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSharePopUpBinding.inflate(inflater, container, false)


        binding.shareConfirmButton.setOnClickListener {
            dismiss()
        }


        return binding.root
    }
}