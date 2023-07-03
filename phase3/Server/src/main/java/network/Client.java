package network;

import model.User;

public class Client {
    private User user;
    private Connection connection;

    public Client(User user, Connection connection) {
        this.user = user;
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Connection getConnection() {
        return connection;
    }

    public User getUser() {
        return user;
    }
}
