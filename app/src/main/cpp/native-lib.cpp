#include <jni.h>
#include <string>
#include <android/log.h>

#define LOG_TAG "test"
#define LOGI(...)   __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...)   __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define LOGD(...)   __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

int jniThrowException(JNIEnv* env, const char* className, const char* message) {
    jclass exClass = env->FindClass(className);
    if (exClass == NULL) {
        LOGE("Unable to find exception class %s", className);
        return -1;
    }

    if(env->ThrowNew(exClass, message ) != JNI_OK) {
        LOGE("Failed throwing '%s' '%s'", className, message);
        return -1;
    }

    return 0;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_test_app_NativeMain_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {

    int result = jniThrowException(env, "com/test/app/TestException",
            "Password required or incorrect password.");

    if (result == 0) {
        return nullptr;
    }

    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}