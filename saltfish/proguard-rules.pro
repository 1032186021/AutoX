# AutoX core
-keep class com.stardust.autojs.** { *; }
-keep class com.stardust.automator.** { *; }
-keep class com.stardust.** { *; }

# Rhino engine
-keep class org.mozilla.javascript.** { *; }

# Saltfish models (Gson serialization)
-keep class com.saltfish.assistant.domain.model.** { *; }

# Room entities
-keep class * extends androidx.room.RoomDatabase { *; }

# ScriptInterface annotated methods
-keepclassmembers class * {
    @com.stardust.autojs.annotation.ScriptInterface *;
}

# Socket.IO
-keep class io.socket.** { *; }
-keep class org.json.** { *; }

# Retrofit
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Gson
-keep class com.google.gson.** { *; }
-keepattributes *Annotation*
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# EventBus
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
