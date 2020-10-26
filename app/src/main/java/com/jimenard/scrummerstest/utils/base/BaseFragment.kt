package com.jimenard.scrummerstest.utils.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Fragemto base para usar inyeccion de dependencias y control sobre estas
 */
abstract class BaseFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    inline fun <reified T : ViewModel> ScrummersViewModelFactory<T>.get(): T =
        ViewModelProviders.of(this@BaseFragment, this)[T::class.java]

    override fun androidInjector(): AndroidInjector<Any> = this.childFragmentInjector
}