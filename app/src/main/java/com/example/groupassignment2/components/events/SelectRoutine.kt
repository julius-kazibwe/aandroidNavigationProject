package com.example.groupassignment2.components.events

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
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
import androidx.navigation.NavHostController
import com.example.groupassignment2.R
import com.example.groupassignment2.components.MyToolbar
import com.example.groupassignment2.components.datastore.RoutineViewModel
import com.example.groupassignment2.models.IconType

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SelectRoutine(
   navController: NavHostController
){
    val context = LocalContext.current
    //val scope = rememberCoroutineScope()
    val dataStore = RoutineViewModel(context)
    dataStore.loadData()
    val retrievedData = dataStore.name.value

    val title = "Select a Routine"
    val iconsList = listOf( IconType.ImageVectorIcon(imageVector = Icons.Default.ArrowBack))
    val routeList = listOf("favorites")


    Scaffold(
        topBar = {
            MyToolbar(iconsList1 = iconsList, title , iconsList2 = emptyList(),
                navController, routeList1 =  routeList)
        },
        content = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        // .wrapContentHeight()
                        .padding(start = 16.dp, top = 16.dp)

                ) {
                    FloatingActionButton(
                        onClick = { navController.navigate("createRoutine") },
                        contentColor = Color.White,
                        backgroundColor = Color.Blue

                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                    Text(
                        text = "Create a new Routine",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold

                        ),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                if(retrievedData.isNotEmpty()){

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.notify),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        Text(
                            text = retrievedData,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

        }
    )
}




