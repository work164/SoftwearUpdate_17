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
        versionCode = 20
        versionName = "3.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
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

            resValue("string", "val_banner_intro", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_language", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_privacy", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_main_menu_bottom", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_main_menu_top", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_setting", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_scan", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_scan_apps", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_app_details", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_install", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_uninstall", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_system_apps", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_app_usage", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_device_details", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_system_update", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_mobile_sensor", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_apps_restore", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_battery_info", "\"ca-app-pub-3940256099942544/6300978111\"")
            resValue("string", "val_banner_android_details", "\"ca-app-pub-3940256099942544/6300978111\"")

            resValue("string", "val_fullscreen_splash", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_language_from_app", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_intro", "\"ca-app-pub-3940256099942544/1033173712\"")

            resValue("string", "val_fullscreen_main_load", "\"ca-app-pub-3940256099942544/1033173712\"")

            resValue("string", "val_fullscreen_scan_app_details", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_update_app_details", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_install_app_details", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_uninstall_app_details", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_system_app_details", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_app_usage_details", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_system_update_details", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_app_restore_details", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_android_details_back", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_battery_details_back", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_device_sensor_back", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_device_details_back", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_all_details_back", "\"ca-app-pub-3940256099942544/1033173712\"")
            resValue("string", "val_fullscreen_setting_back", "\"ca-app-pub-3940256099942544/1033173712\"")

            resValue("string", "val_native_splash_top", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_splash_bottom", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_intro", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_language", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_privacy", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_main_menu_bottom", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_main_menu_top", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_setting", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_scan", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_scan_apps", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_app_details", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_uninstall", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_install", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_system_apps", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_app_usage", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_device_details", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_system_update", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_mobile_sensor", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_apps_restore", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_battery_info", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_android_details", "\"ca-app-pub-3940256099942544/2247696110\"")
            resValue("string", "val_native_exit", "\"ca-app-pub-3940256099942544/2247696110\"")

            resValue("string", "billing_product", "\"android.test.purchased\"")
            resValue("string", "env_dev", "true")
        }
        create("appProd") {

            resValue("string", "app_id", "\"ca-app-pub-7553272958179684~8377819593\"")

            resValue("string", "app_open_l", "\"ca-app-pub-7553272958179684/4619204570\"")

            resValue("string", "val_banner_intro", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_language", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_privacy", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_main_menu_bottom", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_main_menu_top", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_setting", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_scan", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_scan_apps", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_app_details", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_install", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_uninstall", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_system_apps", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_app_usage", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_device_details", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_system_update", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_mobile_sensor", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_apps_restore", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_battery_info", "\"ca-app-pub-7553272958179684/6670082023\"")
            resValue("string", "val_banner_android_details", "\"ca-app-pub-7553272958179684/6670082023\"")

            resValue("string", "val_fullscreen_splash", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_language_from_app", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_intro", "\"ca-app-pub-7553272958179684/3852346991\"")

            resValue("string", "val_fullscreen_main_load", "\"ca-app-pub-7553272958179684/3852346991\"")

            resValue("string", "val_fullscreen_scan_app_details", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_update_app_details", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_install_app_details", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_uninstall_app_details", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_system_app_details", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_app_usage_details", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_system_update_details", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_app_restore_details", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_android_details_back", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_battery_details_back", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_device_sensor_back", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_device_details_back", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_all_details_back", "\"ca-app-pub-7553272958179684/3852346991\"")
            resValue("string", "val_fullscreen_setting_back", "\"ca-app-pub-7553272958179684/3852346991\"")

            resValue("string", "val_native_splash_top", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_splash_bottom", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_intro", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_language", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_privacy", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_main_menu_bottom", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_main_menu_top", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_setting", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_scan", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_scan_apps", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_app_details", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_uninstall", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_install", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_system_apps", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_app_usage", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_device_details", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_system_update", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_mobile_sensor", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_apps_restore", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_battery_info", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_android_details", "\"ca-app-pub-7553272958179684/9843040276\"")
            resValue("string", "val_native_exit", "\"ca-app-pub-7553272958179684/9843040276\"")



            resValue("string", "billing_product", "\"ads_free\"")
            resValue("string", "env_dev", "false")
        }
    }


    buildFeatures.viewBinding = true
    buildFeatures.dataBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation(project(mapOf("path" to ":adsdk")))
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
    implementation ("com.integer.ads:integet-ads:1.0.3")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation ("com.google.code.gson:gson:2.10.1")

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation("com.tbuonomo:dotsindicator:5.0")
    // for fragment navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")

    implementation("org.jsoup:jsoup:1.13.1")
    implementation("com.github.TheBotBox:AppsUsageMonitorAPI:1.1")
    implementation ("com.facebook.infer.annotation:infer-annotation:0.18.0")

}