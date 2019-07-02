package com.company.Models;

import com.google.gson.JsonObject;

public class Request {
    public enum RequestType{
        LOGIN
    }

    private RequestType requestType;
    private JsonObject content = new JsonObject();

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public JsonObject getContent() {
        return content;
    }

    public void setContent(JsonObject content) {
        this.content = content;
    }
}
