#!/bin/sh

if [ $? -eq 0 ]
then
  # compiles and/or launches java app
  if [ -f "./linux/tweet/jin/GUI.class" ]
  then
    echo "[ShareJin] Opening app"
    java -cp ./lib/ini4j-0.5.4.jar:./lib/twitter4j-core-4.0.4.jar:./lib/twitter4j-media-support-4.0.4.jar:./linux/tweet jin.GUI
  else
    echo "[ShareJin] Compiling and Opening"
    javac -cp ./lib/ini4j-0.5.4.jar:./lib/twitter4j-core-4.0.4.jar:./lib/twitter4j-media-support-4.0.4.jar -d ./linux/tweet/ ./src/jin/GUI.java
    java -cp ./lib/ini4j-0.5.4.jar:./lib/twitter4j-core-4.0.4.jar:./lib/twitter4j-media-support-4.0.4.jar:./linux/tweet jin.GUI
  fi
else
  exit 1
fi
