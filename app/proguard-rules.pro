# 1. 你的项目包名（防止你自己写的蓝牙处理类被混淆）
-keep class com.atharok.btremote.** { *; }

# 2. Koin 依赖注入（Koin 报错是闪退大户）
-keep class io.insertkoin.** { *; }
-keepclassmembers class * { @org.koin.core.annotation.KoinInternalApi <methods>; }

# 3. 蓝牙与系统组件（必须保留名字，系统才能调用）
-keep class * extends android.app.Service
-keep class * extends android.content.BroadcastReceiver
-keep class * extends android.app.Activity
-keep class * extends android.app.Application

# 4. Kotlinx Serialization (如果你有存取配置)
-keepattributes *Annotation*, InnerClasses
-keep class kotlinx.serialization.** { *; }
-keep @kotlinx.serialization.Serializable class * { *; }

# 5. 所有的 Compose 基础类（防止 UI 找不到）
-keep class androidx.compose.** { *; }

# 针对 Glance 小组件的保护
-keep class androidx.glance.** { *; }
# 针对 DataStore 的保护
-keep class androidx.datastore.** { *; }
# 针对 Koin 的反射调用保护
-keepclassmembers class * {
    @org.koin.core.annotation.KoinInternalApi <methods>;
}
# 解决 SDK 36 可能出现的资源混淆问题
-keepclassmembers class **.R$* {
    public static <fields>;
}
