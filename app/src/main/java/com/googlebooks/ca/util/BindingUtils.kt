/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.googlebooks.ca.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.googlebooks.ca.R
import com.googlebooks.ca.model.Volume

@BindingAdapter("volumeImage")
fun ImageView.setVolumeImage(volume: Volume?) {
    if (volume != null) {
        if (volume.volumeInfo.imageLinks != null) {
            load(volume.volumeInfo.imageLinks?.smallThumbnail) {
                crossfade(true)
                crossfade(500)
                placeholder(R.drawable.image_not_found)
            }
        } else {
            setImageResource(R.drawable.image_not_found)
        }
    }
}

@BindingAdapter("title")
fun TextView.setTitle(volume: Volume?) {
    volume?.let {
        text = volume.volumeInfo.title
    }
}

@BindingAdapter("authors")
fun TextView.setAuthors(volume: Volume?) {
    volume?.let {
        text = volume.volumeInfo.authors?.joinToString() ?: ""
    }
}

@BindingAdapter("pages")
fun TextView.setPagess(volume: Volume?) {
    volume?.let {
        text = volume.volumeInfo.pageCount?.toString() ?: "-"
    }
}
