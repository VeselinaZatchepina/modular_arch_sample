package com.github.veselinazatchepina.modulararchitecturesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.veselinazatchepina.modulararchitecturesample.navigator.Navigator
import me.vponomarenko.injectionmanager.x.XInjectionManager

/**
 * В проекте 6 несвязанных modules и 1 - core module(:app).
 * Взаимодействие между модулями происходит через интерфейсы Action, Navigator в :app module.
 * В каждом модуле есть свой интерфейс Action, Navigator, которые реализуются в :app module.
 *
 * Модули:
 *
 * additionalmovieinfo - модуль для отображения описания фильма.
 * app - корневой модуль, занимается обработкой взаимодействий модулей, их отрисовкой (через Action и Navigator).
 * cardcontent - модуль карточки фильма. Отображает в себе модули shortmovieinfo и additionalmovieinfo, а также запрашивает данные у repositories.
 * entities - сущность "фильм".
 * listcontent - модуль списка фильмов. Отображает в себе модуль shortmovieinfo, а также запрашивает данные у repositories.
 * repositories - модуль, который отвечает за слой repositories (запросы в локальную бд и в сеть).
 * shortmovieinfo - модуль для отображения постера и названия фильма.
 *
 * DI использовались только для инжекта Action и Navigator.
 *
 */
class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by lazy {
        XInjectionManager.findComponent<Navigator>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.bind(this)
        if (savedInstanceState == null) {
            navigator.showListContent()
        }
    }
}
