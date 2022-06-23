plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("my-weather-plugin") {
            id = "my-weather-plugin"
            implementationClass = "com.rago.gradle.MyWeatherPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies{
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:7.2.1")
    implementation(kotlin("gradle-plugin","1.6.21"))
    implementation(kotlin("android-extensions"))
    implementation("com.squareup:javapoet:1.13.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.42")
}