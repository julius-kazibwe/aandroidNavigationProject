package com.example.groupassignment2.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
fun Things(
    navController: NavHostController
){
    val title = "My Smart Home"
    val iconsList = listOf(
        IconType.ImageVectorIcon(imageVector = Icons.Filled.Search),
        IconType.PainterIcon(painter = painterResource(id = R.drawable.filter_left))
    )
    Scaffold(
        topBar = {
            MyToolbar(iconsList1 = emptyList(), title, iconsList2 = iconsList, navController)
        },
        content ={
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {


                // Image and text container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.TopCenter)
                        .absolutePadding(top = 50.dp)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "No things",
                            modifier = Modifier
                                .padding(top = 16.dp),
                            fontSize = MaterialTheme.typography.h4.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Column(
                            modifier = Modifier.padding(top = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "It looks like we didn't discover any devices.",
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                            )
                            Text(
                                text = "Try an option below",
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                            )
                        }
                    }
                } // Gray line separator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray)
                        .align(Alignment.Center)
                        .padding(vertical = 16.dp)
                )

                // Search icon container
                // Search icon container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter)
                        .absolutePadding(bottom = 270.dp, left = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Blue, CircleShape)
                            .padding(8.dp)
                            .absolutePadding(left = 4.dp),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Run Discovery",
                        modifier = Modifier.absolutePadding(left = 60.dp, top = 5.dp),
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
                // Add a cloud account icon container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter)
                        .absolutePadding(bottom = 215.dp, left = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Blue, CircleShape)
                            .padding(8.dp)
                            .absolutePadding(left = 4.dp),
                        contentAlignment = Alignment.Center

                    ) {

                    }
                    Text(
                        text = "Add a cloud Account",
                        modifier = Modifier.absolutePadding(left = 60.dp, top = 5.dp),
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
                // view supported devices
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter)
                        .absolutePadding(bottom = 160.dp, left = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Blue, CircleShape)
                            .padding(8.dp)
                            .absolutePadding(left = 4.dp),
                        contentAlignment = Alignment.Center

                    ) {

                    }
                    Text(
                        text = "View our supported devices",
                        modifier = Modifier.absolutePadding(left = 60.dp, top = 5.dp),
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
                // contact support
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter)
                        .absolutePadding(bottom = 100.dp, left = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Blue, CircleShape)
                            .padding(8.dp)
                            .absolutePadding(left = 4.dp),
                        contentAlignment = Alignment.Center

                    ) {

                    }
                    Text(
                        text = "Contact support",
                        modifier = Modifier.absolutePadding(left = 60.dp, top = 5.dp),
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
            }
        }
    )

}

