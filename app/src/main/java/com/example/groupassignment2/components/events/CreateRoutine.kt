@file:Suppress("NAME_SHADOWING")

package com.example.groupassignment2.components.events

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import android.app.TimePickerDialog
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groupassignment2.components.MyToolbar
import androidx.navigation.NavHostController
import com.example.groupassignment2.R
import com.example.groupassignment2.components.datastore.RoutineData
import com.example.groupassignment2.components.datastore.RoutineItems
import com.example.groupassignment2.components.datastore.RoutineViewModel
import com.example.groupassignment2.models.IconType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateRoutine(navController: NavHostController){
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val dataStore = RoutineViewModel(context)
    val routineItemsViewModel = RoutineItems(context)

    var routineName by rememberSaveable { mutableStateOf("") }
    var timeOfRunning by rememberSaveable { mutableStateOf("") }
    var notificationText by rememberSaveable { mutableStateOf("") }



    val title = "Create a Routine"
    val iconsList1 = listOf( IconType.ImageVectorIcon(imageVector = Icons.Default.Close))
    val iconsList2 = listOf( IconType.ImageVectorIcon(imageVector = Icons.Default.Done))
    val routeList1 = listOf("selectRoutine")
    val routeList2 = listOf("routines")


    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]





    val timePickerDialog = TimePickerDialog(
        context,
        {_, selectedHour: Int, selectedMinute: Int ->

            val amPm = if (selectedHour < 12) "AM" else "PM"
           // val hours = if(hour % 12 == 0) 12 else hour % 12

            timeOfRunning = "$selectedHour:$selectedMinute $amPm"
            routineItemsViewModel.saveTimeData(timeOfRunning)
        }, hour, minute, false

    )


    val isShowingDialog = navController.previousBackStackEntry?.arguments?.getBoolean("isShowingDialog") ?: false
    if(isShowingDialog) timePickerDialog.show()

    val openAlert = navController.previousBackStackEntry?.arguments?.getBoolean("openAlert") ?: false



    var showProgressBar by remember{ mutableStateOf(false) }
    var progress by remember {mutableStateOf(0.0f)}



    if(openAlert && !showProgressBar) {
        AlertDialog(
            onDismissRequest = { },

            title ={Text(text = "Enter a value")},

            confirmButton = {
                TextButton(onClick = {
                    showProgressBar = true
                    routineItemsViewModel.saveNotificationData(notificationText)


                }) {
                    Text(text = "OK", color = Color.Blue)
                }
            },

           dismissButton = {
               TextButton(onClick = {  navController.navigate("selectAction")}) {
                   Text(text = "CANCEL", color = Color.Blue)
               }
           },
            text = {

                TextField(
                    value = notificationText,
                    onValueChange = {
                        notificationText = it
                                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
            }
        )
    }

    if (showProgressBar ) {
        AlertDialog(
            onDismissRequest = { },

            title ={ },

            confirmButton = {

            },

            dismissButton = {

            },
            text = {
                LaunchedEffect(Unit) {
                    repeat(10) {
                        delay(500)
                        progress += 10f

                        if (progress == 100f) {
                            showProgressBar = false

                            navController.navigate("routines")
                            scope.launch{
                                val newRoutine = RoutineData(
                                    routineName = routineItemsViewModel.loadRoutineData(),
                                    timeOfRunning = routineItemsViewModel.loadTimeData(),
                                    notificationText = routineItemsViewModel.loadNotificationData()
                                )

                                dataStore.saveData(newRoutine)
                            }

                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp).fillMaxWidth() ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    LinearProgressIndicator(
                        progress = progress/100f,
                        color = Color.Blue,
                        backgroundColor =Color.LightGray,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(text = "Creating new Routine")
                }
            }
        )

    }

    Scaffold(
        topBar = {
            MyToolbar(iconsList1 = iconsList1, title = title, iconsList2 = iconsList2, navController, routeList1 = routeList1, routeList2 = routeList2)
        }

    ) {
        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.bottomBar))

        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .weight(1f)

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        TextField(
                            value = routineName,
                            onValueChange = { value ->
                                routineName = value
                                routineItemsViewModel.saveRoutineData(value)
                                            },
                            label = { Text(text = "Routine Name") },
                            placeholder = { Text(text = "Routine Name") },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = colorResource(id = R.color.bottomBar)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            )

                        )

                        Text(
                            text = "When",
                            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                        )
                    }

                }

                if (timeOfRunning != "") {

                    Row(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.clock_fill),
                            contentDescription = "clock",
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(32.dp)
                        )

                        Column(
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Date & Time",
                                color = Color.Gray,

                                )

                            Text(
                                text = "The time is $timeOfRunning",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.settings),
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .background(colorResource(id = R.color.settingsRow))
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Want this routine to run automatically?",
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text(
                            text = "Add an event below.",
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier.background(colorResource(id = R.color.bottomBar))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Add Event",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(end = 16.dp)

                        )

                        FloatingActionButton(
                            onClick = {
                                navController.navigate("selectEvent")


                            },
                            contentColor = Color.White,
                            backgroundColor = Color.Blue,
                            modifier = Modifier.size(32.dp)

                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Add",
                                modifier = Modifier.size(16.dp)
                            )
                        }

                    }

                    Text(
                        text = " Run these actions",
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }

                if (
                    !showProgressBar
                ) {

                    Row(
                        modifier = Modifier
                            .background(colorResource(id = R.color.settingsRow))
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "No actions. Top below to add one.",
                            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                        )

                    }
                } else {

                    Row(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.notify),
                            contentDescription = "notification",
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(32.dp)
                        )

                        Column(
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Notification",
                                color = Color.Gray,

                                )

                            Text(
                                text = "Send Notification:  $notificationText",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.settings),
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                }


                Column(
                    modifier = Modifier.background(colorResource(id = R.color.bottomBar))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Add Action",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(end = 16.dp)

                        )

                        FloatingActionButton(
                            onClick = { navController.navigate("selectThing") },
                            contentColor = Color.White,
                            backgroundColor = Color.Blue,
                            modifier = Modifier.size(32.dp)

                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Add",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Text(
                        text = "But Only if",
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }

                if (timeOfRunning == "") {
                    Row(
                        modifier = Modifier
                            .background(colorResource(id = R.color.settingsRow))
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Add an event before adding conditions.",
                            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                        )

                    }
                } else {

                    Column(
                        modifier = Modifier
                            .background(colorResource(id = R.color.settingsRow))
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Want this routine to only run sometimes?",
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text(
                            text = "Add a condition below.",
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.bottomBar)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Add Condition",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(end = 16.dp)

                        )

                        FloatingActionButton(
                            onClick = { /*navController.navigate("")*/ },
                            contentColor = Color.White,
                            backgroundColor = Color.Blue,
                            modifier = Modifier.size(32.dp)

                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Add",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}