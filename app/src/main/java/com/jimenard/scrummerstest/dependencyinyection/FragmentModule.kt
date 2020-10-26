package com.jimenard.scrummerstest.dependencyinyection

import com.jimenard.scrummerstest.ui.fragment.CarDetailFragment
import com.jimenard.scrummerstest.ui.fragment.CarListFragment
import com.jimenard.scrummerstest.ui.fragment.CategoryListFragment
import com.jimenard.scrummerstest.ui.fragment.NewCarFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    //Fragmentos que se Inyectan
    @ContributesAndroidInjector
    abstract fun contributeCarListFragment(): CarListFragment

    @ContributesAndroidInjector
    abstract fun contributeCarDetailFragment(): CarDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeNewCarFragment(): NewCarFragment

    @ContributesAndroidInjector
    abstract fun contributeCategoryListFragment(): CategoryListFragment
}