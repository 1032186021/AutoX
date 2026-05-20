plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
}

android {
    compileSdk = versions.compile

    defaultConfig {
        applicationId = "com.saltfish.assistant"
        minSdk = versions.mini
        targetSdk = versions.target
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }

    lint {
        abortOnError = false
    }

    buildTypes {
        named("debug") {
            buildConfigField("String", "API_BASE_URL", "\"http://192.168.68.217:8001\"")
            buildConfigField("String", "WS_URL", "\"http://192.168.68.217:8001/socket\"")
            buildConfigField("String", "SCRIPT_VERSION", "\"1.0.0\"")
        }
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            buildConfigField("String", "API_BASE_URL", "\"https://tool-admin.yunibobo.com/api\"")
            buildConfigField("String", "WS_URL", "\"https://tool-admin.yunibobo.com/socket\"")
            buildConfigField("String", "SCRIPT_VERSION", "\"1.0.0\"")
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }

    namespace = "com.saltfish.assistant"
}

dependencies {
    // AutoX core modules
    implementation(project(":autojs"))
    implementation(project(":automator"))
    implementation(project(":common"))

    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.activity.compose)

    // Lifecycle
    val lifecycleVersion = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-service:$lifecycleVersion")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Room
    val roomVersion = "2.4.3"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(libs.okhttp)

    // Socket.IO
    implementation("io.socket:socket.io-client:2.0.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.2.2")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}
