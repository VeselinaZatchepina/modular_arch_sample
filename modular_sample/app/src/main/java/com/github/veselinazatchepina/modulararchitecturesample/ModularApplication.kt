package com.github.veselinazatchepina.modulararchitecturesample

import android.app.Application
import com.github.veselinazatchepina.modulararchitecturesample.action.Action
import com.github.veselinazatchepina.modulararchitecturesample.action.ActionImpl
import com.github.veselinazatchepina.modulararchitecturesample.navigator.Navigator
import com.github.veselinazatchepina.modulararchitecturesample.navigator.NavigatorImpl
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager

class ModularApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        XInjectionManager.bindComponentToCustomLifecycle(object : IHasComponent<Navigator> {
            override fun getComponent(): Navigator =
                NavigatorImpl()
        })

        XInjectionManager.bindComponentToCustomLifecycle(object : IHasComponent<Action> {
            override fun getComponent(): Action =
                ActionImpl(this@ModularApplication)
        })
    }

}