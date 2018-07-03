package com.minebarteksa.sonos.jni;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.HttpURLConnection;
import net.minecraftforge.fml.common.ProgressManager.ProgressBar;
import net.minecraftforge.fml.common.ProgressManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.minebarteksa.sonos.Sonos;

public class LibDownloader
{
  private static final int BuffSize = 1024;

  private static List<Lib> libs = new ArrayList<Lib>();

  public static void Donwload()
  {
    if(!new File(SonosJNI.libDir).exists())
      new File(SonosJNI.libDir).mkdir();

    for(Lib l : libs)
    {
      if(new File(SonosJNI.libDir + "/" + l.libName).exists())
        continue;

      InputStream stream = null;
      RandomAccessFile file = null;
      ProgressBar pb = null;

      try
      {
        URL url = new URL(SonosJNI.downUri + l.libUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.connect();

        int size = conn.getContentLength();
        pb = ProgressManager.push("Downloading SonoLib", size);

        file = new RandomAccessFile(SonosJNI.libDir + "/" + l.libName, "rw");
        stream = conn.getInputStream();

        int downloaded = 0;
        while(true)
        {
          byte buff[];
          if(size - downloaded > 1)
            buff = new byte[1];
          else
            buff = new byte[1];

          int read = stream.read(buff); //Make better
          if(read == -1)
            break;

          file.write(buff);
          downloaded += read;
          pb.step(l.libName);
        }
        ProgressManager.pop(pb);
        file.close();
        stream.close();
      }
      catch(Exception e)
      {
        Sonos.log.error("An error ocoured while downloading SonoLib!");
        Sonos.log.error(e.toString());
        try { file.close(); }
        catch(Exception ee) {}
        file = null;
        new File(SonosJNI.libDir + "/" + l.libName).delete();
      }
      finally
      {
        if(file != null)
        {
          try
          { file.close(); }
          catch(Exception e) {}
        }
        if(stream != null)
        {
          try { stream.close(); }
          catch(Exception e) {}
        }
        if(pb != null)
        {
          try
          {
            while(pb.getStep() != pb.getSteps())
              pb.step("ERROR while Downloading");
            ProgressManager.pop(pb);
          }
          catch(Exception e) {}
        }
      }
    }
  }

  public static void AddLib(Lib l) { libs.add(l); }
  public static void RemoveLib(Lib l) { libs.remove(l); }

  public static class Lib
  {
    public String libName;
    public String libUrl;

    public Lib(String name, String url)
    {
      this.libName = name;
      this.libUrl = url;
    }
  }
}
