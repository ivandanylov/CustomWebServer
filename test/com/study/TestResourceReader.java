package com.study;

import com.study.exceptions.InternalServerErrorException;
import com.study.exceptions.NotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.StringJoiner;

public class TestResourceReader {
    @Test
    public void testReadContent() {
        String webAppPath = "resource" + File.separator + "webapp";

        ResourceReader resourceReader = new ResourceReader(webAppPath);

        StringJoiner indexHTML = new StringJoiner("\n");
        indexHTML.add("HTTP/1.1 200 OK");
        indexHTML.add("Content-Type: text/html; charset=utf-8");
        indexHTML.add("");

        String resourcePath = new File("").getAbsolutePath()
                .concat(File.separator)
                .concat(webAppPath)
                .concat("/index.html").replace("/", File.separator)
                .replace("\\", File.separator);

        try {
            BufferedReader fileContent = new BufferedReader(new InputStreamReader(new FileInputStream(resourcePath)));

            String fileValue;
            StringJoiner resultValue = new StringJoiner("\n");
            while ((fileValue = fileContent.readLine()) != null) {
                resultValue.add(fileValue);
            }

            indexHTML.add(resultValue.toString());

            Assert.assertEquals(indexHTML.toString(), resourceReader.readContent("/index.html"));
        } catch (IOException | NotFoundException | InternalServerErrorException e) {
            e.printStackTrace();
        }
    }
}
