package com.vulture.libvulture.model;

import com.vulture.libvulture.VultureContract;

/**
 * Created by han on 2016/12/6.
 */

public class VultureUser implements VultureContract.User{
    private String username;
    private String password;

    public VultureUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public long getRemainTransfer() {
        return 0;
    }

    @Override
    public void updateState() {

    }

    @Override
    public long lastUpdateTime() {
        return 0;
    }
}
