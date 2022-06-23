package com.rago.gradle

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.internal.DefaultJavaPluginExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


class MyWeatherPlugin : Plugin<Project> {
    object Versions {
        const val CORE_KTX_VERSION = "1.8.0"
        const val COMPOSE_VERSION = "1.2.0-rc01"
        const val NAV_VERSION = "2.4.2"
        const val HILT_VERSION = "2.42"
        const val LIFECYCLE_VIEW_MODEL_VERSION = "2.4.1"
    }

    override fun apply(target: Project) {

        when (target.name) {
            "app" -> {
                //ANDROID
                target.configPluginsAndroid()
                val androidException = target.extensions.getByName("android")
                println(androidException)
                if (androidException is BaseExtension) {
                    androidException.apply {
                        compileSdkVersion(32)
                        defaultConfig {
                            targetSdk = 32
                            minSdk = 26

                            versionCode = 1
                            versionName = "1.0.0"

                            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                        }
                        val proguardFile = "proguard-rules.pro"
                        when (this) {
                            is LibraryExtension -> defaultConfig {
                                consumerProguardFile(proguardFile)
                            }

                            is AppExtension -> buildTypes {
                                getByName("release") {
                                    isMinifyEnabled = true
                                    proguardFiles(
                                        getDefaultProguardFile("proguard-android-optimize.txt"),
                                        proguardFile
                                    )
                                }

                                getByName("debug") {
                                    isTestCoverageEnabled = true
                                }
                            }
                        }

                        compileOptions {
                            sourceCompatibility = JavaVersion.VERSION_1_8
                            targetCompatibility = JavaVersion.VERSION_1_8
                        }

                        target.tasks.withType(KotlinCompile::class.java).configureEach {
                            kotlinOptions {
                                jvmTarget = "1.8"
                            }
                        }
                        composeOptions.kotlinCompilerExtensionVersion = Versions.COMPOSE_VERSION

                        buildFeatures.compose = true

                        packagingOptions {
                            resources {
                                excludes += "/META-INF/{AL2.0,LGPL2.1}"
                            }
                        }
                    }
                }
                target.dependencies {
                    configAndroid()
                }
            }
            else -> {
                // LIBS
                target.plugins.apply("java-library")
                target.plugins.apply("org.jetbrains.kotlin.jvm")
                val javaExtension = target.extensions.getByName("java")

                if (javaExtension is DefaultJavaPluginExtension) {
                    javaExtension.apply {
                        sourceCompatibility = JavaVersion.VERSION_1_8
                        targetCompatibility = JavaVersion.VERSION_1_8
                    }
                }

                target.dependencies {
                    configLibs()
                }
            }
        }
        target.dependencies {
            configGeneral()
        }
    }
}

private fun DependencyHandlerScope.configAndroid() {

    // MODULES -- CLEAN ARCHITECTURE
    add(
        "implementation",
        project(mapOf("path" to ":domain"))
    )
    add(
        "implementation",
        project(mapOf("path" to ":data"))
    )

    // ANDROID
    add("implementation", "androidx.core:core-ktx:${MyWeatherPlugin.Versions.CORE_KTX_VERSION}")
    add("implementation", "com.squareup.okhttp3:logging-interceptor:4.10.0")
    add("implementation", "com.squareup.retrofit2:converter-gson:2.9.0")
    add("implementation", "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    add("implementation", "androidx.activity:activity-compose:1.4.0")
    add("testImplementation", "junit:junit:4.13.2")
    add("androidTestImplementation", "androidx.test.ext:junit:1.1.3")
    add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.4.0")

    //COMPOSE
    add("implementation", "androidx.compose.ui:ui:${MyWeatherPlugin.Versions.COMPOSE_VERSION}")
    add(
        "implementation",
        "androidx.compose.material:material:${MyWeatherPlugin.Versions.COMPOSE_VERSION}"
    )
    add(
        "implementation",
        "androidx.compose.ui:ui-tooling-preview:${MyWeatherPlugin.Versions.COMPOSE_VERSION}"
    )

    add(
        "androidTestImplementation",
        "androidx.compose.ui:ui-test-junit4:${MyWeatherPlugin.Versions.COMPOSE_VERSION}"
    )
    add(
        "debugImplementation",
        "androidx.compose.ui:ui-tooling:${MyWeatherPlugin.Versions.COMPOSE_VERSION}"
    )
    add(
        "debugImplementation",
        "androidx.compose.ui:ui-test-manifest:${MyWeatherPlugin.Versions.COMPOSE_VERSION}"
    )

    // NAVIGATION
    add(
        "implementation",
        "androidx.navigation:navigation-compose:${MyWeatherPlugin.Versions.NAV_VERSION}"
    )

    // HILT
    add("implementation", "androidx.hilt:hilt-navigation-compose:1.0.0")
    add("kapt", "androidx.hilt:hilt-compiler:1.0.0")
    add("implementation", "com.google.dagger:hilt-android:${MyWeatherPlugin.Versions.HILT_VERSION}")
    add("kapt", "com.google.dagger:hilt-compiler:${MyWeatherPlugin.Versions.HILT_VERSION}")

    // VIEW_MODEL
    add(
        "implementation",
        "androidx.lifecycle:lifecycle-viewmodel-compose:${MyWeatherPlugin.Versions.LIFECYCLE_VIEW_MODEL_VERSION}"
    )
    add(
        "implementation",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${MyWeatherPlugin.Versions.LIFECYCLE_VIEW_MODEL_VERSION}"
    )

}

private fun DependencyHandlerScope.configGeneral() {
    add("implementation", "com.squareup.retrofit2:retrofit:2.9.0")
    add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
}

private fun DependencyHandlerScope.configLibs() {
    add("implementation", "javax.inject:javax.inject:1")
    add("implementation", "com.google.code.gson:gson:2.9.0")
}

private fun Project.configPluginsAndroid() {
    this.plugins.apply("com.android.application")
    this.plugins.apply("org.jetbrains.kotlin.android")
    this.plugins.apply("kotlin-android")
    this.plugins.apply("kotlin-kapt")
    this.plugins.apply("kotlin-parcelize")
    this.plugins.apply("dagger.hilt.android.plugin")
}