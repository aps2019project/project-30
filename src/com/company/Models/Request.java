package com.company.Models;

import com.google.gson.JsonObject;

public class Request {
    public enum Type {
        LOGIN
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
