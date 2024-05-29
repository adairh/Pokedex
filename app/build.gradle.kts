import com.android.build.gradle.internal.tasks.databinding.DataBindingGenBaseClassesTask
import com.skydoves.pokedex.Configuration
import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.ksp)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.hilt.plugin)
}

android {
  namespace = "com.skydoves.pokedex"

  defaultConfig {
    applicationId = "com.skydoves.pokedex"
    versionCode = Configuration.versionCode
    versionName = Configuration.versionName
    testInstrumentationRunner = "com.skydoves.pokedex.AppTestRunner"
  }

  buildFeatures {
    mlModelBinding = true
    dataBinding = true
    viewBinding = true
    buildConfig = true
  }

  hilt {
    enableAggregatingTask = true
  }

  kotlin {
    sourceSets.configureEach {
      kotlin.srcDir(layout.buildDirectory.files("generated/ksp/$name/kotlin/"))
    }
    sourceSets.all {
      languageSettings {
        languageVersion = "2.0"
      }
    }
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      isReturnDefaultValues = true
    }
  }

  buildTypes {
    create("benchmark") {
      isDebuggable = true
      signingConfig = getByName("debug").signingConfig
      matchingFallbacks += listOf("release")
    }
  }
}

androidComponents {
  onVariants(selector().all()) { variant ->
    afterEvaluate {
      val dataBindingTask =
        project.tasks.findByName("dataBindingGenBaseClasses" + variant.name.capitalized()) as? DataBindingGenBaseClassesTask
      if (dataBindingTask != null) {
        project.tasks.getByName("ksp" + variant.name.capitalized() + "Kotlin") {
          (this as AbstractKotlinCompileTool<*>).setSource(dataBindingTask.sourceOutFolder)
        }
      }
    }
  }
}

dependencies {
  // modules
  implementation(project(":core-data"))
  implementation(libs.play.services.dtdi)
  implementation(libs.play.services.vision.common)
  implementation(libs.androidx.camera.lifecycle)
  implementation(libs.tensorflow.lite.support)
  implementation(libs.tensorflow.lite.metadata)
  implementation(libs.tensorflow.lite.gpu)
    implementation(libs.androidx.benchmark.common)
    implementation(libs.androidx.navigation.fragment)

    // modules for unit test
  testImplementation(project(":core-network"))
  testImplementation(project(":core-database"))
  testImplementation(project(":core-test"))
  androidTestImplementation(project(":core-test"))

  // androidx
  implementation(libs.material)
  implementation(libs.androidx.fragment)
  implementation(libs.androidx.lifecycle)
  implementation(libs.androidx.startup)

  // data binding
  implementation(libs.bindables)

  // di
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
  androidTestImplementation(libs.hilt.testing)
  kspAndroidTest(libs.hilt.compiler)

  // coroutines
  implementation(libs.coroutines)

  // whatIf
  implementation(libs.whatif)

  // image loading
  implementation(libs.glide)
  implementation(libs.glide.palette)

  // bundler
  implementation(libs.bundler)

  // transformation animation
  implementation(libs.transformationLayout)

  // recyclerView
  implementation(libs.recyclerview)
  implementation(libs.baseAdapter)

  // custom views
  implementation(libs.rainbow)
  implementation(libs.androidRibbon)
  implementation(libs.progressView)

  // unit test
  testImplementation(libs.junit)
  testImplementation(libs.turbine)
  testImplementation(libs.androidx.test.core)
  testImplementation(libs.mockito.core)
  testImplementation(libs.mockito.kotlin)
  testImplementation(libs.coroutines.test)
  androidTestImplementation(libs.truth)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso)
  androidTestImplementation(libs.android.test.runner)

  implementation("com.android.support:multidex:1.0.3")
  //Support Lib
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("androidx.exifinterface:exifinterface:1.0.0")
  implementation("androidx.legacy:legacy-support-v4:1.0.0")
  implementation("androidx.vectordrawable:vectordrawable:1.2.0")
  implementation("com.google.android.material:material:1.1.0-alpha02")
  implementation("androidx.cardview:cardview:1.0.0")

  //Other third party libs
  implementation("com.otaliastudios:cameraview:2.7.2")
  implementation("com.otaliastudios:cameraview:2.0.0-rcl")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")

  implementation("androidx.camera:camera-camera2:1.2.2")
  implementation("androidx.camera:camera-lifecycle:1.2.2")
  implementation("androidx.camera:camera-view:1.2.2")


  // Persistence
  implementation("androidx.room:room-runtime:2.5.0")
  kapt("androidx.room:room-compiler:2.5.0")

  // Koin
  implementation("io.insert-koin:koin-android:3.2.0")
  implementation("io.insert-koin:koin-core:3.2.0")


  // Retrofit
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")


  // Third Party
  implementation("com.leinardi.android:speed-dial:3.3.0")

  implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
  implementation("androidx.navigation:navigation-ui-ktx:2.3.5")


}
