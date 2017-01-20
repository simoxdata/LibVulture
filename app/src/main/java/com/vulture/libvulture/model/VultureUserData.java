package com.vulture.libvulture.model;

import com.vulture.libvulture.VultureContract;

/**
 * Created by han on 2016/11/28.
 */

public class VultureUserData implements VultureContract.UserData{
    private String username;
    private String password;
    private long transfer;
    private long transfer_limit;
    private String inviter;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public long getTransfer() {
        return transfer;
    }

    @Override
    public long getTransferLimit() {
        return transfer_limit;
    }

    @Override
    public String getInviter() {
        return inviter;
    }

    public VultureUserData setInviter(String inviter) {
        this.inviter = inviter;
        return this;
    }

    public VultureUserData setUsername(String username) {
        this.username = username;
        return this;
    }

    public VultureUserData setPassword(String password) {
        this.password = password;
        return this;
    }

    public VultureUserData setTransfer(long transfer) {
        this.transfer = transfer;
        return this;
    }

    public VultureUserData setTransfer_limit(long transfer_limit) {
        this.transfer_limit = transfer_limit;
        return this;
    }
}
