package com.hkr.health.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hkr.health.AppContainer
import com.hkr.health.ui.navigation.HealthDestinations.HOME_ROUTE
import com.hkr.health.ui.navigation.HealthDestinations.QUESTIONS_ROUTE
import com.hkr.health.ui.navigation.HealthDestinations.ANALYSIS_ROUTE
import com.hkr.health.ui.navigation.HealthDestinations.QUESTION_ITEM_ARG
import com.hkr.health.ui.navigation.HealthDestinations.QUESTION_ITEM_ROUTE
import com.hkr.health.ui.screens.analysis.Analysis
import com.hkr.health.ui.screens.analysis.AnalysisViewModel
import com.hkr.health.ui.screens.home.Home
import com.hkr.health.ui.screens.questionItem.QuestionItemScreen
import com.hkr.health.ui.screens.questions.Questions
import com.hkr.health.ui.screens.questions.QuestionsViewModel
@Composable
fun HealthNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
    navActions: HealthNavigationActions = remember(navController) {
        HealthNavigationActions(navController)
    },
    viewModel: QuestionsViewModel
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.DarkGray
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    label = { Text(HOME_ROUTE) },
                    selected = currentRoute == HOME_ROUTE,
                    selectedContentColor = Color.Cyan,
                    unselectedContentColor = Color.White,
                    onClick = {
                        navActions.navigateToHome()
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null) },
                    label = { Text(QUESTIONS_ROUTE) },
                    selected = currentRoute == QUESTIONS_ROUTE,
                    selectedContentColor = Color.Cyan,
                    unselectedContentColor = Color.White,
                    onClick = {
                        navActions.navigateToQuestions()
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Info, contentDescription = null) },
                    label = { Text(ANALYSIS_ROUTE) },
                    selected = currentRoute == ANALYSIS_ROUTE,
                    selectedContentColor = Color.Cyan,
                    unselectedContentColor = Color.White,
                    onClick = {
                        navActions.navigateToAnalysis()
                    }
                )

            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
                .padding(innerPadding)
        ) {
                composable(HOME_ROUTE) { Home() }
                composable(QUESTIONS_ROUTE) {
                    Questions(
                        viewModel = viewModel,
                        onQuestionClick = { category -> navActions.navigateToQuestionItem(category) }
                    )
                }
                composable(ANALYSIS_ROUTE) {
                    Analysis(viewModel = viewModel)
                }
                composable(
                    "$QUESTION_ITEM_ROUTE/{$QUESTION_ITEM_ARG}",
                    arguments = listOf(
                        navArgument(QUESTION_ITEM_ARG){ type = NavType.StringType }
                    )
                ) { entry ->
                    val category = entry.arguments?.getString(QUESTION_ITEM_ARG) ?: ""
                    QuestionItemScreen(
                        viewModel = viewModel,
                        category = category,
                        onBackClick = { navActions.popUpToQuestions() }
                    )
                }
            }
        }
    }