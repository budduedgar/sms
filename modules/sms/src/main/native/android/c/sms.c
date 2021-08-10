#include "util.h"

//Graal handles
static jclass jGraalSmsClass;
jmethodID jGraalReceiceSmsMethod;

static jclass jSmsServiceClass;

static void initializeGraalHandles(JNIEnv* env) {
    jGraalSmsClass = (*env)->NewGlobalRef(env, (*env)->FindClass(env, "org/jpereda/attach/sms/impl/impl/AndroidSmsService"));
    jGraalReceiceSmsMethod = (*env)->GetMethodID(env, jGraalSmsClass, "receiveSms", "(Ljava/lang/String;Ljava/lang/String;)V");
}

static void initializeDalvikHandles() {
    jSmsServiceClass = GET_REGISTER_DALVIK_CLASS(jSmsServiceClass, "com/gluonhq/helloandroid/DalvikSmsService");
    ATTACH_DALVIK();
    jmethodID jSmsServiceInitMethod = (*dalvikEnv)->GetMethodID(dalvikEnv, jSmsServiceClass, "<init>", "(Landroid/app/Activity;)V");
    jobject jActivity = substrateGetActivity();
    jobject jtmpobj = (*dalvikEnv)->NewObject(dalvikEnv, jSmsServiceClass, jSmsServiceInitMethod, jActivity);
    jobject jDalvikSmsService = (*dalvikEnv)->NewGlobalRef(dalvikEnv, jtmpobj);
    DETACH_DALVIK();
}
//////////////////////////
// From Graal to native //
//////////////////////////
JNIEXPORT jint JNICALL 
JNI_OnLoad_sms(JavaVM *vm, void *reserved)
{
    ATTACH_LOG_INFO("JNI_OnLoad_sms called");
#ifdef JNI_VERSION_1_8
    JNIEnv* graalEnv;
    if ((*vm)->GetEnv(vm, (void **)&graalEnv, JNI_VERSION_1_8) != JNI_OK) {
        ATTACH_LOG_WARNING("sms error initializing native Sms from OnLoad");
        return JNI_FALSE;
    }
    ATTACH_LOG_FINE("[sms service] Initializing native Sms from OnLoad started");
    ATTACH_LOG_FINE("[sms service] Initializing native graal handles started");
    initializeGraalHandles(graalEnv);
    ATTACH_LOG_FINE("[sms service] Initializing native dalvik handles started");
    initializeDalvikHandles();
    ATTACH_LOG_FINE("[sms service] Initializing native Sms from OnLoad done");
    return JNI_VERSION_1_8;
#else
    #error Error: Java 8+ SDK is required to compile Attach
#endif
}

///////////////////////////
// From Dalvik to native //
///////////////////////////
JNIEXPORT void JNICALL Java_com_gluonhq_helloandroid_DalvikSmsService_processReceivedSms 
(JNIEnv *env, jclass service, jstring jkey, jstring jvalue) 
{
    const char *keyChars = (*env)->GetStringUTFChars(env, jkey, NULL);
    const char *valueChars = (*env)->GetStringUTFChars(env, jvalue, NULL);
    if (isDebugAttach()) {
        ATTACH_LOG_FINE("[sms service] native layer got sms, key: %s, value: %s", keyChars, valueChars);
    }
    ATTACH_GRAAL();
    ATTACH_LOG_FINE("[sms service] dalvik->native layer all got sms, key: %s, value: %s", keyChars, valueChars);
    jstring jKeyChars = (*graalEnv)->NewStringUTF(graalEnv, keyChars);
    jstring jValueChars = (*graalEnv)->NewStringUTF(graalEnv, valueChars);
    (*graalEnv)->CallVoidMethod(graalEnv, jGraalSmsClass, jGraalReceiceSmsMethod, jKeyChars, jValueChars);
    (*graalEnv)->DeleteLocalRef(graalEnv, jKeyChars);
    (*graalEnv)->DeleteLocalRef(graalEnv, jValueChars);
    DETACH_GRAAL();
    (*env)->ReleaseStringUTFChars(env, jkey, keyChars);
    (*env)->ReleaseStringUTFChars(env, jvalue, valueChars);
}