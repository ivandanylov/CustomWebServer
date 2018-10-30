package com.study;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.StringJoiner;

public class TestResponseWriter {
    private OutputStream outputStream;
    private BufferedWriter bufferedWriter;

    @Before
    public void before() {
        outputStream = new ByteArrayOutputStream();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    @Test
    public void testSendSuccessResponse() {
        ResponseWriter responseWriter = new ResponseWriter(bufferedWriter);

        try {
            responseWriter.sendSuccessResponse("content");

            Assert.assertEquals("content", new String(((ByteArrayOutputStream) outputStream).toByteArray()));

            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSendNotFoundResponse() {
        ResponseWriter responseWriter = new ResponseWriter(bufferedWriter);
        StringJoiner content = new StringJoiner("\n");
        content.add("HTTP/1.1 500 NOT OK");
        content.add("");
        content.add("Error 500");

        try {
            responseWriter.sendInternalServerErrorResponse();

            Assert.assertEquals(content.toString(), new String(((ByteArrayOutputStream) outputStream).toByteArray()));

            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
