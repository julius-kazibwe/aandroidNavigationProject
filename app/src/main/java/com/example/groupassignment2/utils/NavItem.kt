package com.example.groupassignment2.utils

import com.example.groupassignment2.R
import com.example.groupassignment2.models.BottomNavItem

object NavItem {

    val BottomNavItems = listOf(
            BottomNavItem(
                label = "Favorites",
                icon = R.drawable.staroutine,
                route = "favorites"
            ),
            BottomNavItem(
                label = "Things",
                icon =  R.drawable.thingsicon,
                route = "things"
            ),
            BottomNavItem(
                label = "Routines",
                icon = R.drawable.routinesbb,
                route ="routines"
            ),
            BottomNavItem(
                label = "Ideas",
                icon =  R.drawable.bright_light_bulb,
                route ="ideas"
            ),
            BottomNavItem(
                label = "Settings",
                icon = R.drawable.sliders,
                route ="settings"

            ),
        )
    }
