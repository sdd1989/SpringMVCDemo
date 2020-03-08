package com.ql.socketio;


import io.socket.client.IO;
import io.socket.client.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {

        for(int i=0;i<1000;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            });
        }

    }

    private static void test(){
        String url = "http://localhost:3000";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            //失败重试次数
            options.reconnectionAttempts = 10;
            //失败重连的时间间隔
            options.reconnectionDelay = 1000;
            //连接超时时间(ms)
            options.timeout = 500;

            Socket socket = IO.socket(url, options);
            //监听自定义message事件
            socket.on("message", objects -> System.out.println("client: 收到message->" + Arrays.toString(objects)));
            //监听自定义订阅事件
//            socket.on("sub", objects -> System.out.println("client: " + "订阅成功，收到反馈->" + Arrays.toString(objects)));
//            socket.on(Socket.EVENT_CONNECT, objects -> {
//                socket.emit("sub", "我是訂閲對象");
//                System.out.println("client: " + "连接成功");
//            });
//            socket.on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"));
//            socket.on(Socket.EVENT_CONNECT_TIMEOUT, objects -> System.out.println("client: " + "连接超时"));
//            socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));
            socket.connect();
        } catch (Exception e) {
        }
    }
}
