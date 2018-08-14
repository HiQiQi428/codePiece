package org.luncert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.repChain.RepChainClient;
import org.luncert.repChain.Request;
import org.luncert.repChain.Result;

@RunWith(JUnit4.class)
public class TestRepChain {

    @Test
    public void test() {
        Request request = new Request.Builder().setName("张三").setDays(5).setReason("事假").build();
        RepChainClient client = new RepChainClient();
        Result result = client.process(request);
        System.out.println("结果: " + result.toString());
    }
    
}