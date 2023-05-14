package dadm.jmartor.paymate.di

import dadm.jmartor.paymate.data.expenses.ExpenseDataSource
import dadm.jmartor.paymate.data.expenses.ExpenseDataSourceImpl
import dadm.jmartor.paymate.data.expenses.ExpenseRepository
import dadm.jmartor.paymate.data.expenses.ExpenseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ExpenseBinderModule {
    @Binds
    abstract fun bindExpenseRepository(repository: ExpenseRepositoryImpl) : ExpenseRepository

    @Binds
    abstract fun bindExpenseDataSource(dataSource: ExpenseDataSourceImpl) : ExpenseDataSource
}