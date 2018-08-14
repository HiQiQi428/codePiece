package org.luncert.flyweight;

import java.util.HashMap;
import java.util.Map;

public class CarModelFactory {

    private static Map<String, CarModel> map = new HashMap<>();

    private CarModelFactory() {}

    public static synchronized CarModel getModel(String name, double length, double width, double height) {
        if (map.containsKey(name)) return map.get(name);
        else {
            CarModel carModel = new CarModel(name, width, height, length);
            map.put(name, carModel);
            return carModel;
        }
    }

}