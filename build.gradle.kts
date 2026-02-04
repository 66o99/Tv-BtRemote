// Top-level build file where you can add configuration options common to all sub-projects/modules.

// old-bak
// plugins {
//   alias(libs.plugins.android.application) apply false
//   alias(libs.plugins.kotlin.compose) apply false
// }

android {
    // ... 其他配置 (compileSdk, defaultConfig 等)

buildTypes {
        getByName("release") {
            // 1. 赋值必须使用 '='
            signingConfig = signingConfigs.getByName("debug")

            // 2. 属性名需加 'is' 前缀，且使用 '=' 赋值
            isMinifyEnabled = true
            isShrinkResources = true
            
            // 3. 函数调用必须使用括号 '()'，且字符串使用双引号 ""
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
