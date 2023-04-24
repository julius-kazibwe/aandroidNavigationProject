package com.example.groupassignment2.components.events

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import android.app.TimePickerDialog
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groupassignment2.components.MyToolbar
import androidx.navigation.NavHostController
import com.example.groupassignment2.R
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

    val title = "Create a Routine"
    val iconsList1 = listOf( IconType.ImageVectorIcon(imageVector = Icons.Default.Close))
    val iconsList2 = listOf( IconType.ImageVectorIcon(imageVector = Icons.Default.Done))
    val routeList1 = listOf("selectRoutine")
    val routeList2 = listOf("routines")

    var routineName by rememberSaveable { mutableStateOf("")}

    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val time = rememberSaveable {mutableStateOf("")}


    var selectedTime by rememberSaveable{ mutableStateOf("${time.value}")}

    val timePickerDialog = TimePickerDialog(
        context,
        {_, hour: Int, minute: Int ->

            val amPm = if (hour < 12) "AM" else "PM"
            val hour = if(hour % 12 == 0) 12 else hour % 12

            time.value = "$hour:$minute $amPm"
        }, hour, minute, false

    )
    selectedTime = "${time.value}"

    val isShowingDialog = navController.previousBackStackEntry?.arguments?.getBoolean("isShowingDialog") ?: false
    if(isShowingDialog) timePickerDialog.show()

    var openAlert = navController.previousBackStackEntry?.arguments?.getBoolean("openAlert") ?: false

    var enteredValue by rememberSaveable{mutableStateOf(" ")}

    var showProgressBar by remember{ mutableStateOf(false) }
    var progress by remember {mutableStateOf(0.0f)}

   // val createdRoutineViewModel: CreatedRoutineViewModel = viewModel()

    if(openAlert) {
        AlertDialog(
            onDismissRequest = { openAlert = false},

            title ={Text(text = "Enter a value")},

            confirmButton = {
                TextButton(onClick = {
                    openAlert = false
                    showProgressBar = true


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
                    value = enteredValue,
                    onValueChange = {enteredValue = it},
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
        },

    content = {
            Column(
                modifier = Modifier
                    .background(colorResource(id = R.color.bottomBar))

            ) {

                Column (
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
                                onValueChange = { routineName = it },
                                label = { Text(text = "Routine Name") },
                                placeholder = { Text(text = "Routine Name") },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = colorResource(id = R.color.bottomBar)
                                ),
                                modifier = Modifier.fillMaxWidth()

                            )

                            Text(
                                text = "When",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )
                        }

                    }

                    if (selectedTime != "") {

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
                                    text = "The time is $selectedTime",
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
                                    scope.launch{
                                        dataStore.saveData(routineName)
                                    }
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

                    if(
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
                    }else{

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
                                    text = "Send Notification:  $enteredValue",
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

                    if (selectedTime == "") {
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
    )
}