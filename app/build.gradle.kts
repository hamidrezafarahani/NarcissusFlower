@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrain.kotlin.android)
    alias(libs.plugins.androidx.navigation.safeargs.kotlin)
    alias(libs.plugins.google.dagger.hilt.android)
    kotlin("kapt")
}

android {
    namespace = "com.example.narcissusflower"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.narcissusflower"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }

        create("develop") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".develop"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.viewpager2)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    kapt(libs.androidx.lifecycle.compiler)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.androidx.paging.runtime)

    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.google.dagger.hilt.android)
    kapt(libs.google.dagger.hilt.compiler)

    implementation(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.compiler)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.ktx)

    implementation(libs.google.gson)

    implementation(libs.squareup.okhttp)
    implementation(libs.squareup.okhttp.logging.interceptor)

    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.converter.gson)

    implementation(libs.bumptech.glide)

    implementation(libs.facebook.shimmer)

    implementation(libs.jakewharton.timber)
}

kapt {
    correctErrorTypes = true
}