package com.bicontest.egg;

import android.app.Application;

public class glovalVariable extends Application {
    private int flashSecond = 5;
    private boolean flashRandBool = false;

    public int getFlashSecond() {
        return flashSecond;
    }
    public void setFlashSecond( int flashSecond ) {
        this.flashSecond = flashSecond;
    }

    public boolean getFlashRandBool() { return flashRandBool; }
    public void setFlashRandBool( boolean flashRandBool ) { this.flashRandBool = flashRandBool; }
}
