package cl.serlitoral.pasteleriapanquecitotd

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import cl.serlitoral.pasteleriapanquecitotd.domnain.db.CakeDAO
import cl.serlitoral.pasteleriapanquecitotd.domnain.db.CakeDatabase
import cl.serlitoral.pasteleriapanquecitotd.domnain.db.CakeEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CakeDatabaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var dao: CakeDAO
    private lateinit var db: CakeDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(context, CakeDatabase::class.java)
            .build()

        dao = db.cakeDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry
            .getInstrumentation()
            .targetContext

        assertThat(appContext.packageName).isEqualTo("cl.serlitoral.pasteleriapanquecitotd")
    }

    @Test
    fun insertCake_empty() = runBlocking {
        //Give
        val cakeList = listOf<CakeEntity>()

        //When
        dao.insertCakes(cakeList)

        //Then
        dao.getCakes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }
}