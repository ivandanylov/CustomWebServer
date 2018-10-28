package com.study;

import java.io.BufferedWriter;

public class ResponseWriter {
    private BufferedWriter socketWriter;

    public void sendSuccessResponse(String content) {

    }

    public void sendNotFoundResponse() {

    }

    public void sendBadRequestResponse() {
        System.out.println("BadRequestResponse");
    }

    public void sendInternalServerErrorResponse() {

    }
}
