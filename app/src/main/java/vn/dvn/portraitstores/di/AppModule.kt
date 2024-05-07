package vn.dvn.portraitstores.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.data.data_source.remote.api.ProductApi
import vn.dvn.portraitstores.data.repository.ProductRepositoryImpl
import vn.dvn.portraitstores.domain.repository.ProductRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideProductRepository(productApi: ProductApi): ProductRepository {
        return ProductRepositoryImpl(productApi)
    }
}