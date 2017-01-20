package com.vulture.libvulture.model;

import android.content.Context;

import com.vulture.libvulture.ApiClient;
import com.vulture.libvulture.VultureContract;

import java.io.IOException;

/**
 * Created by han on 2016/12/6.
 */

public class VultureUser implements VultureContract.User{

    private String username;
    private String password;

    private VultureUserData mData;
    private VultureContract.Connection mConnection;

    private long mLastUpdateTime;

    public VultureUser(String username, String password) {
        this.username = username;
        this.password = password;
        mConnection = new VultureConnection(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // call it after update or it won't promise the reliability
    // unit in Byte
    @Override
    public long getRemainTransfer() {
        return mData.getTransfer_limit() - mData.getTransfer();
    }

    @Override
    public void updateState() {
        try {
            mData = ApiClient.getUserData(username,password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mConnection.updateState();
        mLastUpdateTime = System.currentTimeMillis();
    }

    @Override
    public long lastUpdateTime() {
        return mLastUpdateTime;
    }

    @Override
    public VultureContract.Connection getConnection() {
        return mConnection;
    }

    @Override
    public void startConnect(Context context) {
        mConnection.start(context);
    }
}
