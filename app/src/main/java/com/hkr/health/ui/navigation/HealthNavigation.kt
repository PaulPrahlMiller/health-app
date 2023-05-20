package com.hkr.health.ui.navigation

import androidx.navigation.NavController
import com.hkr.health.ui.navigation.HealthDestinations.ANALYSIS_ROUTE
import com.hkr.health.ui.navigation.HealthDestinations.HOME_ROUTE
import com.hkr.health.ui.navigation.HealthDestinations.QUESTIONS_ROUTE
import com.hkr.health.ui.navigation.HealthDestinations.QUESTION_ITEM_ROUTE

class HealthNavigationActions(private val navController: NavController) {

    fun navigateToHome() {
        navController.navigate(HOME_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToQuestions() {
        navController.navigate(QUESTIONS_ROUTE){
            launchSingleTop = true
        }
    }

    fun navigateToAnalysis() {
        navController.navigate(ANALYSIS_ROUTE){
            launchSingleTop = true
        }
    }

    fun navigateToQuestionItem(category: String) {
        navController.navigate("$QUESTION_ITEM_ROUTE/$category")
    }

    fun popUpToQuestions() {
        navController.navigate(QUESTIONS_ROUTE) {
            launchSingleTop = true
            popUpTo(QUESTIONS_ROUTE)
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

