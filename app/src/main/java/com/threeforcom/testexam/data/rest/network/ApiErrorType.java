package com.threeforcom.testexam.data.rest.network;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ApiErrorType.SOCKET_TIMEOUT, ApiErrorType.NETWORK_ERROR, ApiErrorType.UNKNOWN_ERROR})
@Retention(RetentionPolicy.SOURCE)
public @interface ApiErrorType {
    int SOCKET_TIMEOUT = 1;
    int NETWORK_ERROR = 2;
    int UNKNOWN_ERROR = 3;
}