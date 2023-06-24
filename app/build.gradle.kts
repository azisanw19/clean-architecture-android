@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.canwar.baseproject"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.canwar.baseproject"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)

    /* Design Material */
    implementation(libs.material)

    /* Constraint Layout */
    implementation(libs.constraintlayout)

    /* Preference */
    implementation(libs.preference)

    /* Android testing */
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    /* Okhttp Test */
    testImplementation(libs.mockwebserver)

    /* Dependency Injection Dagger Hilt */
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    /* Retrofit and logging */
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    /* RxJava dan Adapter */
    implementation(libs.rxandroid)
    implementation(libs.rxadapter)

    /* Image view */
    implementation(libs.coil)

    /* Navigation */
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

}