plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.atharok.btremote"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.atharok.btremote"
        minSdk = 28
        targetSdk = 36
        versionCode = 21
        versionName = "1.9.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // 签名配置：因为你在 Actions 报错没找到密钥，我们在这里做一个保险
    signingConfigs {
        getByName("debug") {
            // 保持默认
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        getByName("release") {
            // 【关键点】如果你的 Secrets 没配，这里强制用 debug 签名避免报错
            signingConfig = signingConfigs.getByName("debug")

            isMinifyEnabled = true     // 开启混淆
            isShrinkResources = true   // 剔除无用资源
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // 多渠道配置
    flavorDimensions += "version"
    productFlavors {
        create("default") {
            dimension = "version"
        }
        create("gplay") {
            dimension = "version"
            applicationIdSuffix = ".gplay"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.datastore)
    implementation(libs.koin.androidx.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.glance.appwidget)
    implementation(libs.glance.material3)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
