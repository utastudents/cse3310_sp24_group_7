package uta.cse3310;

public enum PlayerType {
    NOPLAYER {
        @Override
        public void method1() {
            // Empty method for NOPLAYER
        }

        @Override
        public void method2() {
           
        }
    },
    XPLAYER {
        @Override
        public void method1() {
            // Empty method for XPLAYER
        }

        @Override
        public void method2() {
    
        }
    },
    OPLAYER {
        @Override
        public void method1() {
            // Empty method for OPLAYER
        }

        @Override
        public void method2() {
        
        }
    };

    // Constructor
    PlayerType() {
      
    }


    public abstract void method1();
    public abstract void method2();
}
