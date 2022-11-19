package com.gjh.learn.rpc.client.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * created on 2022/11/19
 *
 * @author kevinlights
 */
public class ServiceFactory<T> {
    private Class<T> interfaceType;

    public ServiceFactory(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    public T getObject() {
        InvocationHandler handler = new ServiceProxy<>();
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, handler);
    }

    public T getRemoteObject(String remoteClass) {
        InvocationHandler handler = new DynamicServiceProxy<>(remoteClass);
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, handler);
    }
}
