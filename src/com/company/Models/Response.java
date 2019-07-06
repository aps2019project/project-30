package com.company.Models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class Response {

    public static class Codes {
        public static final int BAD_LOGIN = 430;
        public static final int BAD_SIGN_UP = 431;
        public static final int SUCCESSFUL_LOGIN = 100;
        public static final int SUCCESSFUL_SIGN_UP = 101;
        public static final int SENT_CARDS=200;
        public static final int SHOLSELCARD_SENT=201;
        final public static int SUCCESSFUL_LOG_OUT = 102;
        final public static int ACCOUNTS_INFO = 104;
        public static final int MESSAGE_NOTIFY = 50;
    }

    public Response(int code, Property... properties) {
        this.setCode(code);
        JsonObject jsonObject = new JsonObject();
        for (Property property : properties) {
            jsonObject.addProperty(property.getProperty(), property.getValue());
        }
        this.setContent(jsonObject);
    }

    public Response() {
    }

    private int code;
    private JsonObject content = new JsonObject();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JsonObject getContent() {
        return content;
    }

    public void setContent(JsonObject content) {
        this.content = content;
    }
}
