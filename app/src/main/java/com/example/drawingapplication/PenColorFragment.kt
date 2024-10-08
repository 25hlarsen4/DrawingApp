package com.example.drawingapplication

import android.app.AlertDialog
import android.app.Dialog
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
import yuku.ambilwarna.AmbilWarnaDialog

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

    private lateinit var binding: FragmentPenColorBinding
    private var defaultColor = 0
    private lateinit var viewModel : DrawViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Optionally, get the ViewModel here if needed
        val myViewModel: DrawViewModel by activityViewModels()
        viewModel = myViewModel

        // Return a placeholder dialog since we are showing another dialog
        return binding.root// Placeholder or empty dialog
    }

}