@echo off
set JAVA_HOME=C:\Program Files\Android\Android Studio\jbr

if not exist local.properties (
  echo sdk.dir=C:\Users\zhiyo\AppData\Local\Android\Sdk > local.properties
)

if "%1"=="" (
  set TARGET=release
) else (
  set TARGET=%1
)

if "%TARGET%"=="debug" (
  call gradlew.bat :app:assembleCommonDebug
) else if "%TARGET%"=="release" (
  call gradlew.bat :app:assembleCommonRelease
) else if "%TARGET%"=="v6" (
  call gradlew.bat :app:assembleV6Debug
) else (
  echo Usage: build-apk.bat [debug^|release^|v6]
  exit /b 1
)
