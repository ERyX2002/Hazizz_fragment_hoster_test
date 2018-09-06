package com.indeed.hazizz.Communication.Requests;

//import com.indeed.hazizz.Communication.POJO.Requests.Register;
import com.indeed.hazizz.Communication.POJO.Requests.RequestInterface;
import com.indeed.hazizz.Communication.POJO.Response.Error;
import com.indeed.hazizz.Communication.POJO.Response.ResponseInterface;
import com.indeed.hazizz.Communication.SetupInterface;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.*;

public interface RequestTypes{

 //   private HashMap<String, String> headerMap;

    @POST("register/")
    Call<HashMap<String, Object>> register(
            @HeaderMap Map<String, String> headers,
            @Body HashMap<String, Object>  register

    );

    @GET("users/")
    Call<HashMap<String, Object>> getUsers(
            @HeaderMap Map<String, String> headers,
            @Body JSONObject registerBody

    );

    @POST("auth/")
    Call<HashMap<String, Object>> login(
            @HeaderMap Map<String, String> headers,
            @Body HashMap<String, Object> registerBody

    );

/*
    public HashMap<String, String> makeHeader(){
        headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        return headerMap;
    }

    public Call<JSONObject> makeCall(JSONObject requestJson){
        Call<JSONObject> asd = call(makeHeader(), requestJson);
        return asd;
    }
 */

    //public void call();



   /* @Override
    public Call<JSONObject> register(Map<String, String> headers, JSONObject registerBody) {
        return null;
    }

    @Override
    public Call<JSONObject> auth(JSONObject authBody) {
        return null;
    } */
}
