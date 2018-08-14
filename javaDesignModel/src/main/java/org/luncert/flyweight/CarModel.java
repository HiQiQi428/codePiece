package org.luncert.flyweight;

public class CarModel {

    private String name;
    private double height;
    private double width;
    private double length;

    public CarModel(String name, double height, double width, double length) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public String getName() { return name; }
    public double getHeight() { return height; }
    public double getWidth() { return width; }
    public double getLength() { return length; }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("CarModel [name = ").append(name)
                .append(", height = ").append(height)
                .append(", width = ").append(width)
                .append(", length = ").append(length).append("]").toString();
    }
    
}