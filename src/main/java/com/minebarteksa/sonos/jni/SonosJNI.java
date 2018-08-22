package com.minebarteksa.sonos.jni;

public class SonosJNI
{
  public static final String libDir = System.getProperty("user.dir") + "/mods/SonosLibs";
  public static final String downUri = "https://raw.github.com/MineBartekSA/Sonos/master/src/JNI";

  public static stdio std;

  public static void JNInit()
  {
    LibDownloader.AddLib(stdio.getLib());

    LibDownloader.Donwload();

    std = new stdio();
    std.printf("Hello there!");
  }
}
