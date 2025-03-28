plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("de.mannodermaus.android-junit5")
    id("tech.apter.junit5.jupiter.robolectric-extension-gradle-plugin") version ("0.9.0")
}

android {
    namespace = "com.tarappo.androidtestsamplecode"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tarappo.androidtestsamplecode"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)

    // (Optional) If you need "Parameterized Tests"
    testImplementation(libs.junit.jupiter.params)

    // (Optional) If you also have JUnit 4-based tests
    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.vintage.engine)

    // Instrumented Test
    androidTestImplementation(libs.ui.test.junit4)

    // Unit Test
    testImplementation((libs.ui.test.junit4))
    testImplementation(libs.ui.test.manifest)
    debugImplementation(libs.ui.test.manifest)
}
