package com.example.snipergame.supportFunctions;

public class MathUtils {
    public static double square(double x) {
        return x * x;
    }
    public static double distant(double x1, double y1, double x2, double y2) {
        double distantX = square(x1 - x2);
        double distantY = square(y1 - y2);
        return Math.sqrt(distantX + distantY);
    }
}
