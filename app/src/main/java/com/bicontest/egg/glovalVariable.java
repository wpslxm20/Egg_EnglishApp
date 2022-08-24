package com.bicontest.egg;

import android.app.Application;

public class glovalVariable extends Application {
        private int flashSecond;

        public int getFlashSecond() {
            return flashSecond;
        }

        public void setFlashSecond( int flashSecond ) {
            this.flashSecond = flashSecond;
        }
}
