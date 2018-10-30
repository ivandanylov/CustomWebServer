package com.study;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void start(int port, String webAppPath) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                    RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedWriter, webAppPath);
                    requestHandler.handle();
                }
            }
        }
    }
}
