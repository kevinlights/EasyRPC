package com.gjh.learn.rpc.client;

import com.gjh.learn.rpc.client.proxy.MyRPC;
import com.gjh.learn.rpc.client.proxy.ServiceFactory;
import com.gjh.learn.rpc.client.remote.TestService;

import java.lang.reflect.Field;

/**
 * created on 2022/11/19
 *
 * @author kevinlights
 */
public class MyClientApp {

    @MyRPC("com.github.yeecode.easyrpc.server.service.TestService")
    private TestService testService;

    public void init() throws Exception {
        // init proxy objects
        // 1. use class annotation value
        // ServiceFactory<TestService> factory = new ServiceFactory<>(TestService.class);
        // testService = factory.getObject();

        // 2. use constructor parameter
        // set remote class while initializing object, no need to touch the interface.
        // testService = factory.getRemoteObject("com.github.yeecode.easyrpc.server.service.TestService");

        // 3. use field annotation value
        // get remote class from field annotation
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            String remoteClass = field.getAnnotation(MyRPC.class).value();
            if (null != remoteClass && !"".equals(remoteClass.trim())) {
                try {
                    System.out.println(field.getType());
                    field.setAccessible(true);
                    field.set(this, new ServiceFactory<>(field.getType()).getObject());
                } catch (Exception e) {
                    System.out.println("init proxy error: " + e.getMessage());
                    throw new Exception("Init proxy error: " + e.getMessage());
                }
            }
        }
    }

    public void run() {
        String res = testService.sayHello("Kevin");
        System.out.println(res);
    }

    public static void main(String[] args) throws Exception {
        MyClientApp app = new MyClientApp();
        System.out.println("Init proxy objects now...");
        app.init();

        System.out.println("MyClientApp is running, call rpc now...");
        app.run();
    }
}
