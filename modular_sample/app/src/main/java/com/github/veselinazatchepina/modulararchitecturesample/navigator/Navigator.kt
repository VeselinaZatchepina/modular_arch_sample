package com.github.veselinazatchepina.modulararchitecturesample.navigator

import androidx.fragment.app.FragmentActivity
import com.github.veselinazatchepina.cardcontent.navigator.CardContentNavigator
import com.github.veselinazatchepina.listcontent.navigator.ListContentNavigator

interface Navigator : ListContentNavigator, CardContentNavigator {

    fun bind(activity: FragmentActivity?)

    fun showListContent()

}