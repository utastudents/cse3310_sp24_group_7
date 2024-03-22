package uta.cse3310;

public enum PlayerType {
    NOPLAYER {
        @Override
        public void method1() {
       return 0;
        }

        @Override
        public void method2() {
           return 0;
        }
    },
    XPLAYER {
        @Override
        public void method1() {
         return 0;
        }

        @Override
        public void method2() {
    return 0;
        }
    },
    OPLAYER {
        @Override
        public void method1() {
           return 0;
        }

        @Override
        public void method2() {
        return 0;
        }
    };

    
    PlayerType() {
    }
    public abstract void method1();
    public abstract void method2();
}
