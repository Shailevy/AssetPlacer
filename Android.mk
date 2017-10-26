LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)


LOCAL_PROGUARD_FLAG_FILES := proguard.flags
LOCAL_CERTIFICATE := platform

LOCAL_MANIFEST_FILE := app/src/main/AndroidManifest.xml

LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/app/src/main/res

LOCAL_DEX_PREOPT := false
LOCAL_PROGUARD_ENABLED := disabled



LOCAL_SRC_FILES := $(call all-java-files-under, app/src/main/java) 
LOCAL_PACKAGE_NAME := AssetPlacer

include $(BUILD_PACKAGE)
include $(CLEAR_VARS)
