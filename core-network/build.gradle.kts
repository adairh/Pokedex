
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "me.sjihh.pokedex.core.network"
}

dependencies {
  implementation(projects.coreModel)
  testImplementation(projects.coreTest)

  // coroutines
  implementation(libs.coroutines)
  testImplementation(libs.coroutines)
  testImplementation(libs.coroutines.test)

  // network
  implementation(libs.sandwich)
  implementation(libs.retrofit)
  implementation(libs.retrofit.moshi)
  implementation(libs.okhttp.interceptor)
  testImplementation(libs.okhttp.mockserver)
  testImplementation(libs.androidx.arch.core)

  // json parsing
  implementation(libs.moshi)
  ksp(libs.moshi.codegen)

  // di
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}
