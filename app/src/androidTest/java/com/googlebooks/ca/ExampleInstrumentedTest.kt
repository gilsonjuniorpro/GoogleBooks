package com.googlebooks.ca

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.googlebooks.ca.repository.AppDatabase
import com.googlebooks.ca.repository.Book
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val db = AppDatabase.getDatabase(appContext)
        val dao = db.getBookDao()

        runBlocking {
            val bookTest = Book(
                "MY_ID",
                "http://my_link",
                "My Book Title",
                "my description",
                listOf("I", "You", "Other"),
                "Novatec",
                "2019-03-01",
                145,
                "http://url_smallthumbnail",
                "http://url_thumbnail"
            )

            val rowId = dao.save(bookTest)
            assertTrue(rowId > -1)

            val books = dao.allFavorites().first()
            books.forEach{
                assertEquals(it.title, bookTest.title)
            }

            val isFavorite = dao.isFavorite(bookTest.id)
            assertTrue(isFavorite == 1)

            val result = dao.delete(bookTest)
            assertTrue(result == 1)
        }
    }
}
