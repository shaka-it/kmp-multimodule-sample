package kmp.multimodule.sample.common.posts.data

import kmp.multimodule.sample.common.posts.api.repository.PostsRepository
import kmp.multimodule.sample.common.posts.data.ktor.KtorPostsDataSource
import kmp.multimodule.sample.common.posts.data.mapper.PostMapper
import kmp.multimodule.sample.common.posts.data.repository.DefaultPostsRepository
import kmp.multimodule.sample.common.posts.data.sqldelight.SqlDelightPostsDataSource
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val postsDataModule = module {
    factory {
        PostMapper()
    }
    single {
        SqlDelightPostsDataSource(
            driverFactory = get(),
            coroutineContext = Dispatchers.Default,
        )
    }
    single {
        KtorPostsDataSource(
            httpClient = get(),
            authRepository = get(),
        )
    }
    single<PostsRepository> {
        DefaultPostsRepository(
            localDataSource = get(),
            postMapper = get(),
            remoteDataSource = get(),
            coroutineContext = Dispatchers.Default,
        )
    }
}