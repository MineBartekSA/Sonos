package com.minebarteksa.sonos.jni;

import java.nio.channels.Channels;
import java.io.FileOutputStream;
import java.net.URL;
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

  public static void DownloadLib()
  {
    try
    {
      FileOutputStream fos = new FileOutputStream(SonosJNI.libDir + "/libstdioport.so");
      fos.getChannel().transferFrom(Channels.newChannel(new URL(SonosJNI.downUri + "/stdio/libstdioport.so").openStream()), 0, Long.MAX_VALUE);
      fos.close();
    }
    catch(Exception e)
    {
      Sonos.log.error("An Exception was caught when trying to Download 'libstdioport.so' library! ");
      Sonos.log.error(e.toString());
    }
  }

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
