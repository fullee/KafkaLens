-dontwarn org.apache.kafka.**
-dontwarn org.slf4j.**
-dontwarn org.xerial.snappy.**
-dontwarn net.bytebuddy.**
-dontwarn kotlinx.coroutines.**

-keep class org.apache.kafka.** { *; }
-keep class kotlinx.** { *; }

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
    public static ** valueOf(int);
}