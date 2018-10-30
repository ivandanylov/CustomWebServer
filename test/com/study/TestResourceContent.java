package com.study;

import org.junit.Assert;
import org.junit.Test;

import java.util.StringJoiner;

public class TestResourceContent {
    @Test
    public void testGetContent() {
        StringJoiner content = new StringJoiner("\n");
        content.add(String.format("HTTP/1.1 %d OK", 200));
        content.add("mime-type");
        content.add("");
        content.add("content");

        Assert.assertEquals(content.toString(), ResourceContent.getContent(200, "mime-type", "content"));
    }
}
