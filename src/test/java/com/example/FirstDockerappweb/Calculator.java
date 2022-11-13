package com.example.FirstDockerappweb;

public class Calculator {

    private int factor;

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}