package com.sirem.tesaruna.di

import dagger.Module
import dagger.android.ContributesAndroidInjector


// declare all the fragments here , dependency of fragments are provided by this module

@Module
abstract class FragmentBuildersModule {

    // Method #1
//    @ContributesAndroidInjector
//    abstract fun contributeHomeFragment(): HomeFragment

}