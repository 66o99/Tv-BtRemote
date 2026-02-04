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
