package com.jimenard.scrummerstest.dependencyinyection

import android.content.Context
import com.jimenard.scrummerstest.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class]
)
/**
 * Componente para inyectar objetos en las actividades a partir del contexto de la aplicacion
 */
interface ScrummersComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        @BindsInstance
        abstract fun appContext(appContext: Context): Builder
    }
}