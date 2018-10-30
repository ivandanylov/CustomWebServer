package com.study;

import com.study.exceptions.BadRequestException;
import com.study.exceptions.InternalServerErrorException;
import com.study.exceptions.NotFoundException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class RequestHandler {
    private BufferedReader reader;
    private BufferedWriter writer;
    private String webAppPath;

    public RequestHandler(BufferedReader reader, BufferedWriter writer, String webAppPath) {
        this.reader = reader;
        this.writer = writer;
        this.webAppPath = webAppPath;
    }

    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser();
        ResponseWriter responseWriter = new ResponseWriter(writer);

        Request request;
        try {
            request = requestParser.parseRequest(reader);

            ResourceReader resourceReader = new ResourceReader(webAppPath);
            responseWriter.sendSuccessResponse(resourceReader.readContent(request.getUri()));
        } catch (BadRequestException bre) {
            responseWriter.sendBadRequestResponse();
        } catch (NotFoundException nfe) {
            responseWriter.sendNotFoundResponse();
        } catch (InternalServerErrorException see) {
            responseWriter.sendInternalServerErrorResponse();
        }
    }
}
