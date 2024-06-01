
@file:OptIn(ExperimentalCoroutinesApi::class)

package me.sjihh.pokedex.core.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import me.sjihh.pokedex.core.test.MainCoroutinesRule
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.nio.charset.StandardCharsets

@RunWith(JUnit4::class)
abstract class ApiAbstract<T> {

  @Rule
  @JvmField
  val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  val coroutinesRule = MainCoroutinesRule()

  lateinit var mockWebServer: MockWebServer

  @Before
  fun mockServer() {
    mockWebServer = MockWebServer()
    mockWebServer.start()
  }

  @After
  fun stopServer() {
    mockWebServer.shutdown()
  }

  fun enqueueResponse(fileName: String) {
    enqueueResponse(fileName, emptyMap())
  }

  private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
    val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
    val source = inputStream.source().buffer()
    val mockResponse = MockResponse()
    for ((key, value) in headers) {
      mockResponse.addHeader(key, value)
    }
    mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
  }

  fun createService(clazz: Class<T>): T {
    return Retrofit.Builder()
      .baseUrl(mockWebServer.url("/"))
      .addConverterFactory(MoshiConverterFactory.create())
      .addCallAdapterFactory(
        ApiResponseCallAdapterFactory.create(
          coroutineScope = coroutinesRule.testScope,
        ),
      )
      .build()
      .create(clazz)
  }
}
