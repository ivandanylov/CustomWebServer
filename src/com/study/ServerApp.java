package com.study;

import java.io.File;
import java.io.IOException;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(3000, "resource" + File.separator + "webapp");
    }
}
