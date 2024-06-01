@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    // fetch plugins from google maven (https://maven.google.com)
    google() {
      content {
        includeGroupByRegex("androidx\\..*")
        includeGroupByRegex("com\\.android(\\..*|)")
        includeGroupByRegex("com\\.google\\.android\\..*")
        includeGroupByRegex("com\\.google\\.firebase(\\..*|)")
        includeGroupByRegex("com\\.google\\.gms(\\..*|)")
        includeGroupByRegex("com\\.google\\.mlkit")
        includeGroupByRegex("com\\.google\\.oboe")
        includeGroupByRegex("com\\.google\\.prefab")
        includeGroupByRegex("com\\.google\\.testing\\.platform")
      }
      mavenContent {
        releasesOnly()
      }
    }

    // fetch dagger plugin only
    mavenCentral() {
      content {
        includeGroup("com.google.dagger")
        includeGroup("com.google.dagger.hilt.android")
      }
      mavenContent {
        releasesOnly()
      }
    }

    // fetch plugins from gradle plugin portal (https://plugins.gradle.org)
    gradlePluginPortal()

    // fetch snapshot plugins from sonatype
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
      mavenContent {
        snapshotsOnly()
      }
    }
  }
}

plugins {
  id("com.android.settings") version "8.4.0"
}

android {
  minSdk = 23
  compileSdk = 34
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    // fetch libraries from google maven (https://maven.google.com)
    google() {
      content {
        includeGroupByRegex("androidx\\..*")
        includeGroupByRegex("com\\.android(\\..*|)")
        includeGroupByRegex("com\\.google\\.android\\..*")
        includeGroupByRegex("com\\.google\\.firebase(\\..*|)")
        includeGroupByRegex("com\\.google\\.gms(\\..*|)")
        includeGroupByRegex("com\\.google\\.mlkit")
        includeGroupByRegex("com\\.google\\.oboe")
        includeGroupByRegex("com\\.google\\.prefab")
        includeGroupByRegex("com\\.google\\.testing\\.platform")
      }
      mavenContent {
        releasesOnly()
      }
    }

    // fetch libraries from jcenter
    // https://jfrog.com/blog/into-the-sunset-bintray-jcenter-gocenter-and-chartcenter/
    // FIXME: Need to clean up jcenter usage
    @Suppress("DEPRECATION")
    jcenter() {
      content {
        includeGroupByRegex("com\\.github\\.florent37")
      }
      mavenContent {
        releasesOnly()
      }
    }

    // fetch libraries from maven central
    mavenCentral() {
      mavenContent {
        releasesOnly()
      }
    }

    // fetch snapshot libraries from sonatype
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
      mavenContent {
        snapshotsOnly()
      }
    }
  }
}
rootProject.name = "Pokedex"
include(":app")
include(":benchmark")
include(":core-model")
include(":core-network")
include(":core-database")
include(":core-data")
include(":core-test")