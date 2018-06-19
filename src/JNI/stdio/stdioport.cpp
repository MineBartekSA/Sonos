#include <iostream>

#include "com_minebarteksa_sonos_jni_stdio.h"

JNIEXPORT void JNICALL Java_com_minebarteksa_sonos_jni_stdio_jprintf (JNIEnv *env, jobject obj, jstring s)
{
	std::string ss = env->GetStringUTFChars(s, (jboolean)false);
	std::cout << "[C++] " << ss << std::endl;
}
