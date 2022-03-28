package com.ridhimakohli.hotelreservation.types;

import org.springframework.security.core.Authentication;

import java.util.List;

public class AjaxResponseBody<T> {

    String msg;
    List<T> result;
    Authentication auth;
    String key;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Authentication getAuth() {
        return auth;
    }

    public void setAuth(Authentication auth) {
        this.auth = auth;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
