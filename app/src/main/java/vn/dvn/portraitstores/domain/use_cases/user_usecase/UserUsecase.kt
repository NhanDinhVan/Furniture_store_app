package vn.dvn.portraitstores.domain.use_cases.user_usecase

data class UserUsecase(
    val authenUsecase: AuthenUsecase,
    val checkLoginUseCase: CheckLoginUseCase,
    val registerUsecase: RegisterUsecase,
    val verifyCodeUsecase: VerifyCodeUsecase
)
