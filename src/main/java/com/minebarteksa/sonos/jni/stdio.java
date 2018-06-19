package com.minebarteksa.sonos.jni;

public class stdio
{
  public native void jprintf(String s);
  public stdio() { System.load("/home/minebarteksa/Projects/Sonos/src/JNI/stdio/build/libstdioport.so"); }
}
