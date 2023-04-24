package com.example.groupassignment2.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.groupassignment2.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Settings(
    navController: NavHostController
){
    val title = "My Smart Home"

    Scaffold(
        topBar = {
            MyToolbar(iconsList1 = emptyList(), title , iconsList2 = emptyList(), navController)
        },
        content ={
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),

            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                       // .wrapContentHeight()
                        //.align(Alignment.TopCenter),


                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(colorResource(id = R.color.settingsRow))
                            ){
                        Text(text ="User Settings", modifier = Modifier
                            .padding(16.dp),
                            color = Color.Gray, fontWeight = Bold,
                            style = TextStyle(textAlign = TextAlign.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color.White)

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.person_circle),
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = Modifier.padding(start = 16.dp)
                            )

                            Column(
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Text(
                                    text = "Moses",
                                    fontWeight= Bold,
                                    style = TextStyle(
                                        fontSize = 15.sp
                                    ),

                                )

                                Text(
                                    text = "kmntanda@gmail.com",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(colorResource(id = R.color.settingsRow))
                    ){
                        Text(text ="Home Settings", modifier = Modifier
                            .padding(16.dp),
                            color = Color.Gray, fontWeight = Bold,
                            style = TextStyle(textAlign = TextAlign.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(Color.White)

                    ) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterStart)

                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.person_fill__1_),
                                    contentDescription = null,
                                    tint = Color.Yellow

                                )
                                Text(
                                    text = "Accounts & Hubs",
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = Bold
                                    ),
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(color = Color.LightGray, thickness = 1.dp)
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.people_fill_2_),
                                    contentDescription = null,
                                    tint = Color.Yellow
                                )
                                Text(
                                    text = "Clients",
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = Bold

                                    ),
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(color = Color.LightGray, thickness = 1.dp)
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.location),
                                    contentDescription = null,
                                    tint = Color.Yellow
                                )
                                Text(
                                    text = "Locations",
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = Bold
                                    ),
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(colorResource(id = R.color.settingsRow))
                    ){
                        Text(text ="Voice", modifier = Modifier
                            .padding(16.dp),
                            color = Color.Gray, fontWeight = Bold,
                            style = TextStyle(textAlign = TextAlign.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color.White)

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.voiceass),
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                            Text(
                                    text = "Voice Assistants",
                                fontWeight = Bold,
                            style = TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier.padding(start = 16.dp)
                            )

                        }
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(colorResource(id = R.color.settingsRow))
                    ){
                        Text(text ="App Permissions", modifier = Modifier
                            .padding(16.dp),
                            color = Color.Gray, fontWeight = Bold,
                            style = TextStyle(textAlign = TextAlign.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color.White)

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.settings),
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                            Text(
                                text = "Notifications & Permissions",
                                fontWeight = Bold,
                                style = TextStyle(
                                    fontSize = 15.sp
                                ),
                                modifier = Modifier.padding(start = 16.dp)
                            )

                        }
                    }

                }
            }
        }
    )
}

