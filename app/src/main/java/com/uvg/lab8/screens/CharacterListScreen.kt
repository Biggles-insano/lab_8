package com.uvg.lab8.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.uvg.lab8.data.CharacterDb
import com.uvg.lab8.model.Character

@Composable
fun CharacterListScreen(navController: NavController) {
    val characters: List<Character> = CharacterDb().getAllCharacters() // Especifica el tipo de lista

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Characters") })
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            items(characters) { character ->
                CharacterRow(character = character) {
                    navController.navigate("character_detail/${character.id}")
                }
            }
        }
    }
}

@Composable
fun CharacterRow(character: Character, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(character.image),
            contentDescription = character.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = character.name, style = MaterialTheme.typography.h6)
            Text(text = "${character.species} - ${character.status}")
        }
    }
}
