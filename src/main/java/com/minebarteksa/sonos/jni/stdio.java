package com.minebarteksa.sonos.jni;

import java.io.File;
import com.minebarteksa.sonos.Sonos;

public class stdio
{
  private boolean isLoaded = false;

  private native void jprintf(String s);

  public stdio()
  {
    if(new File(SonosJNI.libDir + "/libstdioport.so").exists())
    {
      System.load(SonosJNI.libDir + "/libstdioport.so");
      isLoaded = true;
    }
    else
      Sonos.log.warn("No 'libstdioport.so' in SonosLibs directory!");
  }

  public static LibDownloader.Lib getLib(){ return new LibDownloader.Lib("libstdioport.so", "/stdio/libstdioport.so"); }

  public void printf(String s)
  {
    if(!isLoaded)
    {
      Sonos.log.warn("Unable to run 'printf' because Sonos was unable to load 'libstdioport.so'!");
      return;
    }
    jprintf(s);
  }
}
