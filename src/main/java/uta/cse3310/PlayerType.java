package uta.cse3310;

public enum PlayerType {

    NOPLAYER {
        @Override
        public int getScore() {
            return 0;
        }

        @Override
        public void updateScore(int newScore) {
            // Do nothing for NOPLAYER
        }
    },

    XPLAYER {
        private String nickname;
        private int playercurrScore;
        private int playerID;

        @Override
        public int getScore() {
            return playercurrScore;
        }

        @Override
        public void updateScore(int newScore) {
            playercurrScore += newScore;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getPlayerID() {
            return playerID;
        }

        public void setPlayerID(int playerID) {
            this.playerID = playerID;
        }
    },

    OPLAYER {
        private String nickname;
        private int playercurrScore;
        private int playerID;

        @Override
        public int getScore() {
            return playercurrScore;
        }

        @Override
        public void updateScore(int newScore) {
            playercurrScore += newScore;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getPlayerID() {
            return playerID;
        }

        public void setPlayerID(int playerID) {
            this.playerID = playerID;
        }
    };

    PlayerType() {
    }

    public abstract int getScore();

    public abstract void updateScore(int newScore);
}