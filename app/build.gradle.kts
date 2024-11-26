plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
//    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.drawingapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.drawingapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    testOptions {
        unitTests {
            // To configure JVM args for unit tests
            isIncludeAndroidResources = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }


}

dependencies {
    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // Core Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.yukuku.ambilwarna)

    // Activity & Compose
    implementation("androidx.activity:activity-compose:1.9.2") // Use consistent version
    implementation(platform(libs.androidx.compose.bom)) // Ensure Compose version consistency
    implementation("androidx.compose.ui:ui:1.7.2")
    implementation("androidx.compose.ui:ui-graphics:1.7.2")
    implementation("androidx.compose.material3:material3:1.3.0")

    // Lifecycle & ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.runtime.livedata)

    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-common:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation(libs.core.ktx)
    implementation(libs.androidx.navigation.testing)
    testImplementation(libs.androidx.ui.test.junit4.android)
    ksp("androidx.room:room-compiler:2.6.1")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation("org.mockito:mockito-core:5.5.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0")
    androidTestImplementation("androidx.compose.ui:ui-test-manifest:1.0.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testImplementation("org.mockito:mockito-android:5.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:<compose-version>")
    androidTestImplementation("androidx.compose.ui:ui-test-manifest:<compose-version>")
    testImplementation("org.powermock:powermock-module-junit4:2.0.9")
    ksp("com.google.devtools.ksp:symbol-processing-api:1.9.10-1.0.13")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
    testImplementation("org.powermock:powermock-module-junit4:2.0.0")
    testImplementation("org.mockito:mockito-core:3.11.2")
    testImplementation("org.powermock:powermock-api-mockito2:2.0.9")
    testImplementation("junit:junit:4.13.2")


    // Debugging
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Test Libraries
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("androidx.test:rules:1.6.1")

    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("androidx.compose.ui:ui:1.0.0")
    implementation("androidx.compose.material:material:1.0.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.0")

}

//dependencies {
//    implementation(libs.androidx.navigation.compose)
//    androidTestImplementation(libs.androidx.core)
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    implementation(libs.core.ktx)
//    implementation(libs.androidx.junit.ktx)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
//    implementation(libs.androidx.fragment.ktx)
//    implementation(libs.androidx.core.splashscreen)
//    implementation(libs.yukuku.ambilwarna)
//    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
//    androidTestImplementation("androidx.test:runner:1.6.2")
//    androidTestImplementation("androidx.test:rules:1.6.1")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:{latest_version}")
//    implementation(libs.androidx.room.common)
//    implementation(libs.androidx.room.ktx)
//    implementation(libs.androidx.lifecycle.livedata.ktx)
//    implementation(libs.androidx.runtime.livedata)
//    implementation("androidx.room:room-runtime:2.5.0")
//
//
//    implementation("androidx.compose.ui:ui:1.7.2")
//    implementation("androidx.compose.ui:ui-graphics:1.7.2")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3:1.3.0")
//    ksp("androidx.room:room-compiler:2.6.1")
//    implementation("androidx.room:room-common:2.6.1")
//    implementation("androidx.room:room-ktx:2.6.1")
//    implementation("androidx.activity:activity-compose:1.9.2")
//    implementation("androidx.compose.runtime:runtime-livedata:1.7.2")
//}