package com.daiwei.v2;

public class Car {

    private State state;

    public void open() {
        state.open();
    }

    public void close() {
        state.close();
    }

    public void run() {
        state.run();
    }

    public void stop() {
        state.stop();
    }
}
