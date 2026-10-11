package org.example;

public class Calculator {
    public  int add(int a, int b){return a + b;}
    public  int subtract(int a, int b){return a - b;}
    public  int multiply(int a, int b){return a * b;}
    public  double divide(int a, int b){
        if(b == 0){
            throw new IllegalArgumentException("cannot divide by zero");
        }
        return (double) a / b;
    }
    public void slowOperation() throws InterruptedException{
        Thread.sleep(1000);
    }

    public double sin(double radians) {
        return Math.sin(radians);
    }

    public double cos(double radians) {
        return Math.cos(radians);
    }

    public double tan(double radians) {
        double cosVal = Math.cos(radians);
        if (Math.abs(cosVal) < 1e-12) {
            throw new IllegalArgumentException("tangent is undefined for this angle");
        }
        return Math.tan(radians);
    }

    public double cot(double radians) {
        double sinVal = Math.sin(radians);
        if (Math.abs(sinVal) < 1e-12) {
            throw new IllegalArgumentException("cotangent is undefined for this angle");
        }
        return Math.cos(radians) / sinVal;
    }
}
