package uta.cse3310;
import java.util.Set;

public class OnlineUserList {
    private Set<String> onlineUsers;

    public OnlineUserList(Set<String> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }
    public Set<String> getOnlineUsers() {
        return onlineUsers;
    }
    public void setOnlineUsers(Set<String> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }
}