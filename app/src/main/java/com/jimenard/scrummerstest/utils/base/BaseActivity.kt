package com.jimenard.scrummerstest.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection

/**
 * Actividad base para usar inyeccion de dependencias y control sobre estas
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    inline fun <reified T : ViewModel> ScrummersViewModelFactory<T>.get(): T =
        ViewModelProviders.of(this@BaseActivity, this)[T::class.java]
}