

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.android.test) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.kapt) apply false
  alias(libs.plugins.kotlin.parcelize) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.hilt.plugin) apply false
  alias(libs.plugins.spotless)
  alias(libs.plugins.google.gms.google.services) apply false
}
val sourceCompatibility by extra(11)

private typealias AndroidExtension = com.android.build.api.dsl.CommonExtension<*, *, *, *, *, *>

private val Project.androidExtension: AndroidExtension
    get() = extensions.getByType(com.android.build.api.dsl.CommonExtension::class.java)

private fun Project.android(block: AndroidExtension.() -> Unit) {
  plugins.withType<com.android.build.gradle.BasePlugin>().configureEach {
    androidExtension.block()
  }
}

private val targetSdkVersion = libs.versions.targetSdk.get().toInt()
private val bytecodeVersion = JavaVersion.toVersion(libs.versions.jvmBytecode.get())

subprojects {
  apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)

  // Common Android configurations
  android {
    defaultConfig {
      vectorDrawables.useSupportLibrary = true
    }

    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
      abortOnError = false
    }
  }

  // Configurations for `com.android.application` plugin
  plugins.withType<com.android.build.gradle.AppPlugin>().configureEach {
    extensions.configure<com.android.build.api.dsl.ApplicationExtension> {
      defaultConfig {
        targetSdk = targetSdkVersion
      }
    }
  }

  // Configurations for `com.android.test` plugin
  plugins.withType<com.android.build.gradle.TestPlugin>().configureEach {
    extensions.configure<com.android.build.api.dsl.TestExtension> {
      defaultConfig {
        targetSdk = targetSdkVersion
      }
    }
  }

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = bytecodeVersion.toString()
    kotlinOptions.freeCompilerArgs += listOf(
      "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
      "-opt-in=kotlin.time.ExperimentalTime",
    )
  }

  extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    val buildDirectory = layout.buildDirectory.asFileTree
    kotlin {
      target("**/*.kt")
      targetExclude(buildDirectory)
      ktlint().editorConfigOverride(
        mapOf(
          "indent_size" to "2",
          "continuation_indent_size" to "2"
        )
      )
      licenseHeaderFile(rootProject.file("spotless/spotless.license.kt"))
      trimTrailingWhitespace()
      endWithNewline()
    }
    format("kts") {
      target("**/*.kts")
      targetExclude(buildDirectory)
      licenseHeaderFile(rootProject.file("spotless/spotless.license.kt"), "(^(?![\\/ ]\\*).*$)")
    }
    format("xml") {
      target("**/*.xml")
      targetExclude(buildDirectory)
      licenseHeaderFile(rootProject.file("spotless/spotless.license.xml"), "(<[^!?])")
    }
  }
}
