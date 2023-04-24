package com.example.groupassignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.groupassignment2.components.*
import com.example.groupassignment2.components.events.*
import com.example.groupassignment2.ui.theme.GroupAssignment2Theme
import com.example.groupassignment2.utils.NavItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroupAssignment2Theme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    // scaffold component
                    Scaffold(

                        // Bottom navigation
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        },content = {padding->
                            // NavHost: where screens are placed
                            NavHostContainer(navController = navController, padding = padding)

                        }
                    )
                }
            }
        }
    }
}


@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,

        //set the start destination as things
        startDestination = "favorites",
        //set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {
            composable(Routes.Things.route){
                Things(navController)
            }
            composable(Routes.Favorites.route){
                Favorites(navController)
            }
            composable(Routes.Routines.route){
                Routines(navController)
            }
            composable(Routes.Ideas.route){
                Ideas(navController)
            }
            composable(Routes.Settings.route){
                Settings(navController)
            }
            composable(Routes.SelectRoutine.route){
                SelectRoutine(navController)
            }
            composable(Routes.CreateRoutine.route) {
                CreateRoutine(navController)
            }
            composable(Routes.SelectEvent.route){
                SelectEvent(navController)
            }
            composable(Routes.SelectThing.route){
                SelectThing(navController)
            }
            composable(Routes.SelectAction.route){
                SelectAction(navController)
            }
        }

    )

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        //set the background color
        backgroundColor = colorResource(id = R.color.bottomBar)

    ) {
        // observe the backstack
        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        //observe the current route to  change the icon
        //color, label
        val currentRoute = navBackStackEntry?.destination?.route

        //Bottom nav items we declared
        NavItem.BottomNavItems.forEach { item ->

            BottomNavigationItem(
                //if the current route is equal then its selected route
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                unselectedContentColor = Color.Black,
                icon = {
                   Icon(painter = painterResource(id = item.icon),
                       contentDescription = "iconId",
                       tint = if (currentRoute == item.route) Color.Yellow else Color.Black // Tint the icon based on selected or not
                   )
                },
                label = {
                    Text(text = item.label, fontSize =2.5.em, fontWeight = FontWeight.Bold)
                },
                alwaysShowLabel = true
            )
        }

    }

}


