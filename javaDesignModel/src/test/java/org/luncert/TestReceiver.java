package org.luncert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.commandModel.Command;
import org.luncert.commandModel.Invoker;
import org.luncert.commandModel.RebootCommand;
import org.luncert.commandModel.Receiver;

@RunWith(JUnit4.class)
public class TestReceiver {

    @Test
    public void test() {
        Receiver receiver = new Receiver();
        Command cmd = new RebootCommand(receiver);
        Invoker invoker = new Invoker(cmd);
        invoker.action();
    }
}