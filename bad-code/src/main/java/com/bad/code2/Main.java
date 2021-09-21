package com.bad.code2;

import com.bad.code2.shapes.Cube;
import com.bad.code2.shapes.Square;

public class Main {
    public static void main(String... args) {
        Cube qube = new Cube(1d, 1d, 1d, 10d);
        System.out.println("Qube volume: " + qube.getVolume());

        Square square = new Square(1d, 1d, 5d);
        System.out.println("Square perimeter: " + square.getPerimeter());
    }

}
