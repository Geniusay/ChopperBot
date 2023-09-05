package org.example.pojo;

/**
 * @Description
 * @Author welsir
 * @Date 2023/9/4 22:08
 */
public class Platform {
    private String platName;
    private Account account;

    public Platform() {
    }

    public String toString() {
        return "Platform{platName='" + this.platName + "', account=" + this.account + "}";
    }

    public String getPlatName() {
        return this.platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
