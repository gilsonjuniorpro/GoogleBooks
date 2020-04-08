package com.googlebooks.ca

import com.googlebooks.ca.model.BookHttp
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun googleBooksApiTest() {
        val searchResult = BookHttp.searchBook("Dominando o android")
        searchResult?.items?.forEach{
            println(it.volumeInfo.title)
            //println(it)
        }
        //assertTrue(searchResult!!.items[0].volumeInfo.title.contains("Dominando o android"))
    }
}
