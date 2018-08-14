package org.luncert.prototype.register;

import java.util.HashMap;
import java.util.Map;

public class PrototypeManager {

    private static Map<String, Prototype> map = new HashMap<>();

    private PrototypeManager() {}

    public synchronized static void setPrototype(String prototypeId, Prototype prototype) {
        map.put(prototypeId, prototype);
    }

    public synchronized static void removePrototype(String prototypeId) {
        map.remove(prototypeId);
    }

    public synchronized static Prototype getPrototype(String prototypeId) throws Exception {
        Prototype prototype = map.get(prototypeId);
        if (prototype != null) return prototype;
        else throw new Exception("Prototype not exists");
    }
    
}