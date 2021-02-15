/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve a binding object that allows you to refer to views by id name
        // Names are converted from snake case to camel case.
        // For example, a View with the id word_one is referenced as binding.wordOne
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the LETTER from the Intent extras
        // intent.extras.getString returns String? (String or null)
        // so toString() guarantees that the value will be a String

        /*
        Both of these properties are marked with a question mark. Why is this?
        The reason is that the intent and extras properties are nullable, meaning they may or may not have a value.
        Sometimes you may want a variable to be null. The intent property might not actually be an Intent
        (if the activity wasn't launched from an intent) and the extras property might not actually be a Bundle,
        but rather a value called null. In Kotlin, null means the absence of a value. The object may exist or it
        may be null. If your app tries to access a property or call a function on a null object, the app will crash.
        To safely access this value, you put a ? after the name. If intent is null, your app won't even attempt to
        access the extras property, and if extras is null, your code won't even attempt to call getString().
        */
        val letterId = intent?.extras?.getString("letter").toString()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId, this)

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + letterId
    }

    companion object {
        val LETTER = "letter"
        val SEARCH_PREFIX = "https://www.google.com/search?q="
    }
}