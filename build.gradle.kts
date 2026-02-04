// Top-level build file where you can add configuration options common to all sub-projects/modules.
# plugins {
#    alias(libs.plugins.android.application) apply false
#    alias(libs.plugins.kotlin.compose) apply false
# }

android {
    // ... 其他配置 (compileSdk, defaultConfig 等)

    buildTypes {
        release {
            // 【关键代码】强制让 release 模式使用自带的 debug 签名
            signingConfig signingConfigs.debug

            // 开启混淆压缩以大幅减小体积
            minifyEnabled true
            // 开启资源缩减（剔除未使用的图片、布局等）
            shrinkResources true
            
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
