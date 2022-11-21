/*
 * Copyright 2020 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.example.realtimebraillescanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.realtimebraillescanner.CameraHTBFragment.Companion.DESIRED_HEIGHT_CROP_PERCENT
import com.example.realtimebraillescanner.CameraHTBFragment.Companion.DESIRED_WIDTH_CROP_PERCENT
import com.example.realtimebraillescanner.util.SmoothedMutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // TODO Instantiate LanguageIdentification
//    private val languageIdentifier = LanguageIdentification.getClient()

//    val targetLang = MutableLiveData<Language>()
    val sourceText = SmoothedMutableLiveData<String>(SMOOTHING_DURATION)

    // We set desired crop percentages to avoid having to analyze the whole image from the live
    // camera feed. However, we are not guaranteed what aspect ratio we will get from the camera, so
    // we use the first frame we get back from the camera to update these crop percentages based on
    // the actual aspect ratio of images.
    val imageCropPercentages = MutableLiveData<Pair<Int, Int>>()
        .apply { value = Pair(DESIRED_HEIGHT_CROP_PERCENT, DESIRED_WIDTH_CROP_PERCENT) }
    val translatedText = SmoothedMutableLiveData<String>(SMOOTHING_DURATION)
    val braille = SmoothedMutableLiveData<String>(SMOOTHING_DURATION)

//    val translatedText = MediatorLiveData<ResultOrError>()
//    private val translating = MutableLiveData<Boolean>()
//    val modelDownloading = SmoothedMutableLiveData<Boolean>(SMOOTHING_DURATION)

//    private var modelDownloadTask: Task<Void> = Tasks.forCanceled()

//    private val translators =
//        object : LruCache<TranslatorOptions, Translator>(NUM_TRANSLATORS) {
//            override fun create(options: TranslatorOptions): Translator {
//                return Translation.getClient(options)
//            }
//
//            override fun entryRemoved(
//                evicted: Boolean,
//                key: TranslatorOptions,
//                oldValue: Translator,
//                newValue: Translator?
//            ) {
//                oldValue.close()
//            }
//        }

//    val sourceLang = Transformations.switchMap(sourceText) { text ->
//        val result = MutableLiveData<Language>()
//
//        // TODO  Call the language identification method and assigns the result if it is not
//        //  undefined (“und”)
//
//        languageIdentifier.identifyLanguage(text)
//            .addOnSuccessListener { languageCode ->
//                if (languageCode != "und")
//                    result.value = Language(languageCode)
//            }
//        result
//    }

    override fun onCleared() {
        // TODO Shut down ML Kit clients.
//        languageIdentifier.close()
//        translators.evictAll()
    }


//    private fun translate(): Task<String> {
//
//        // TODO Take the source language value, target language value, and the source text and
//        //  perform the translation.
//        //  If the chosen target language model has not been downloaded to the device yet,
//        //  call downloadModelIfNeeded() and then proceed with the translation.
//
//        val text = sourceText.value
//        val source = sourceLang.value
//        val target = targetLang.value
//        if (modelDownloading.value != false || translating.value != false) {
//            return Tasks.forCanceled()
//        }
//        if (source == null || target == null || text == null || text.isEmpty()) {
//            return Tasks.forResult("")
//        }
//        val sourceLangCode = TranslateLanguage.fromLanguageTag(source.code)
//        val targetLangCode = TranslateLanguage.fromLanguageTag(target.code)
//        if (sourceLangCode == null || targetLangCode == null) {
//            return Tasks.forCanceled()
//        }
//        val options = TranslatorOptions.Builder()
//            .setSourceLanguage(sourceLangCode)
//            .setTargetLanguage(targetLangCode)
//            .build()
//        val translator = translators[options]
//        modelDownloading.setValue(true)

        // Register watchdog to unblock long running downloads
//        Handler().postDelayed({ modelDownloading.setValue(false) }, 15000)
//        modelDownloadTask = translator.downloadModelIfNeeded().addOnCompleteListener {
//            modelDownloading.setValue(false)
//        }
//        translating.value = true
//        return modelDownloadTask.onSuccessTask {
//            translator.translate(text)
//        }.addOnCompleteListener {
//            translating.value = false
//        }
//    }

////     Gets a list of all available translation languages.
//    val availableLanguages: List<Language> = TranslateLanguage.getAllLanguages()
//        .map { Language(it) }

//    init {
////        modelDownloading.setValue(false)
//        translating.value = false
//        // Create a translation result or error object.
//        val processTranslation =
//            OnCompleteListener<String> { task ->
//                if (task.isSuccessful) {
//                    translatedText.value = ResultOrError(task.result, null)
//                } else {
//                    if (task.isCanceled) {
//                        // Tasks are cancelled for reasons such as gating; ignore.
//                        return@OnCompleteListener
//                    }
//                    translatedText.value = ResultOrError(null, task.exception)
//                }
//            }
//        // Start translation if any of the following change: detected text, source lang, target lang.
////        translatedText.addSource(sourceText) { translate().addOnCompleteListener(processTranslation) }
////        translatedText.addSource(sourceLang) { translate().addOnCompleteListener(processTranslation) }
////        translatedText.addSource(targetLang) { translate().addOnCompleteListener(processTranslation) }
//    }

    companion object {
        // Amount of time (in milliseconds) to wait for detected text to settle
        private const val SMOOTHING_DURATION = 50L

//        private const val NUM_TRANSLATORS = 1
    }
}