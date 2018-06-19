//Here should be all yours includes that you will need in your C++ code
#include <cstdlib> //Just an example

//Here should be an include of yours important header that was generated using the 'javah' command
#include "com_minebarteksa_sonos_jni_yourClass.h" //You can just edit the 'yourClass' part

//Now the fun beggins, form that important header you have to get every function and write code for it.
//For example for 'public native void func(String text);'
JNIEXPORT void JNICALL Java_com_minebarteksa_sonos_jni_yourClass_func (JNIEnv *env, jobject obj, jstring text)
{
	//Some code
}

//But when you will needing to return someting you just return it like in normal C++
//I think that's everything you need to know!
