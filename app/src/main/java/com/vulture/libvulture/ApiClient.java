package com.vulture.libvulture;

import com.google.gson.Gson;
import com.vulture.libvulture.model.VultureConnection;
import com.vulture.libvulture.model.VultureUser;
import com.vulture.libvulture.model.VultureUserData;
import com.vulture.libvulture.response.ApiResponse;
import com.vulture.libvulture.response.DelConnectionResponse;
import com.vulture.libvulture.response.GetConnectionResponse;
import com.vulture.libvulture.response.InviteResponse;
import com.vulture.libvulture.response.RegisterResponse;
import com.vulture.libvulture.response.SignResponse;
import com.vulture.libvulture.response.UserDataResponse;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SuiBian on 2017/1/18.
 */

public class ApiClient {

    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

//    private static final String PROTOCAL = "http";
    private static final String API_PREFIX = "/api";
    private static final String HOST = Config.SERVER_HOST + API_PREFIX;

    private static final String registerUrl = "";
    private static final String getUserDataUrl = "";
    private static final String getConnectionUrl = "/connection";
    private static final String delConnectionUrl = "/connection";
    private static final String signinUrl = "/signIn";
    private static final String inviteUrl = "/invite";

    public static VultureContract.User register(String username, String password) throws IOException {
        RequestBody rb = RequestBody.create(mediaType, "username=" + username + "&" + "password=" + password);
        Request req = new Request.Builder()
                .url(HOST + registerUrl)
                .post(rb)
                .build();
        Response res = client.newCall(req).execute();
        Gson gson = new Gson();
        String re = res.body().string();
        ApiResponse rr = gson.fromJson(re, RegisterResponse.class);
        if (rr != null && rr.success()) {
            return new VultureUser(username, password);
        } else {
            return null;
        }
    }

    public static VultureUserData getUserData(String username,String password) throws IOException {
        Request req = new Request.Builder()
                .url(HOST + getUserDataUrl + "/" + username + "?password=" + password)
                .get()
                .build();
        Response res = client.newCall(req).execute();
        Gson gson = new Gson();
        String re = res.body().string();
        ApiResponse udr = gson.fromJson(re, UserDataResponse.class);
        if(udr != null && udr.success()){
            return (VultureUserData) udr.getMessage();
        }else{
            return null;
        }
    }

    public static VultureConnection getConnection(String username,String password) throws IOException {
        Request req = new Request.Builder()
                .url(HOST  + "/" + username + getConnectionUrl + "?password=" + password)
                .get()
                .build();
        Response res = client.newCall(req).execute();
        Gson gson = new Gson();
        String re = res.body().string();
        ApiResponse gcr = gson.fromJson(re, GetConnectionResponse.class);
        if(gcr != null && gcr.success()){
            return (VultureConnection) gcr.getMessage();
        }else{
            return null;
        }
    }

    public static boolean deleteConnection(String username,String password) throws IOException {
        Request req = new Request.Builder()
                .url(HOST + "/" + username + delConnectionUrl + "?password=" + password)
                .delete()
                .build();
        Response res = client.newCall(req).execute();
        Gson gson = new Gson();
        String re = res.body().string();
        ApiResponse dcr = gson.fromJson(re, DelConnectionResponse.class);
        return dcr != null && dcr.success();
    }

    public static boolean signIn(String username) throws IOException {
        Request req = new Request.Builder()
                .url(HOST + "/" + username + signinUrl)
                .get()
                .build();
        Response res = client.newCall(req).execute();
        Gson gson = new Gson();
        String re = res.body().string();
        ApiResponse sr = gson.fromJson(re, SignResponse.class);
        return sr != null && sr.success();
    }

    public static boolean invite(String username,String password, String code) throws IOException {
        RequestBody rb = RequestBody.create(mediaType, "username=" + username + "&" + "password=" + password + "&" + "code=" + code);
        Request req = new Request.Builder()
                .url(HOST + "/" + username + inviteUrl)
                .post(rb)
                .build();
        Response res = client.newCall(req).execute();
        Gson gson = new Gson();
        String re = res.body().string();
        ApiResponse ir = gson.fromJson(re, InviteResponse.class);
        return ir != null && ir.success();
    }

}
