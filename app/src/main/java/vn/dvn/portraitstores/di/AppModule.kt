package vn.dvn.portraitstores.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.data.data_source.local.UserDao
import vn.dvn.portraitstores.data.data_source.local.UserDatabase
import vn.dvn.portraitstores.data.data_source.remote.api.AuthenApi
import vn.dvn.portraitstores.data.data_source.remote.api.BannerApi
import vn.dvn.portraitstores.data.data_source.remote.api.BrandApi
import vn.dvn.portraitstores.data.data_source.remote.api.CartApi
import vn.dvn.portraitstores.data.data_source.remote.api.ProductApi
import vn.dvn.portraitstores.data.data_source.remote.api.TransactionApi
import vn.dvn.portraitstores.data.data_source.remote.api.UserApi
import vn.dvn.portraitstores.data.repository.BannerRepositoryImpl
import vn.dvn.portraitstores.data.repository.BrandRepositoryImpl
import vn.dvn.portraitstores.data.repository.CartRepositoryImpl
import vn.dvn.portraitstores.data.repository.ProductRepositoryImpl
import vn.dvn.portraitstores.data.repository.TransactionRepositoryImpl
import vn.dvn.portraitstores.data.repository.UserRepositoryImpl
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.repository.BannerRepository
import vn.dvn.portraitstores.domain.repository.BrandRepository
import vn.dvn.portraitstores.domain.repository.CartRepository
import vn.dvn.portraitstores.domain.repository.ProductRepository
import vn.dvn.portraitstores.domain.repository.TransactionRepository
import vn.dvn.portraitstores.domain.repository.UserRepository
import vn.dvn.portraitstores.domain.use_cases.banner_usecase.BannerUseCase
import vn.dvn.portraitstores.domain.use_cases.banner_usecase.GetAllBannerUseCase
import vn.dvn.portraitstores.domain.use_cases.brand_usecase.BrandUseCase
import vn.dvn.portraitstores.domain.use_cases.brand_usecase.GetAllBrandUseCase
import vn.dvn.portraitstores.domain.use_cases.cart_usecase.AddToCartUseCase
import vn.dvn.portraitstores.domain.use_cases.cart_usecase.CartUseCase
import vn.dvn.portraitstores.domain.use_cases.cart_usecase.GetAllCartUseCase
import vn.dvn.portraitstores.domain.use_cases.cart_usecase.UpdateCartUseCase
import vn.dvn.portraitstores.domain.use_cases.product_usecase.GetAllProductUseCase
import vn.dvn.portraitstores.domain.use_cases.product_usecase.GetProductByCategoryId
import vn.dvn.portraitstores.domain.use_cases.product_usecase.ProductUsecase
import vn.dvn.portraitstores.domain.use_cases.transaction_usecase.CheckOutUsecase
import vn.dvn.portraitstores.domain.use_cases.transaction_usecase.TransactionUsecase
import vn.dvn.portraitstores.domain.use_cases.user_usecase.AuthenUsecase
import vn.dvn.portraitstores.domain.use_cases.user_usecase.CheckLoginUseCase
import vn.dvn.portraitstores.domain.use_cases.user_usecase.RegisterUsecase
import vn.dvn.portraitstores.domain.use_cases.user_usecase.UserUsecase
import vn.dvn.portraitstores.domain.use_cases.user_usecase.VerifyCodeUsecase
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
    fun provideAuthenApi(retrofit: Retrofit): AuthenApi = retrofit.create(AuthenApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideCheckLoginUsecase(userRepository: UserRepository): CheckLoginUseCase {
        return CheckLoginUseCase(userRepository)
    }
    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUsecase {
        return UserUsecase(
            authenUsecase = AuthenUsecase(repository),
            checkLoginUseCase = CheckLoginUseCase(repository),
            verifyCodeUsecase = VerifyCodeUsecase(repository),
            registerUsecase = RegisterUsecase(repository),
        )
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideProductRepository(productApi: ProductApi): ProductRepository {
        return ProductRepositoryImpl(productApi)
    }

    @Provides
    @Singleton
    fun provideProductUseCases(repository: ProductRepository): ProductUsecase {
        return ProductUsecase(
            getAllProductUseCase = GetAllProductUseCase(repository),
            getProductByCategoryId = GetProductByCategoryId(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCartApi(retrofit: Retrofit): CartApi = retrofit.create(CartApi::class.java)

    @Provides
    @Singleton
    fun provideCartRepository(cartApi: CartApi): CartRepository {
        return CartRepositoryImpl(cartApi)
    }

    @Provides
    @Singleton
    fun provideCartUseCases(repository: CartRepository): CartUseCase {
        return CartUseCase(
            getAllCartUseCase = GetAllCartUseCase(repository),
            addToCartUseCase = AddToCartUseCase(repository),
            updateCartUseCase = UpdateCartUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideBrandApi(retrofit: Retrofit): BrandApi = retrofit.create(BrandApi::class.java)

    @Provides
    @Singleton
    fun provideBrandRepository(brandApi: BrandApi): BrandRepository {
        return BrandRepositoryImpl(brandApi)
    }

    @Provides
    @Singleton
    fun provideBrandUseCases(repository: BrandRepository): BrandUseCase {
        return BrandUseCase(
            getAllBrandUseCase = GetAllBrandUseCase(repository)
        )
    }
    @Provides
    @Singleton
    fun provideBannerApi(retrofit: Retrofit): BannerApi = retrofit.create(BannerApi::class.java)

    @Provides
    @Singleton
    fun provideBannerRepository(bannerApi: BannerApi): BannerRepository {
        return BannerRepositoryImpl(bannerApi)
    }

    @Provides
    @Singleton
    fun provideBannerUseCases(repository: BannerRepository): BannerUseCase {
        return BannerUseCase(
            getAllBannerUseCase = GetAllBannerUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideUserDatabase(application: Application) = Room.databaseBuilder(
        application,
        UserDatabase::class.java,
        Constants.USER_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }


    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao,
                              authenApi: AuthenApi,
                              userApi: UserApi): UserRepository = UserRepositoryImpl(authenApi,userApi,userDao)


    @Provides
    @Singleton
    fun provideTransactionApi(retrofit: Retrofit): TransactionApi = retrofit.create(TransactionApi::class.java)

    @Provides
    @Singleton
    fun provideTransactionRepository(transactionApi: TransactionApi): TransactionRepository {
        return TransactionRepositoryImpl(transactionApi)
    }

    @Provides
    @Singleton
    fun provideTransactionUseCases(repository: TransactionRepository): TransactionUsecase {
        return TransactionUsecase(
            checkOutUsecase = CheckOutUsecase(repository)
        )
    }

}