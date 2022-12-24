package com.article.core.di

import com.article.core.data.AppSharedPreferences
import com.article.core.data.AppSharedPreferencesImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SharedPreferencesModule {

    @Binds
    abstract fun bindsSharedPreferences(sharedPreferencesImpl: AppSharedPreferencesImpl):
            AppSharedPreferences
}