package com.ahmed.mvvmexample.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahmed.mvvmexample.viewmodel.PetsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmed.mvvmexample.data.Cat
import org.koin.androidx.compose.koinViewModel


@Composable
fun PetList(modifier: Modifier,
            onPetClicked: (Cat)->Unit,
            pets: List<Cat>) {
    val petsViewModel: PetsViewModel = koinViewModel()
    val petsUiState: PetsUIState by petsViewModel.petsUIState.collectAsStateWithLifecycle()

    Column (
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = petsUiState.isLoading
        ) {
            CircularProgressIndicator()
        }
        AnimatedVisibility(
            visible = petsUiState.pets.isNotEmpty()
        ) {
            LazyColumn {
                items(petsUiState.pets) { pet ->
                    PetListItem(cat = pet,
                        onPetClicked = onPetClicked)
                }
            }
        }
        AnimatedVisibility(
            visible = petsUiState.error != null
        ) {
            Text(text = petsUiState.error ?: "")
        }
    }
}