plugins {
    id("com.android.application") // Użycie `id()` zamiast aliasu
    id("org.jetbrains.kotlin.android") // Kotlin Android plugin
    id("com.google.gms.google-services") // Google Services plugin
}

android {
    namespace = "com.example.myapplication123"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication123"
        minSdk = 34
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // Użycie kompatybilnej wersji
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
        // Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2024.02.00")) // Zgodność z Kotlin 1.9.10



    //komponenty Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.7.2")


    // Compose
        implementation("androidx.activity:activity-compose:1.7.2")

        //Lifecycle
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

        // Debugowanie
        debugImplementation("androidx.compose.ui:ui-tooling")

    // Firebase Database
    implementation("com.google.firebase:firebase-database:21.0.0")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.0")
}
