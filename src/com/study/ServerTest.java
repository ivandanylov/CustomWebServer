package com.study;

import java.io.IOException;

public class ServerTest {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3000);
        server.setWebAppPath("resource/webapp");
        server.start();
    }
}
