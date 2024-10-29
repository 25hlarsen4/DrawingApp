package com.example.drawingapplication
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

//DrawViewModel by viewModels { DrawViewModelFactory(repository) }
@Composable
fun DrawViewListScreen(
    modifier: Modifier = Modifier,
    drawViewListViewModel: DrawViewModel
){
    Column(modifier = modifier) {

        DrawViewList(
            list = drawViewListViewModel.DrawViewObjects,
            onSelectedTask = { DrawViewObject ->
                drawViewListViewModel.select(DrawViewObject)
            }
        )
    }


}