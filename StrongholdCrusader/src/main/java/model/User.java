package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import model.buildings.Storage;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String passwordRecoveryQuestion;
    private String PasswordRecoveryAnswer;
    private String slogan;

    private int score;
    private int rank;

    private Storage storage;
    private Governance governance;

    boolean isLoggedIn;

    private ArrayList<Trade> trades = new ArrayList<>();


    public User(String username, String password, String nickname, String email,
                String passwordRecoveryQuestion, String PasswordRecoveryAnswer, String slogan) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
        this.PasswordRecoveryAnswer = PasswordRecoveryAnswer;
        this.slogan = slogan;
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

    public Storage getStorage() {
        return storage;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Governance getGovernment() {
        return governance;
    }

    public void setGovernment(Governance governance) {
        this.governance = governance;
    }

    public Governance getGovernance() {
        return governance;
    }

    public void setGovernance(Governance governance) {
        this.governance = governance;
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void setTrades(ArrayList<Trade> trades) {
        this.trades = trades;
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
}
