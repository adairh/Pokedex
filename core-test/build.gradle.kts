

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  compileSdk = 30
  buildToolsVersion = "29.0.2"
  namespace = "me.sjihh.pokedex.core.test"
}

dependencies {
  implementation(projects.coreModel)
  implementation(libs.coroutines)
  implementation(libs.coroutines.test)
  implementation(libs.junit)
}