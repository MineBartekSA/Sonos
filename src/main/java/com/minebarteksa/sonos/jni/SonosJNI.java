package com.minebarteksa.sonos.jni;

import com.minebarteksa.sonos.Sonos;
import java.io.File;

public class SonosJNI
{
  public static final String libDir = System.getProperty("user.dir") + "/mods/SonosLibs";
  public static final String downUri = "https://raw.github.com/MineBartekSA/Sonos/master/src/JNI";

  public static stdio std;

  public static void JNInit()
  {
    if(!new File(libDir).exists())
    {
      Sonos.log.info("No SonosLibs directory! Creating one!");
      new File(libDir).mkdir();
      //Libs Download
      stdio.DownloadLib();
      return;
    }
    std = new stdio();
    std.printf("Hello there!");
  }
}
