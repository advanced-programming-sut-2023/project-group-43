package model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    private ArrayList<User> friends = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String passwordRecoveryQuestion;
    private String PasswordRecoveryAnswer;
    private String slogan;
    private int score;
    private int rank;
    private Governance governance = new Governance();

    boolean isLoggedIn;

    private ArrayList<Trade> trades = new ArrayList<>();
    private int avatarNumber = 4;
    private HashMap<String, Cell[][]> mapsOfThisUser;


    public User(String username, String password, String nickname, String email,
                String passwordRecoveryQuestion, String PasswordRecoveryAnswer, String slogan) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
        this.PasswordRecoveryAnswer = PasswordRecoveryAnswer;
        this.slogan = slogan;
        this.mapsOfThisUser = new HashMap<>();
    }
    public HashMap<String, Cell[][]> getMapsOfThisUser() {
        return mapsOfThisUser;
    }

    public void AddToMapsOfThisUser(String string, Cell[][] cells) {
        this.mapsOfThisUser.put(string, cells);
    }

    public Boolean mapWithThisName(String name) {
        for (Map.Entry<String, Cell[][]> set : mapsOfThisUser.entrySet()) {
            if (set.getKey().equals(name)) return true;
        }
        return false;
    }
    public int getAvatarNumber() {
        return avatarNumber;
    }

    public void setAvatarNumber(int avatarNumber) {
        this.avatarNumber = avatarNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordRecoveryQuestion() {
        return passwordRecoveryQuestion;
    }

    public void setPasswordRecoveryQuestion(String passwordRecoveryQuestion) {
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
    }

    public String getPasswordRecoveryAnswer() {
        return PasswordRecoveryAnswer;
    }

    public void setPasswordRecoveryAnswer(String passwordRecoveryAnswer) {
        this.PasswordRecoveryAnswer = passwordRecoveryAnswer;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Governance getGovernance() {
        return governance;
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void setGovernance(Governance governance) {
        this.governance = governance;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void addFriend(User user) {
        friends.add(user);
    }
}
