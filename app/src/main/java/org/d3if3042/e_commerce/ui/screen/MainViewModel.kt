package org.d3if3042.e_commerce.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.d3if3042.e_commerce.model.Product
import org.d3if3042.e_commerce.network.ProductApi

sealed class UiState {
    object Loading : UiState()
    data class Success(val products: List<Product>) : UiState()
    data class Error(val message: String) : UiState()
}

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> get() = _uiState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val productList = ProductApi.retrofitService.getProducts()
                _uiState.value = UiState.Success(productList)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load products")
            }
        }
    }
}
