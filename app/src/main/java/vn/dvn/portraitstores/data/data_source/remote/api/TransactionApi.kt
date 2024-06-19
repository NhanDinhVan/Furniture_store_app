package vn.dvn.portraitstores.data.data_source.remote.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.TransactionRequestDto
import vn.dvn.portraitstores.domain.model.Orders
import vn.dvn.portraitstores.domain.model.Transaction

interface TransactionApi {

    @POST("v1/transactions")
    suspend fun checkout(@Header("Authorization") authHeader: String,
                         @Body request: TransactionRequestDto
                         ):ApiResponseDto<Transaction>

    @GET("v1/transactions/{status}")
    suspend fun getTransactions(@Path("status") status: String,
                          @Header("Authorization") authHeader: String): ApiResponseDto<Map<String, List<Orders>>>

    @GET("v1/transactions")
    suspend fun getAllTransactions(@Header("Authorization") authHeader: String):ApiResponseDto<List<Transaction>>

    @PUT("v1/transactions/{transactionId}")
    suspend fun updateTransactions(@Header("Authorization" )authHeader: String,
            @Path("transactionId") transactionId: String ):ApiResponseDto<Transaction>
}