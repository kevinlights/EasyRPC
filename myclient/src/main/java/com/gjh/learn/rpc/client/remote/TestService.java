package com.gjh.learn.rpc.client.remote;

import com.gjh.learn.rpc.client.proxy.RemoteClass;

/**
 * created on 2022/11/19
 *
 * @author kevinlights
 */
@RemoteClass("com.github.yeecode.easyrpc.server.service.TestService")
public interface TestService {
    String sayHello(String name);
}
