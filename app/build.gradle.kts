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
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    testOptions {
        unitTests {
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.ui:ui:1.7.2")
    implementation("androidx.compose.ui:ui-graphics:1.7.2")
    implementation("androidx.compose.material3:material3:1.3.0")

    // Lifecycle & ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.runtime.livedata)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-common:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation(libs.core.ktx)
    implementation(libs.androidx.navigation.testing)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.androidx.camera.core)
    implementation(libs.firebase.firestore.ktx)
    testImplementation(libs.androidx.ui.test.junit4.android)
    ksp("androidx.room:room-compiler:2.6.1")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation("org.mockito:mockito-core:4.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-manifest:1.0.0")
    testImplementation(libs.mockito.kotlin.v500)
    testImplementation(libs.mockito.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v121)
    androidTestImplementation(libs.androidx.espresso.core.v361)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:<compose-version>")
    debugImplementation("androidx.compose.ui:ui-test-manifest:<compose-version>")
    testImplementation(libs.powermock.powermock.module.junit4)
    ksp(libs.symbol.processing.api)
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.kotlin.stdlib)
    testImplementation(libs.powermock.powermock.module.junit4)
    testImplementation(libs.powermock.api.mockito2)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.inline)
    testImplementation("net.bytebuddy:byte-buddy-agent:1.15.4")
    testImplementation("org.mockito:mockito-core:3.7.7")
    testImplementation("org.powermock:powermock-core:1.7.4")
    testImplementation("org.powermock:powermock-module-testng:1.7.4")
    testImplementation("org.powermock:powermock-api-mockito2:1.7.4")


    // Debugging
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Test Libraries
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v361)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.rules)

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation(libs.firebase.auth.ktx)
    implementation(libs.androidx.ui.v100)
    implementation(libs.androidx.material)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx.v231)
    implementation(libs.androidx.activity.compose.v130)
    testImplementation("org.robolectric:robolectric:4.9")
    implementation(libs.ui.tooling)
    implementation(libs.androidx.compose.ui.ui2)

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