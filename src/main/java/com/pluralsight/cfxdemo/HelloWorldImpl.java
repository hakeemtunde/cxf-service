
package com.pluralsight.cfxdemo;

import javax.jws.WebService;

@WebService(endpointInterface = "com.pluralsight.cfxdemo.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

