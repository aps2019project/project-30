package com.company.Models;

import com.google.gson.JsonObject;

public class Response {

    public static class Codes{
        final public static int LOGIN = 1;
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
