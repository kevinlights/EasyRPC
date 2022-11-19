package com.gjh.learn.rpc.client.proxy;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * created on 2022/11/19
 *
 * @author kevinlights
 */
public class ServiceProxy<T> implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RemoteClass remoteClass = method.getDeclaringClass().getAnnotation(RemoteClass.class);
        if (null == remoteClass) {
            throw new Exception("远程类标志未指定");
        }

        ArrayList<Object> argTypeList = new ArrayList<>();
        if (null != args) {
            for (Object obj : args) {
                argTypeList.add(obj.getClass().getName());
            }
        }

        String argTypes = JSON.toJSONString(argTypeList);
        String argValues = JSON.toJSONString(args);

        Result result = HttpUtil.callRemoteService(remoteClass.value(), method.getName(), argTypes, argValues);

        if (result.isSuccess()) {
            return JSON.parseObject(result.getResultValue(), Class.forName(result.getResultType()));
        } else {
            throw new Exception("远程调用异常：" + result.getMessage());
        }
    }
}
