package com.lowersoft.waifupics.di

import com.lowersoft.waifupics.data.remote.service.ktor.PicsServiceImpl
import com.lowersoft.waifupics.domain.repository.PicsRepository
import com.lowersoft.waifupics.domain.repository.PicsRepositoryKtorImpl
import com.lowersoft.waifupics.presentation.ui.feed.FeedViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        PicsServiceImpl(
            client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.BODY
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
    }

    single<PicsRepository> {
        PicsRepositoryKtorImpl(get())
    }

    viewModel {
        FeedViewModel(get())
    }
}