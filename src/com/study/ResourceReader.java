package com.study;

import com.study.exceptions.InternalServerErrorException;
import com.study.exceptions.NotFoundException;

import java.io.*;
import java.util.StringJoiner;

public class ResourceReader {
    private String webAppPath;

    public ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }


    public String readContent(String uri) throws NotFoundException, InternalServerErrorException {
        String resourcePath = new File("").getAbsolutePath()
                .concat(File.separator)
                .concat(webAppPath)
                .concat(uri).replace("/", File.separator)
                .replace("\\", File.separator);

        String mimeType;

        if (uri.contains(".html")) {
            mimeType = "Content-Type: text/html; charset=utf-8";
        } else if (uri.contains(".css")) {
            mimeType = "Content-Type: text/css; charset=utf-8";
        } else {
            mimeType = "Content-Type: text/plain; charset=utf-8";
        }

        File file = new File(resourcePath);

        if (file.isFile() && file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(resourcePath)));

                String fileValue;
                StringJoiner resultValue = new StringJoiner("\n");
                while ((fileValue = bufferedReader.readLine()) != null) {
                    resultValue.add(fileValue);
                }

                return ResourceContent.getContent(200, mimeType, resultValue.toString());
            } catch (FileNotFoundException fe) {
                throw new NotFoundException("Requested resource not found");
            } catch (IOException ie) {
                throw new InternalServerErrorException("Exception on getting resources");
            }
        } else {
            throw new NotFoundException("Requested resource not found");
        }
    }
}
