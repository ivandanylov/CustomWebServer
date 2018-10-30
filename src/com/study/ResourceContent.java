package com.study;

import java.util.StringJoiner;

public class ResourceContent {
    public static String getContent(int httpStatus, String mimeType, String content) {
        StringJoiner resultContent = new StringJoiner("\n");
        resultContent.add(String.format("HTTP/1.1 %d OK", httpStatus));
        resultContent.add(mimeType);
        resultContent.add("");
        resultContent.add(content);


        return resultContent.toString();
    }
}
