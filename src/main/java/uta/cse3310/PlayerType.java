package uta.cse3310;

public enum PlayerType {
    NOPLAYER {
        @Override
        public void method1() {
            // Empty method for NOPLAYER
        }

        @Override
        public void method2() {
            // Empty method for NOPLAYER
        }
    },
    XPLAYER {
        @Override
        public void method1() {
            // Empty method for XPLAYER
        }

        @Override
        public void method2() {
            // Empty method for XPLAYER
        }
    },
    OPLAYER {
        @Override
        public void method1() {
            // Empty method for OPLAYER
        }

        @Override
        public void method2() {
            // Empty method for OPLAYER
        }
    };

    // Constructor
    PlayerType() {
        // Empty constructor
    }

    // Empty methods
    public abstract void method1();
    public abstract void method2();
}
