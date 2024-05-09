package vn.dvn.portraitstores.presentation.home_screen

import vn.dvn.portraitstores.domain.model.Products

sealed interface HomeUiState {
    data class Success(val productList: List<Products>): HomeUiState
    object Error: HomeUiState

    object Loading: HomeUiState
}