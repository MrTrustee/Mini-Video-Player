plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.minivideoplayer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.minivideoplayer"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    val exoplayerVersion = "1.6.1"
    // Core ExoPlayer library
    implementation("androidx.media3:media3-exoplayer:$exoplayerVersion")
    // Common utilities (recommended)
    implementation("androidx.media3:media3-common:$exoplayerVersion")
    // UI components
    implementation("androidx.media3:media3-ui:$exoplayerVersion")
    // DASH support
    implementation("androidx.media3:media3-exoplayer-dash:$exoplayerVersion")
    // HLS support
    implementation("androidx.media3:media3-exoplayer-hls:$exoplayerVersion")
    // Smooth Streaming support
    implementation("androidx.media3:media3-exoplayer-smoothstreaming:$exoplayerVersion")
    // Optional: Extractor (for progressive formats like MP4, MP3)
    implementation("androidx.media3:media3-exoplayer-rtsp:$exoplayerVersion")



}