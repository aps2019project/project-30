package com.company.Models;

import com.google.gson.JsonObject;

public class Request {
    public enum Type {
        LOGIN,
        SIGN_UP,
        NEW_MESSAGE
    }

    public Request() {

    }

    public Request(Type type, Property... properties) {
        setType(type);
        JsonObject jsonObject = new JsonObject();
        for (Property property:properties) {
            jsonObject.addProperty(property.getProperty(),property.getValue());
        }
        setContent(jsonObject);
    }

    private Type type;
    private JsonObject content = new JsonObject();

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public JsonObject getContent() {
        return content;
    }

    public void setContent(JsonObject content) {
        this.content = content;
    }
}
