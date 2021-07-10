package dev.plesa.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.plesa.data.R
import dev.plesa.data.RemoteDataSource
import dev.plesa.data.remote.RemoteDataSourceImpl
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteModule = module {

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(androidContext().getString(R.string.github_api_base_url))
            .client(get())
            .addConverterFactory(get())
            .build()
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(get())
    }

}