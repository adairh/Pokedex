

package me.sjihh.pokedex.core.network

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.SandwichInitializer
import com.skydoves.sandwich.retrofit.responseOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest {

  @Test
  fun exception() {
    val exception = Exception("foo")
    val apiResponse = ApiResponse.exception(exception)
    assertThat(apiResponse.message, `is`("foo"))
  }

  @Test
  fun success() {
    val apiResponse =
      ApiResponse.responseOf(SandwichInitializer.successCodeRange) { Response.success("foo") }
    if (apiResponse is ApiResponse.Success) {
      assertThat(apiResponse.data, `is`("foo"))
    }
  }
}
