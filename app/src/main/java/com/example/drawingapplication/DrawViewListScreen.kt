package com.example.drawingapplication
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DrawViewListScreen(
    modifier: Modifier = Modifier,
    DrawViewListViewModel: DrawViewModel = viewModel()
){
    Column(modifier = modifier) {

        DrawViewList(
            list = DrawViewListViewModel.DrawViewObjects,
            onSelectedTask = { DrawViewObject ->
                DrawViewListViewModel.select(DrawViewObject)
            }
        )
    }


}