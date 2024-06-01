

package com.github.skydoves.benchmark

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test

/**
 * Generates a baseline profile which can be copied to `app/src/main/baseline-prof.txt`.
 */
@RequiresApi(Build.VERSION_CODES.P)
class BaselineProfileGenerator {
  @get:Rule
  val baselineProfileRule = BaselineProfileRule()

  @Test
  fun startup() = baselineProfileRule.collect(
    packageName = PACKAGE_NAME,
    stableIterations = 2,
    maxIterations = 8,
  ) {
    pressHome()
    // This block defines the app's critical user journey. Here we are interested in
    // optimizing for app startup. But you can also navigate and scroll
    // through your most important UI.
    startActivityAndWait()
    device.waitForIdle()

    // Navigate to the details screen
    device.testDiscover() || return@collect
    device.navigateFromMainToDetails()
    device.pressBack()
  }
}

private fun UiDevice.testDiscover(): Boolean {
  // UI automator library has an issue about scrolling down.
  //  waitForObject(By.res(packageName, "recyclerView")).scroll(Direction.DOWN, 1f).
  return wait(Until.hasObject(By.res(PACKAGE_NAME, "transformationLayout")), 1_000)
}

private fun UiDevice.navigateFromMainToDetails() {
  // Open a show from one of the carousels
  waitForObject(By.res(PACKAGE_NAME, "transformationLayout")).click()
  wait(Until.hasObject(By.res(PACKAGE_NAME, "nestedScroll")), 1_000)
  waitForObject(By.res(PACKAGE_NAME, "nestedScroll")).scroll(Direction.DOWN, 1f)
  waitForIdle()
  pressBack()
}

private fun UiDevice.waitForObject(selector: BySelector, timeout: Long = 5_000): UiObject2 {
  if (wait(Until.hasObject(selector), timeout)) {
    return findObject(selector)
  }

  error("Object with selector [$selector] not found")
}
