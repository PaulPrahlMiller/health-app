package com.hkr.health

import android.content.Context
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.text.nlclassifier.BertNLClassifier

class MobileBertClassifier(
    val context: Context
) {

    private lateinit var bertClassifier: BertNLClassifier

    fun init(): BertNLClassifier{
        val baseOptions = BaseOptions.builder().build()

        val options = BertNLClassifier.BertNLClassifierOptions
            .builder()
            .setBaseOptions(baseOptions)
            .build()

        return BertNLClassifier.createFromFileAndOptions(context, MOBILEBERT, options)
    }

    companion object {
        const val MOBILEBERT = "mobilebert.tflite"
    }
}