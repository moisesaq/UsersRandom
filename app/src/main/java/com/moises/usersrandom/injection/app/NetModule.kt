package com.moises.usersrandom.injection.app

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.moises.usersrandom.service.APIServices
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@Singleton
object NetModule {

    private const val BASE_URL = "https://randomuser.me"

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideAPIServices(retrofit: Retrofit): APIServices {
        return retrofit.create(APIServices::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory,
                                 okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}