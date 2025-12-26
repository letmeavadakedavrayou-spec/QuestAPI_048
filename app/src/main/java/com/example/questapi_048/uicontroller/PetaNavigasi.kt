package com.example.questapi_048.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.questapi_048.uicontroller.route.DestinasiDetail
import com.example.questapi_048.uicontroller.route.DestinasiEdit
import com.example.questapi_048.uicontroller.route.DestinasiEntry
import com.example.questapi_048.uicontroller.route.DestinasiHome
import com.example.questapi_048.uicontroller.view.DetailScreen
import com.example.questapi_048.uicontroller.view.EntrySiswaScreen
import com.example.questapi_048.uicontroller.view.HomeScreen
import com.example.questapi_048.uicontroller.view.ItemEditScreen

@Composable
fun DataSiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { itemId ->
                    navController.navigate("${DestinasiDetail.route}/$itemId")
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = { navController.navigate(DestinasiHome.route) {
                    popUpTo(DestinasiHome.route) { inclusive = true }
                } }
            )
        }

        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetail.idSiswa) { type = NavType.IntType })
        ) {
            DetailScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = { itemId ->
                    navController.navigate("${DestinasiEdit.route}/$itemId")
                }
            )
        }

        composable(
            route = DestinasiEdit.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEdit.idSiswa) { type = NavType.IntType })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

    }
}