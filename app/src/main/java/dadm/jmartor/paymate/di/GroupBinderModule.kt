package dadm.jmartor.paymate.di

import dadm.jmartor.paymate.data.groups.GroupDataSource
import dadm.jmartor.paymate.data.groups.GroupDataSourceImpl
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
abstract class GroupBinderModule {
    @Binds
    abstract fun bindGroupRepository(repository: GroupRepositoryImpl) : GroupRepository

    @Binds
    abstract fun bindGroupDataSource(dataSource: GroupDataSourceImpl) : GroupDataSource
}