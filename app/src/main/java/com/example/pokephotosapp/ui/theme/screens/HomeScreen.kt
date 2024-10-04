package com.example.pokephotosapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokephotosapp.R
import com.example.pokephotosapp.model.PokePhoto
import com.example.pokephotosapp.viewmodel.PokeUiState


@Composable


fun HomeScreen(
    pokeUiState: PokeUiState,
    modifier: Modifier= Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    when( pokeUiState){
        is PokeUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is PokeUiState.Success -> PhotosGridScreen(photos = pokeUiState.photos )
        is PokeUiState.Error -> ErrorScreen(modifier =  modifier.fillMaxSize())


        else -> {}
    }
}
@Composable
fun PhotosGridScreen(
    photos:List<PokePhoto>,
    modifier: Modifier= Modifier,
    contentPadding: PaddingValues= PaddingValues(0.dp)
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier= modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding
    ) {
        items(
            items = photos,
            key= {photo -> photo.id}


        ){
                photo -> PokePhotoCard(photo = photo,
            modifier = modifier
                .padding(4.dp)
                .fillMaxSize()
                .aspectRatio(1.5f)
        )


        }


    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.loader2),
            contentDescription = "Loading")
    }


}
@Composable
fun PokePhotoCard(
    photo: PokePhoto,
    modifier: Modifier
){


    AsyncImage(model = ImageRequest.Builder(
        context = LocalContext.current
    ).data(photo.url)
        .crossfade(true)
        .build()
        , contentDescription = stringResource(R.string.poke_image),
        modifier = modifier,
        error= painterResource(id = R.drawable.error_4042),
        placeholder = painterResource(id = R.drawable.cargando2),
        contentScale = ContentScale.Crop




    )
}


@Composable
fun ResultScreen(photos:String, modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Text(text = photos )
    }
}
@Composable
fun ErrorScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.error_load2)
            , contentDescription = "Error Loading" )
        Text(text = stringResource(R.string.problem_with_connection))
    }
}
/*
@Preview
@Composable
fun HomeScreenPreview(){
   Surface(
       modifier = Modifier.fillMaxSize()
   ) {
       HomeScreen(catUiState = CatUiState.Success("photos"))
    }


}
*/
