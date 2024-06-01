

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "me.sjihh.pokedex.core.data"
}

dependencies {
  api(projects.coreModel)
  implementation(projects.coreNetwork)
  implementation(projects.coreDatabase)
  testImplementation(projects.coreTest)

  // coroutines
  implementation(libs.coroutines)
  testImplementation(libs.coroutines)
  testImplementation(libs.coroutines.test)

  // network
  implementation(libs.sandwich)

  // di
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)

  // unit test
  testImplementation(libs.junit)
  testImplementation(libs.turbine)
  testImplementation(libs.androidx.test.core)
  testImplementation(libs.mockito.core)
  testImplementation(libs.mockito.kotlin)
}
