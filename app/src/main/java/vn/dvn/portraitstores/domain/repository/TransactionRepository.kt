package vn.dvn.portraitstores.domain.repository

import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.ProductDto
import vn.dvn.portraitstores.data.data_source.remote.dto.TransactionRequestDto
import vn.dvn.portraitstores.domain.model.Orders
import vn.dvn.portraitstores.domain.model.Transaction


interface TransactionRepository {

    suspend fun getAll(token: String): ApiResponseDto<List<Transaction>>;

    suspend fun checkout(transactionRequestDto: TransactionRequestDto ,token: String): ApiResponseDto<Transaction>;

    suspend fun getTransaction(status: String, token: String): ApiResponseDto<Map<String, List<Orders>>>;

    suspend fun updateTransaction(transaction: Transaction, token: String): ApiResponseDto<Transaction>;
}