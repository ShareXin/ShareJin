#!/bin/sh

# sets pictures directory
XDG_PICTURES_DIR="${XDG_PICTURES_DIR:-$HOME/Pictures}"

# take a screenshot using gnome-screenshot
gnome-screenshot -a -f /tmp/ShareJin_tmp.jpg

if [ $? -eq 0 ]
then
  # compiles and/or launches java app
  if [ -f "./linux/image/learn/GUI.class" ]
  then
    echo "[ShareJin] Opening app"
    java -cp ./lib/ini4j-0.5.4.jar:./lib/twitter4j-core-4.0.4.jar:./lib/twitter4j-media-support-4.0.4.jar:./linux/image learn.GUI
  else
    echo "[ShareJin] Compiling and Opening"
    javac -cp ./lib/ini4j-0.5.4.jar:./lib/twitter4j-core-4.0.4.jar:./lib/twitter4j-media-support-4.0.4.jar -d ./linux/image/ ./src-image/learn/GUI.java
    java -cp ./lib/ini4j-0.5.4.jar:./lib/twitter4j-core-4.0.4.jar:./lib/twitter4j-media-support-4.0.4.jar:./linux/image learn.GUI
  fi
else
  exit 1
fi

# date and time for naming
date=$(date +%Y-%m-%d)
time=$(date +%T)

# copies tmo file to permanent location
cp /tmp/ShareJin_tmp.jpg $XDG_PICTURES_DIR/ShareJin/Full/twitter-$date-$time.png
