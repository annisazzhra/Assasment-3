package org.d3if3042.e_commerce.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import org.d3if3042.e_commerce.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Gallery") }
            )
        }
    ) { padding ->
        when (uiState) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                val products = (uiState as UiState.Success).products
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.padding(padding)
                ) {
                    items(products) { product ->
                        ProductItem(product = product)
                    }
                }
            }
            is UiState.Error -> {
                val message = (uiState as UiState.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = message)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Box(modifier = Modifier.padding(8.dp)) {
        Column {
            Image(
                painter = rememberImagePainter(product.image),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Text(text = product.title, style = MaterialTheme.typography.titleSmall)
            Text(text = "$${product.price}", style = MaterialTheme.typography.titleSmall)
        }
    }
}
