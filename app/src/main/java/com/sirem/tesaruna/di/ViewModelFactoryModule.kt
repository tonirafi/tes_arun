package com.sirem.tesaruna.di

import androidx.lifecycle.ViewModelProvider
import com.sirem.tesaruna.utils.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    // Method #2
    @Binds
    abstract fun bindViewModelFactory(viewModelProvideFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}