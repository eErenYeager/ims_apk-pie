#/******************************************************************************
#*@file Android.mk
#*brief Rules for compiling the source files
#*******************************************************************************/

src_java_ims := src/org/codeaurora/ims
src_java_ims += src/com/qualcomm/ims/utils
src_java_ims += src/com/qualcomm/ims/vt
src_proto := src

LOCAL_PATH:= $(call my-dir)

# ==========================================================================
# Build the service
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_PACKAGE_NAME := ims
LOCAL_CERTIFICATE := platform
LOCAL_PROGUARD_ENABLED := disabled
LOCAL_MODULE_OWNER := qti
LOCAL_JAVA_LIBRARIES := telephony-common ims-common voip-common
LOCAL_STATIC_JAVA_LIBRARIES := ims-ext-common


LOCAL_PROPRIETARY_MODULE := true

LOCAL_PRIVATE_PLATFORM_APIS := true

LOCAL_PROTOC_OPTIMIZE_TYPE := micro

LOCAL_SRC_FILES := $(call all-java-files-under, $(src_java_ims)) \
    $(call all-proto-files-under, $(src_proto))

include $(BUILD_PACKAGE)

include $(call all-makefiles-under,$(LOCAL_PATH))

# ==========================================================================

