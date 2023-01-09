package com.cleanarch.codewars.demo.integration.setup

import com.cleanarch.codewars.demo.di.AppComponent
import com.cleanarch.codewars.demo.di.DataLayerModule
import com.cleanarch.codewars.demo.integration.UserNetworkTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DataLayerModule::class,
    ]
)
interface NetworkTestComponent : AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(baseTest: BaseNetworkTest): Builder

        fun build(): NetworkTestComponent
    }

    fun inject(test: UserNetworkTest)
}