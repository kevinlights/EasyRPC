package com.gjh.learn.rpc.client;

import com.gjh.learn.rpc.client.proxy.ServiceFactory;
import com.gjh.learn.rpc.client.remote.TestService;

/**
 * created on 2022/11/19
 *
 * @author kevinlights
 */
public class MyClientApp {

    private TestService testService;

    public void init() {
        // init proxy objects
        ServiceFactory<TestService> factory = new ServiceFactory<>(TestService.class);
        testService = factory.getObject();
    }

    public void run() {
        String res = testService.sayHello("Kevin");
        System.out.println(res);
    }

    public static void main(String[] args) {
        MyClientApp app = new MyClientApp();
        System.out.println("Init proxy objects now...");
        app.init();

        System.out.println("MyClientApp is running, call rpc now...");
        app.run();
    }
}
