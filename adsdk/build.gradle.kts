plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.sample.adsdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //ads
    implementation ("com.google.android.gms:play-services-ads:22.5.0")
//    implementation 'com.google.android.gms:play-services-ads-lite:22.1.0'

    //Billing
    implementation("com.android.billingclient:billing:6.0.1")

//    implementation platform('com.google.firebase:firebase-bom:30.2.0')
//    implementation 'com.google.firebase:firebase-config-ktx'
//    implementation 'com.google.firebase:firebase-analytics-ktx'
//    //9449implementation 'com.google.firebase:firebase-crashlytics-ktx'
//    implementation 'com.google.firebase:firebase-messaging-ktx'

    implementation ("com.intuit.ssp:ssp-android:1.1.0")
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
}