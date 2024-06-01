

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.ksp)
}

android {
  namespace = "me.sjihh.pokedex.core.model"
}

dependencies {
  // json parsing
  implementation(libs.moshi)
  ksp(libs.moshi.codegen)

  // logger
  api(libs.timber)
}