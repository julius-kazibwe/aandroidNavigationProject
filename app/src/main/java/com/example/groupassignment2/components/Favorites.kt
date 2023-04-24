package com.example.groupassignment2.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.groupassignment2.R
import com.example.groupassignment2.models.IconType

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Favorites(
    navController: NavHostController
){
    val title = "My Smart Home"
    val iconsList = listOf( IconType.ImageVectorIcon(imageVector = Icons.Filled.Edit))

    Scaffold(
        topBar = {
                 MyToolbar(iconsList1 = emptyList(), title, iconsList2 = iconsList, navController)
        },

        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center

            ) {

                // Image and text container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.TopCenter),
                    contentAlignment = Alignment.Center

                ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter= painterResource(id = R.drawable.staroutine),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)

                            )

                            Text(
                                text = "No Favourites!",
                                fontSize = MaterialTheme.typography.h5.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray
                            )
                            Text(
                                text = "Add your favourite routines for easy",
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                            )
                            Text(
                                text = "Access here.",
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                            )

                            Text(
                                text = "Tap the '+' button below to add your",
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                            )
                            Text(
                                text = "favourite routines. ",
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                            )

                        }
                    }
                }

        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate("selectRoutine") },
                    modifier = Modifier.align(Alignment.BottomEnd),
                    contentColor = Color.White,
                    backgroundColor = Color.Blue

                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")                }
            }

        }
    )

}
