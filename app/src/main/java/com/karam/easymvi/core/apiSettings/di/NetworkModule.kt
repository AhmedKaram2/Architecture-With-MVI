package com.karam.easymvi.core.apiSettings.di

import com.karam.easymvi.core.apiSettings.Const
import com.karam.easymvi.features.authentication.data.apis.AuthenticationAPI
import com.mohre.smartinspectionapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().run {
            addInterceptor(HttpLoggingInterceptor().apply {

                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                readTimeout(60L, TimeUnit.SECONDS)
                connectTimeout(60L, TimeUnit.SECONDS)
                writeTimeout(60L, TimeUnit.SECONDS)
                addInterceptor(getHeaderInterceptor())

            })
            build()
        }
    }

    private fun getHeaderInterceptor(): Interceptor {

        var request: Request
        return Interceptor { chain ->
            request = if (chain.request().header(Const.NO_AUTH_HEADER_KEY) == null) {

                chain.request().newBuilder()
                    .header(
                        Const.Authorization, Const.TokenKey + "// Token you get from the user"
                    )
                    .build()

            } else {

                chain.request().newBuilder()
                    .build()
            }

            chain.proceed(request)
        }

    }


    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().run {
            baseUrl(BuildConfig.END_POINT)
            client(httpClient)
            addConverterFactory(GsonConverterFactory.create())
            build()
        }
    }

    @Provides
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationAPI {
        return retrofit.create(AuthenticationAPI::class.java)
    }
}