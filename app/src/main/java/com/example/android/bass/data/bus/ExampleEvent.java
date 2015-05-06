package com.example.android.bass.data.bus;

public class ExampleEvent {

    private final boolean really;

    public ExampleEvent(boolean really) {
        this.really = really;
    }

    public boolean ohReally() {
        return really;
    }
}
