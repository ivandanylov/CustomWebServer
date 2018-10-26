package com.study;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    private int port;
    private String webAppPath;

    public Server() {

    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    private String getResourcePath(String value) {
        Pattern pattern = Pattern.compile("(?= ).*(?= )");
        Matcher matcher = pattern.matcher(value);

        String path = "";

        if (matcher.find()) {
            path = webAppPath.concat(matcher.group(0).trim()).replace("/", File.separator);
        }

        return path;
    }

    private InputStream getFile(String path) {
        File file = new File(path);

        if (file.isFile() && file.exists()) {
            try {
                InputStream inputStreamStream = new FileInputStream(path);
                return inputStreamStream;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

                    String value;
                    while ((value = bufferedReader.readLine()) != null && !value.isEmpty()) {
                        //System.out.println(value);
                        if (value.startsWith("GET")) {
                            InputStream inputStream = getFile(getResourcePath(value));

                            if (inputStream != null) {
                                BufferedReader fileBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                                bufferedWriter.write("HTTP/1.1 200 OK\n");
                                bufferedWriter.write("\n");

                                String fileValue;
                                while ((fileValue = fileBufferedReader.readLine()) != null) {
                                    bufferedWriter.write(fileValue + "\n");
                                }

                                bufferedWriter.flush();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
