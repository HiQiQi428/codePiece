package org.luncert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.responsibilityChain.ChainOfResponsibilityClient;
import org.luncert.responsibilityChain.Request;
import org.luncert.responsibilityChain.Result;

@RunWith(JUnit4.class)
public class TestResponsibilityChain {

    @Test
    public void test() {
        Request request = new Request.Builder().setName("张三").setDays(5).setReason("事假").build();
        ChainOfResponsibilityClient client = new ChainOfResponsibilityClient();
        Result result = client.execute(request);
        System.out.println("结果: " + result.toString());
    }
    
}