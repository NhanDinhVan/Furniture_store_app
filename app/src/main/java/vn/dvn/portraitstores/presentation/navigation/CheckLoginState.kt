package vn.dvn.portraitstores.presentation.navigation

import vn.dvn.portraitstores.domain.model.User

data class CheckLoginState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)