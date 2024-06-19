package vn.dvn.portraitstores.domain.use_cases.transaction_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.CartUpdationRequestDto
import vn.dvn.portraitstores.data.data_source.remote.dto.TransactionRequestDto
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.domain.model.Transaction
import vn.dvn.portraitstores.domain.repository.TransactionRepository
import java.io.IOException
import javax.inject.Inject

class CheckOutUsecase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(authHeader: String, transactionRequestDto: TransactionRequestDto): Flow<Resource<Transaction>> = flow {
        try {
            emit(Resource.Loading<Transaction>())

            val result = transactionRepository.checkout( transactionRequestDto,Constants.BEARER_TOKEN + authHeader).result

            emit(Resource.Success<Transaction>(result!!))
        }catch (e: HttpException)
        {
            emit(Resource.Error<Transaction>(message = e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException)
        {
            emit(Resource.Error<Transaction>(message = e.localizedMessage?:"IO Exception"))
        }
    }
}