package com.company.Models;

import com.google.gson.JsonObject;

public class Request {
    public enum RequestType{
        LOGIN
    }

    private RequestType requestType;
    private JsonObject requestContent;

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public JsonObject getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(JsonObject requestContent) {
        this.requestContent = requestContent;
    }
}
