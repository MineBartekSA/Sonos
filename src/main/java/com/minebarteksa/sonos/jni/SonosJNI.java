package com.minebarteksa.sonos.jni;

public class SonosJNI
{
  public static stdio std;

  public static void JNInit()
  {
    std = new stdio();
    std.jprintf("Hello there!");
  }
}
