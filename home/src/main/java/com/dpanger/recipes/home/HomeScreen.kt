package com.dpanger.recipes.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dpanger.recipes.data.Recipe
import com.dpanger.recipes.themes.RecipesTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Recipes(
        modifier = modifier,
        recipes = emptyList<Recipe>().toImmutableList()
    )
}

@Composable
@Preview
private fun HomeScreenPreview() {
    RecipesTheme {
        HomeScreen(modifier = Modifier.fillMaxSize())
    }
}