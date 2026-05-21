#!/bin/bash
cd "$(dirname "$0")"

export JAVA_HOME="C:/Program Files/Android/Android Studio/jbr"

if [ ! -f local.properties ]; then
  echo "sdk.dir=C:/Users/zhiyo/AppData/Local/Android/Sdk" > local.properties
fi

case "${1:-release}" in
  debug)
    ./gradlew :app:assembleCommonDebug
    ;;
  release)
    ./gradlew :app:assembleCommonRelease
    ;;
  v6)
    ./gradlew :app:assembleV6Debug
    ;;
  *)
    echo "Usage: $0 [debug|release|v6]"
    exit 1
    ;;
esac
