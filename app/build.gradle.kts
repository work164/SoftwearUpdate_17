plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlin-kapt")
    id("kotlin-parcelize")

}

android {
    signingConfigs {

        create("release") {
            storeFile = file("/appupdatekey.jks")
            storePassword = "appupdatekey"
            keyAlias = "key0"
            keyPassword = "appupdatekey"
        }

        create("debugs") {
            storeFile = file("/appupdatekey.jks")
            storePassword = "appupdatekey"
            keyAlias = "key0"
            keyPassword = "appupdatekey"
        }
    }
    namespace = "com.app.update.softwareupdatekkappsstudio"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.update.softwareupdatekkappsstudio"
        minSdk = 24
        targetSdk = 34
        versionCode = 17
        versionName = "2.7"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }








    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions += "default"
    productFlavors {
        create("appDev") {

            resValue("string", "app_id", "\"ca-app-pub-3940256099942544~3347511713\"")

            resValue("string", "app_open_l", "\"ca-app-pub-3940256099942544/3419835294\"")
            resValue("string", "admob_banner_id", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue(
                "string",
                "admob_splash_fullscreen",
                "\"ca-app-pub-3940256099942544/1033173712\""
            )
            resValue("string", "admob_interal", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "native_id", "\"ca-app-pub-3940256099942544/2247696110\"")



            resValue("string", "billing_product", "\"android.test.purchased\"")
            resValue("string", "env_dev", "true")
        }
        create("appProd") {


            resValue("string", "app_id", "\"ca-app-pub-7553272958179684~8377819593\"")

            resValue("string", "app_open_l", "\"ca-app-pub-7553272958179684/4619204570\"")
            resValue("string", "admob_banner_id", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue(
                "string",
                "admob_splash_fullscreen",
                "\"ca-app-pub-7553272958179684/3852346991\""
            )
            resValue("string", "admob_interal", "\"ca-app-pub-7553272958179684/8721530290\"")
            resValue("string", "native_id", "\"ca-app-pub-7553272958179684/9843040276\"")

            resValue("string", "billing_product", "\"ads_free\"")
            resValue("string", "env_dev", "false")
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
        viewBinding=true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(project(mapOf("path" to ":adsdk")))
    implementation("androidx.lifecycle:lifecycle-process:2.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //sdp and ssp library
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    //lottie animation
    implementation("com.airbnb.android:lottie:6.1.0")
    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:31.1.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-config-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")

    implementation("com.google.firebase:firebase-inappmessaging-display-ktx")
    implementation("com.google.android.play:core-ktx:1.8.1")

    //Google Gson
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("com.google.android.gms:play-services-ads:23.0.0")
    implementation("com.google.android.ump:user-messaging-platform:2.2.0")

    //billing
    implementation("com.android.billingclient:billing-ktx:6.2.0")

    // facebook
    implementation("com.facebook.fresco:fresco:2.5.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // TedPermission
    implementation("io.github.ParkSangGwon:tedpermission-normal:3.3.0")
    implementation ("androidx.room:room-ktx:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    // Kotlin components
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.7.0")
    implementation ("androidx.activity:activity-ktx:1.8.2")


    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation ("com.google.code.gson:gson:2.10.1")

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation("com.tbuonomo:dotsindicator:5.0")


}