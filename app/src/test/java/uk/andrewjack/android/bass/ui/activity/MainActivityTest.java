package com.example.android.bass.ui.activity;

import android.os.Bundle;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MainActivityTest {
    private MainActivity sut;

    @Before
    public void setUp() throws Exception {
        sut = new MainActivity();
    }

    @Ignore
    @Test
    public void firstEverTest() {
        sut.onCreate(new Bundle());
    }
}