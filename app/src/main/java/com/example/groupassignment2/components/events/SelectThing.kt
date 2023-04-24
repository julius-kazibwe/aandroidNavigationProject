package com.example.groupassignment2.components.events

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.groupassignment2.components.MyToolbar
import androidx.navigation.NavHostController
import com.example.groupassignment2.R
import com.example.groupassignment2.models.IconType


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SelectThing(navController: NavHostController){

    val title = "Select a Thing"
    val iconsList1 = listOf( IconType.ImageVectorIcon(imageVector = Icons.Default.ArrowBack))
    val iconsList2 = listOf(IconType.PainterIcon(painter = painterResource(id = R.drawable.filter_left)))
    val routeList1 = listOf("createRoutine")
    val routeList2 = listOf("")

    val tabTitles = listOf("THINGS", "SCENES", "ROUTINES")
    var selectedTabIndex by remember { mutableStateOf(0) }


    Scaffold(
        topBar = {
            MyToolbar(iconsList1 = iconsList1, title = title, iconsList2 = iconsList2, navController, routeList1 = routeList1, routeList2 = routeList2)
        },
        content = {

            Column{

                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    backgroundColor = Color.White,
                    contentColor = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    tabTitles.forEachIndexed { index, tabTitle ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(text = tabTitle) },

                            )
                    }
                }

                when (selectedTabIndex) {
                    0 -> {
                        Column(
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                                .wrapContentSize()
                                .clickable(onClick = { navController.navigate("selectAction")})
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.notify),
                                contentDescription = "notification",
                                modifier = Modifier.size(52.dp).padding(start = 16.dp)

                            )
                            Text(
                                text = "Notification",
                                color = Color.Gray,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                    1 -> {
                        // content for Tab 2
                    }
                    2 -> {
                        // content for Tab 3
                    }
                }

            }
        }
    )
}