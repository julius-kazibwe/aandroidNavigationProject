package com.example.groupassignment2.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.groupassignment2.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Ideas(
    navController: NavHostController
){

    val title = "My Smart Home"

    Scaffold(

        topBar = {
            MyToolbar(iconsList1 = emptyList(), title, iconsList2 =  emptyList(), navController)
        },
        content ={
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.settingsRow)),

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        text = "More Recommendations",
                        modifier = Modifier.padding(8.dp),
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.h6


                    )
                    Text(
                        text = "Even more recommendations!",
                        modifier = Modifier.padding(8.dp) ,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.Gray
                    )

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.White)
                        .padding(all = 8.dp)

                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.th),
                            contentDescription = "My Image",
                            modifier = Modifier
                                .fillMaxSize()
                                    .width(400.dp),
                            contentScale = ContentScale.Crop

                        )
                        Column{
                            Text(
                                text = "Thanksgiving Dinner",
                                color = Color.White,
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier.padding(start = 12.dp)
                            )
                            Text(
                                text = "Ready",
                                color = Color.White,
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier.padding(start = 12.dp)
                            )

                            Spacer(modifier = Modifier.size(16.dp))

                            Text(
                                text = "Send me a notification ",
                                style = MaterialTheme.typography.subtitle2,
                                color = Color.LightGray,
                                modifier = Modifier.padding(start = 12.dp)
                            )
                        }
                    }
                        
                    }
                }
            }
    )
}



