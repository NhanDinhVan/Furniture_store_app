package vn.dvn.portraitstores.data.repository

import vn.dvn.portraitstores.data.data_source.remote.api.ProductApi
import vn.dvn.portraitstores.data.data_source.remote.api.TransactionApi
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.TransactionRequestDto
import vn.dvn.portraitstores.domain.model.Orders
import vn.dvn.portraitstores.domain.model.Transaction
import vn.dvn.portraitstores.domain.repository.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl  @Inject constructor(
    private val transactionApi: TransactionApi
)  : TransactionRepository {

    override suspend fun getAll(token: String): ApiResponseDto<List<Transaction>> {
        return transactionApi.getAllTransactions(token)
    }

    override suspend fun checkout(
        transactionRequestDto: TransactionRequestDto,
        token: String
    ): ApiResponseDto<Transaction> {
        return transactionApi.checkout(token,transactionRequestDto)
    }

    override suspend fun getTransaction(
        status: String,
        token: String
    ): ApiResponseDto<Map<String, List<Orders>>> {
        return transactionApi.getTransactions(status, token)
    }

    override suspend fun updateTransaction(
        transaction: Transaction,
        token: String
    ): ApiResponseDto<Transaction> {
        return transactionApi.updateTransactions(token, transaction.transactionId)
    }
}