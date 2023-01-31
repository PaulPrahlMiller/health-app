package com.hkr.health

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.hkr.health.HealthDestinations.ANALYSIS_ROUTE
import com.hkr.health.HealthDestinations.HOME_ROUTE
import com.hkr.health.HealthDestinations.QUESTIONS_ROUTE
import com.hkr.health.HealthDestinations.QUESTION_ITEM_ROUTE

class HealthNavigationActions(private val navController: NavController) {

    fun navigateToHome() {
        navigateWithPopSingleTop(HOME_ROUTE)
    }

    fun navigateToQuestions() {
        navigateWithPopSingleTop(QUESTIONS_ROUTE)
    }

    fun navigateToAnalysis() {
        navigateWithPopSingleTop(ANALYSIS_ROUTE)
    }

    fun navigateToQuestionItem(category: String) {
        navController.navigate("$QUESTION_ITEM_ROUTE/$category")
    }

    private fun navigateWithPopSingleTop(route: String) {
        navController.navigate(route){
            // Pop up to start destination to prevent building
            // a large stack of destinations
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination on
            // top of the nav stack
            launchSingleTop = true
            // Restore state when reselecting a previously selected route
            restoreState = true
        }
    }
}

object HealthDestinations {
    const val HOME_ROUTE = "home"
    const val QUESTIONS_ROUTE = "questions"
    const val ANALYSIS_ROUTE = "analysis"
    const val QUESTION_ITEM_ROUTE = "question"
    const val QUESTION_ITEM_ARG = "category"
}

