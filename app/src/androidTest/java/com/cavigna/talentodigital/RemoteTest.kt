package com.cavigna.talentodigital

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.cavigna.talentodigital.model.models.CoinDetails
import com.cavigna.talentodigital.model.models.D
import com.cavigna.talentodigital.model.remopte.api.ApiService
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class RemoteTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)


    private val server = MockWebServer()

    private lateinit var service: ApiService

    private val coinDetailsMock = CoinDetails(
        "ETH","ETH","ETH","Ethereum",
        "https://s3.us-east-2.amazonaws.com/nomics-api/static/images/currencies/eth.svg",
        "active","3538.44529165","2021-05-08T00:00:00Z",
        "2021-05-08T06:15:00Z","115785365","409700180507",
        "387","40629","25629",  "2015-08-07T00:00:00Z",
        "2015-08-07T00:00:00Z","2018-08-29T00:00:00Z","2","0",
        "3545.46612020","2021-05-08T00:00:00Z",
        D(
            "59414294288.88", "99.40150754", "0.0289",
            "-3886350195.15", "-0.0614", "11555867487.59",
            "0.0290"
        )
    )

    @Before
    fun init() {
        server.start(8000)

        val BASE_URL = server.url("/").toString()

        service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(ApiService::class.java)

        server.apply {
            enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("mock_response.json"))
            )
        }
    }

    @After
    fun teardown() {
        server.shutdown()

    }


    @Test
    fun testApiSuccess() {
        testScope.launch {
            val coinDetails = service.fetchCoinDetails("ETH")
            assertThat(coinDetailsMock).isEqualTo(coinDetails)
        }
    }


}