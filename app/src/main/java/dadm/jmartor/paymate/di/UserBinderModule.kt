package dadm.jmartor.paymate.di

import dadm.jmartor.paymate.data.users.UserDataSource
import dadm.jmartor.paymate.data.users.UserDataSourceImpl
import dadm.jmartor.paymate.data.users.UserRepository
import dadm.jmartor.paymate.data.users.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.sql.DataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class UserBinderModule {
    @Binds
    abstract fun bindUserRepository(repository: UserRepositoryImpl) : UserRepository

    @Binds
    abstract fun bindUserDataSource(dataSource: UserDataSourceImpl) : UserDataSource
}