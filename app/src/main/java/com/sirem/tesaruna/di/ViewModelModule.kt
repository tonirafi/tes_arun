package com.sirem.tesaruna.di

import androidx.lifecycle.ViewModel
import com.sirem.tesaruna.model.DataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    // Method #1
    @Binds
    @IntoMap
    @ViewModelKey(DataViewModel::class)
    abstract fun bindMainViewModel(dataViewModel: DataViewModel): ViewModel
}
