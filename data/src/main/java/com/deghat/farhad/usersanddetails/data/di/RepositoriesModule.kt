package com.deghat.farhad.usersanddetails.data.di

import com.deghat.farhad.usersanddetails.data.mapper.LoginResponseEntityMapper
import com.deghat.farhad.usersanddetails.data.remote.Remote
import com.deghat.farhad.usersanddetails.data.remote.ServiceGenerator
import com.deghat.farhad.usersanddetails.data.repository.LoginRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
class RepositoriesModule {

    @Provides
    fun loginRepositoryImpl(
        remote: Remote,
        loginResponseEntityMapper: LoginResponseEntityMapper
    ): LoginRepositoryImpl {
        return LoginRepositoryImpl(remote, loginResponseEntityMapper)
    }

    @Provides
    fun loginResponseEntityMapper(): LoginResponseEntityMapper {
        return LoginResponseEntityMapper()
    }

    @Provides
    fun remote(serviceGenerator: ServiceGenerator): Remote {
        return Remote(serviceGenerator)
    }

    @Provides
    fun serviceGenerator(retrofit: Retrofit): ServiceGenerator {
        return ServiceGenerator(retrofit)
    }

    @Provides
    fun retrofit(
        moshiConverterFactory: MoshiConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        httpClient: OkHttpClient,
        @Named("BaseUrl") baseUrl: String
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(httpClient)
            .build()
    }

    @Provides
    @Named("BaseUrl")
    fun baseUrl() = "https://reqres.in"

    @Provides
    fun moshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun moshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi {
        return Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .build()
    }

    @Provides
    fun kotlinJsonAdapterFactory(): KotlinJsonAdapterFactory {
        return KotlinJsonAdapterFactory()
    }

    @Provides
    fun rxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun httpClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun interceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", "UsersAndDetails")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

}