package com.example.groupassignment2.components.events

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.example.groupassignment2.components.MyToolbar
import androidx.navigation.NavHostController
import com.example.groupassignment2.models.IconType

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SelectEvent(navController: NavHostController){

    val title = "Select an Event"
    val iconsList1 = listOf( IconType.ImageVectorIcon(imageVector = Icons.Default.ArrowBack))
    val routeList1 = listOf("createRoutine")

    var isShowingDialog by rememberSaveable { mutableStateOf(false) }


    Scaffold(
        topBar = {
            MyToolbar(iconsList1 = iconsList1, title = title, iconsList2 = emptyList() ,navController, routeList1 = routeList1)
        },
        content = {
            Column (

                    ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable(
                                onClick = {
                                    isShowingDialog = true

                                  //navController.navigate("createRoutine")
                                   // navController.popBackStack()
                                    //navController.currentBackStackEntry?.arguments?.putBoolean("isShowingDialog", isShowingDialog)

                                }
                            )

                    )
                    {
                        Text(text = "The time is ")
                        Text(text = "Time", fontWeight = Bold,)
                    }

                    if (isShowingDialog) {
                        LaunchedEffect(key1 = isShowingDialog) {
                            navController.currentBackStackEntry?.arguments?.putBoolean("isShowingDialog", isShowingDialog)
                            navController.navigate("createRoutine")
                        }
                    }


                    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(start = 12.dp, end = 12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    ) {
                        Text(text = "It's sunset at ")
                        Text(text = "Location", fontWeight = Bold,)
                    }

                    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(start = 12.dp, end = 12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    ) {
                        Text(text = "It's sunrise at ")
                        Text(text = "Location", fontWeight = Bold,)
                    }

                    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(start = 12.dp, end = 12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    ) {
                        Column{
                            Row{
                                Text(text = "It's ")
                                Text(text = "15 ", fontWeight = Bold,)
                                Text(text = "minutes before sunrise at")
                            }
                            Text(text = "Location", fontWeight = Bold)
                        }

                    }

                    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(start = 12.dp, end = 12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    ) {
                        Column{
                            Row{
                                Text(text = "It's ")
                                Text(text = "15 ", fontWeight = Bold,)
                                Text(text = "minutes after sunrise at")
                            }
                            Text(text = "Location", fontWeight = Bold)
                        }
                    }

                    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(start = 12.dp, end = 12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    ) {
                        Column{
                            Row{
                                Text(text = "It's ")
                                Text(text = "15 ", fontWeight = Bold,)
                                Text(text = "minutes before sunset at")
                            }
                            Text(text = "Location", fontWeight = Bold)
                        }
                    }

                    Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(start = 12.dp, end = 12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    ) {
                        Column{
                            Row{
                                Text(text = "It's ")
                                Text(text = "15 ", fontWeight = Bold,)
                                Text(text = "minutes after sunset at")
                            }
                            Text(text = "Location", fontWeight = Bold)
                        }
                    }
                }
            }

        }
    )
}