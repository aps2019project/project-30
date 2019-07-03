package com.company.Models;

import com.google.gson.JsonObject;
import com.sun.org.apache.bcel.internal.classfile.Code;

public class Response {

    public static class Codes{
        final public static int LOGIN = 1;
    }

    public Response(int code,Property... properties) {
        setCode(code);
        JsonObject jsonObject = new JsonObject();
        for (Property property :properties) {
            jsonObject.addProperty(property.getProperty(),property.getValue());
        }
        this.setContent(jsonObject);
    }

    public Response(){
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
