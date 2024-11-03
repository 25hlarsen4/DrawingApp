package com.example.drawingapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.drawingapplication.databinding.FragmentSaveViewBinding

class SaveFragment : DialogFragment() {

    private lateinit var binding: FragmentSaveViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveViewBinding.inflate(inflater, container, false)
        val myViewModel: DrawViewModel by activityViewModels {
            DrawViewModelFactory((requireActivity().application as FileApplication).fileRepository, requireContext())
        }

        binding.saveBackButton.setOnClickListener {
            dismiss()
        }

        binding.saveConfirmButton.setOnClickListener {
            var inputText = binding.saveFileName.text.toString()
            inputText = inputText.replace(".png", "")

            var fileExists = false
            for (data in myViewModel.allFiles.value!!) {
                if (data.filename == "$inputText.png")
                {
                    fileExists = true
                }
            }
            if (!fileExists) {
                binding.saveText.text = "File has been saved"
                myViewModel.saveFile(myViewModel.bitmap.value!!, requireContext(), inputText)
            }
            else {
                binding.saveText.text = "File already exists"
            }
        }


        return binding.root
    }
    fun setText(param: String) {
        binding.saveText.text = param
    }
}