package com.example.drawingapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.test.core.app.takeScreenshotNoSync
import com.example.drawingapplication.databinding.FragmentDrawViewBinding
import yuku.ambilwarna.AmbilWarnaDialog

class DrawViewFragment : Fragment() {

//    private lateinit var binding : FragmentDrawViewBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding =  FragmentDrawViewBinding.inflate(inflater, container, false)
//        val myViewModel: DrawViewModel by activityViewModels{
//            DrawViewModelFactory((requireActivity().application as FileApplication).fileRepository, requireContext())}
//
//        // Getting the draw view
//        val drawView = binding.drawingCanvas
//
//        myViewModel.bm.observe(viewLifecycleOwner) { bitmap ->
//            // Invalidate the view to trigger a redraw
//            drawView.invalidate()
//        }
//
//        binding.penButton.setOnClickListener {
//            val penSizeFragment = PenSizeFragment()
//            penSizeFragment.show(parentFragmentManager, "pen_size_fragment")
//        }
//
//        binding.colorButton.setOnClickListener {
//            //found it wasn't possible to have this open a pop up where we have the below code,
//            // so rather than have it in a fragment dialog, we have to call it here
//            // Otherwise, we end up with two dialogs popping up and you have to click once to even
//            // access the color picker (BAD UI)
//            val colorPicker = AmbilWarnaDialog(
//                this.context,
//                myViewModel.getColor(),
//                object : AmbilWarnaDialog.OnAmbilWarnaListener {
//                    override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
//                        // Action when OK is pressed (color selected)
//                        myViewModel.updateColor(color)
//                    }
//
//                    override fun onCancel(dialog: AmbilWarnaDialog) {
//                        // Needs to be here, otherwise error, but functionally serves
//                        //no purpose for our app
//                    }
//                })
//            // Show the color picker dialog
//            colorPicker.show()
//        }
//
//        binding.shapeButton.setOnClickListener {
//            val penShapeFragment = PenShapeFragment()
//            penShapeFragment.show(parentFragmentManager, "pen_shape_fragment")
//        }
//
//        return binding.root
//    }


}