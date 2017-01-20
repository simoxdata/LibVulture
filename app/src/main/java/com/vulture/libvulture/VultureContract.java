package com.vulture.libvulture;

import android.content.Context;

/**
 * Created by SuiBian on 2017/1/18.
 */

public interface VultureContract {

    public interface Connection{

        public void start(Context context);

        public void stop();

        public boolean isConnecting();
        // in ms
        public long getStartTime();
        // in ms
        public long getActiveTime();
        // in byte
        public long getCostTransfer();

        public boolean updateState();

    }

    public interface User{

        public String getUsername();

        public String getPassword();
        // in byte
        public long getRemainTransfer();

        public void updateState();
        // timestamp in ms
        public long lastUpdateTime();

        public Connection getConnection();

        public void startConnect(Context context);
    }


}
