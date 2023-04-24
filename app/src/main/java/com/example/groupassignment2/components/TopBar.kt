package com.example.groupassignment2.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.groupassignment2.R
import com.example.groupassignment2.models.IconType



@Composable
fun MyToolbar(
    iconsList1: List<IconType> = emptyList(),
    title: String ,
    iconsList2: List<IconType> = emptyList(),
    navController: NavHostController,
    routeList1: List<String> = emptyList(),
    routeList2: List<String> = emptyList()
) {

    TopAppBar(
        backgroundColor = colorResource(id = R.color.goldenyellow),
        elevation = 4.dp,

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            if (iconsList1.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    LazyRow {

                        items(iconsList1.size) { index ->
                            when (val icon = iconsList1[index]) {
                                is IconType.PainterIcon -> {
                                    IconButton(onClick = {navController.navigate(routeList1[index]) }) {
                                        Icon(
                                            painter = icon.painter,
                                            contentDescription = "Painter icon",
                                            tint = Color.White
                                        )
                                    }
                                }
                                is IconType.ImageVectorIcon -> {
                                    IconButton(onClick = { navController.navigate(routeList1[index])}) {
                                        Icon(
                                            imageVector = icon.imageVector,
                                            contentDescription = "ImageVector icon",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 16.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            if (iconsList2.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    LazyRow {
                        items(iconsList2.size) { index ->
                            when (val icon = iconsList2[index]) {
                                is IconType.PainterIcon -> {
                                    IconButton(onClick = { navController.navigate(routeList2[index]) }) {
                                        Icon(
                                            painter = icon.painter,
                                            contentDescription = "Painter icon",
                                            tint = Color.White
                                        )
                                    }
                                }
                                is IconType.ImageVectorIcon -> {
                                    IconButton(onClick = { navController.navigate(routeList2[index]) }) {
                                        Icon(
                                            imageVector = icon.imageVector,
                                            contentDescription = "ImageVector icon",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

