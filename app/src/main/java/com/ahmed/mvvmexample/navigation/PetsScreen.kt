package com.ahmed.mvvmexample.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahmed.mvvmexample.data.Cat
import com.ahmed.mvvmexample.viewmodel.PetsViewModel
import com.ahmed.mvvmexample.views.PetList
import com.ahmed.mvvmexample.views.PetListAndDetails
import com.ahmed.mvvmexample.views.PetsUIState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetsScreen(onPetClicked: (Cat) -> Unit,
               contentType: ContentType) {
    val petsViewModel = koinViewModel<PetsViewModel>()
    val petsUIState by petsViewModel.petsUIState.collectAsStateWithLifecycle()
    PetsScreenContent(
        modifier = Modifier.fillMaxSize(),
        onPetClicked = onPetClicked,
        contentType = contentType,
        petsUIState = petsUIState
    )
}

@Composable
fun PetsScreenContent(
    modifier: Modifier,
    onPetClicked: (Cat) -> Unit,
    contentType: ContentType,
    petsUIState: PetsUIState
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = petsUIState.isLoading
        ) {
            CircularProgressIndicator()
        }
        AnimatedVisibility(
            visible = petsUIState.pets.isNotEmpty()
        ) {
            if (contentType == ContentType.List) {
                PetList(
                    onPetClicked = onPetClicked,
                    pets = petsUIState.pets,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else {
                PetListAndDetails(
                    pets = petsUIState.pets
                )
            }
        }
        AnimatedVisibility(
            visible = petsUIState.error != null
        ) {
            Text(text = petsUIState.error ?: "")
        }
    }
}