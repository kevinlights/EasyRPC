package com.github.yeecode.easyrpc.server.service;

/**
 * created on 2022/11/19
 *
 * @author kevinlights
 */
public class TestService {
    public String sayHello(String name) {
        System.out.println("call TestService -> sayHello, name: " + name);
        System.out.println("*************************");
        return "Hello, " + name;
    }
}
