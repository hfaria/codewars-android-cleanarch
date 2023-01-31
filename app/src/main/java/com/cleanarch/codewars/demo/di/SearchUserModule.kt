package com.cleanarch.codewars.demo.di

import androidx.lifecycle.ViewModel
import com.cleanarch.codewars.demo.ui.search_user.SearchUserFragment
import com.cleanarch.codewars.demo.ui.search_user.SearchUserViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SearchUserModule {

    @ContributesAndroidInjector
    abstract fun contributesSearchUserFragment(): SearchUserFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchUserViewModel::class)
    abstract fun bindViewModel(viewmodel: SearchUserViewModel): ViewModel

}
