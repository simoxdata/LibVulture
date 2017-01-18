package com.vm.shadowsocks.wrapper;

import android.content.Context;
import android.content.Intent;

import com.vm.shadowsocks.core.LocalVpnService;

/**
 * Created by SuiBian on 2017/1/18.
 */

public class SSService {
    public static void connect(Context context, String ip,String port,String password){
        connect(context,parseProxyUrl(ip,port,password));
    }

    public static void connect(Context context, String ProxyUrl){
        LocalVpnService.ProxyUrl = ProxyUrl;
        context.startService(new Intent(context, LocalVpnService.class));
    }

    public static void stop(){
        LocalVpnService.IsRunning = false;
    }

    public static boolean isRunning(){
        return LocalVpnService.IsRunning;
    }

    private static String parseProxyUrl(String ip,String port,String password){
        return "ss://aes-256-cfb:" + password + "@" + ip + ":" + port;
    }
}
