
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "me.sjihh.pokedex.core.database"

  defaultConfig {
    // The schemas directory contains a schema file for each version of the Room database.
    // This is required to enable Room auto migrations.
    // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
    ksp {
      arg("room.schemaLocation", "$projectDir/schemas")
    }
  }

  sourceSets.getByName("test") {
    assets.srcDir(files("$projectDir/schemas"))
  }
}

dependencies {
  implementation(projects.coreModel)
  testImplementation(projects.coreTest)

  // coroutines
  implementation(libs.coroutines)
  testImplementation(libs.coroutines)
  testImplementation(libs.coroutines.test)

  // database
  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.room.ktx)
  ksp(libs.androidx.room.compiler)
  testImplementation(libs.androidx.arch.core)

  // json parsing
  implementation(libs.moshi)
  ksp(libs.moshi.codegen)

  // di
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)

  // unit test
  testImplementation(libs.junit)
  testImplementation(libs.androidx.test.core)
  testImplementation(libs.robolectric)
}