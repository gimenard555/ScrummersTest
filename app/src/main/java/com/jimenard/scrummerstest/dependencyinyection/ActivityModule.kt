package com.jimenard.scrummerstest.dependencyinyection

import com.jimenard.scrummerstest.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Listado de las actividades que necesitan de inyecccion de dependencias para el view model
 * del modulo de compradores
 */
@Module
abstract class ActivityModule {
    //Actividades que se Inyectan
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}