package com.example.groupassignment2

sealed class Routes (val route: String){
    object Things: Routes("things")
    object Favorites: Routes("favorites")
    object Routines: Routes("routines")
    object Ideas: Routes("ideas")
    object Settings: Routes("settings")
    object SelectRoutine: Routes("selectRoutine")
    object CreateRoutine: Routes("createRoutine")
    object SelectEvent: Routes("selectEvent")
    object SelectThing: Routes("selectThing")
    object SelectAction: Routes("selectAction")



}
