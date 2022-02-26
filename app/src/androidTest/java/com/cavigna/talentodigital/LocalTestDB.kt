package com.cavigna.talentodigital

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.cavigna.talentodigital.model.local.db.BaseDeDatos
import com.cavigna.talentodigital.model.local.db.CoinDao
import com.cavigna.talentodigital.model.models.Coin
import com.cavigna.talentodigital.model.models.CoinDetails
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class LocalTestDB {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test.db")
    lateinit var db: BaseDeDatos

    private lateinit var  dao: CoinDao

    @Before
    fun init(){
        hiltRule.inject()
        dao = db.dao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `insertCoinTest`(): Unit = runBlockingTest{
        val coin = Coin("TSL", "TESLACoin")



        dao.insertListOfCoins(listOf(coin))

        val allCoins = dao.selectAllCoins()


        assertThat(allCoins).contains(coin)


    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertCoinDetailsTest() = runBlockingTest {
        val coinDetail = CoinDetails("TSLA")

        dao.insertCoinDetails(coinDetail)
        assertThat(dao.selectCoinDetails("TSLA")).isEqualTo(coinDetail)
    }
}