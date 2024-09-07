package com.uvg.lab8.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.uvg.lab8.data.CharacterDb
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun CharacterDetailScreen(navController: NavController, characterId: Int) {
    val character = CharacterDb().getCharacterById(characterId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = character.name, style = MaterialTheme.typography.h4)
            Text(text = "Especie: ${character.species}")
            Text(text = "Estado: ${character.status}")
            Text(text = "Género: ${character.gender}")
        }
    }
}
