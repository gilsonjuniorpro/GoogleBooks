package com.googlebooks.ca.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    val items: List<Volume>
): Parcelable