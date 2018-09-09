package com.indeed.hazizz.Communication.Requests;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.indeed.hazizz.Communication.POJO.Response.CustomResponseHandler;
import com.indeed.hazizz.Communication.RequestInterface1;
import com.indeed.hazizz.SharedPrefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Request {

    public final String BASE_URL = "https://hazizz.duckdns.org:8080/";
  //  private com.indeed.hazizz.Communication.POJO.Requests.RequestInterface pp;
   // private ParentPOJO pp;
    public RequestInterface1 requestType;

    public  HashMap<String, Object> response1;
    private HashMap<String, Object> request;

    private Retrofit retrofit;

    Call<HashMap<String, Object>>  call;
    RequestTypes aRequest;

    CustomResponseHandler cOnResponse;
    Context context;


    public Request(Context context, String reqType, HashMap<String, Object>  request, CustomResponseHandler cOnResponse){
        this.cOnResponse = cOnResponse;
        this.request = request;
        this.context = context;

        Gson gson = new GsonBuilder().serializeNulls().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        findRequestType(reqType);
     //   requestType = new Login();
        aRequest = retrofit.create(RequestTypes.class);
    }

    public void findRequestType(String reqType){
        switch(reqType){
            case "register":
                requestType = new Register();
                break;
            case "login":
                requestType = new Login();
                break;
            case "me":
                requestType = new Me();
                break;
        }
    }

    public void makeCall2(){
        call.enqueue(new Callback<HashMap<String, Object> >() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                Log.e("hey", "gotResponse");
                Log.e("hey", response.raw().toString());

                if(response.body() != null){ // response != null
                    Log.e("hey", (String)response.body().toString());
                    if(response.body().containsKey("errorCode")) {
                        cOnResponse.onErrorResponse(response.body());
                        Log.e("hey", "2");
                    }else{
                        response1 = response.body();
                        cOnResponse.onResponse(response1);
                        Log.e("hey", "3");
                    }
                }else {
                    try {
                        String strError = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(strError);
                        HashMap<String, Object> errorResponse = new HashMap<String, Object>();

                        errorResponse.put("time", String.valueOf(errorJson.get("time")));
                        errorResponse.put("errorCode", String.valueOf(errorJson.get("errorCode")));
                        errorResponse.put("title", String.valueOf(errorJson.get("title")));
                        errorResponse.put("message", String.valueOf(errorJson.get("message")));

                        cOnResponse.onErrorResponse(errorResponse);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // Log.e("hey", "errorCode : " + response.errorBody().get("errorCode"));

             //   Log.e("hey", "errorCode is : " + response.body().getErrorCode());
                // responseHandler.checkErrorCode(response.body().getErrorCode());
                // responseHandler.checkHttpStatus(response.code());

            }
            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {
                cOnResponse.onFailure();
            }
        });
      // TODO  requestType.get("key");
    }

    public void makeCall(){
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Log.e("hey", "gotResponse");
                Log.e("hey", response.raw().toString());

                if(response.body() != null){ // response != null
                    Log.e("hey", (String)response.body().toString());
                    if(response.body().containsKey("errorCode")) {
                        cOnResponse.onErrorResponse(response.body());
                        Log.e("hey", "2");
                    }else{
                        response1 = response.body();
                        cOnResponse.onResponse(response1);
                        Log.e("hey", "3");
                    }
                }else {
                    try {
                        String strError = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(strError);
                        HashMap<String, Object> errorResponse = new HashMap<String, Object>();

                        errorResponse.put("time", String.valueOf(errorJson.get("time")));
                        errorResponse.put("errorCode", String.valueOf(errorJson.get("errorCode")));
                        errorResponse.put("title", String.valueOf(errorJson.get("title")));
                        errorResponse.put("message", String.valueOf(errorJson.get("message")));

                        cOnResponse.onErrorResponse(errorResponse);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // Log.e("hey", "errorCode : " + response.errorBody().get("errorCode"));

                //   Log.e("hey", "errorCode is : " + response.body().getErrorCode());
                // responseHandler.checkErrorCode(response.body().getErrorCode());
                // responseHandler.checkHttpStatus(response.code());

            }
            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                cOnResponse.onFailure();
            }
        });
        // TODO  requestType.get("key");
    }

    public HashMap<String, Object>  getResponse(){
        return response1;
    }

    public class Login implements RequestInterface1 {
        //   public String name = "register";
        Login(){
            Log.e("hey", "created Login object");
        }

        @Override
        public void setupCall() {
            HashMap<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Content-Type", "application/json");

            HashMap<String, Object> body = new HashMap<String, Object>();
            body.put("username", "bela");
            body.put("password", "sasasajt");

            //  Log.e("hey", requestJson.toString());
            call = aRequest.login(headerMap, request);
        }

        public HashMap<String, Object>  getResponse() {
            return response1;
        }
    }

    public class Register implements RequestInterface1 {
     //   public String name = "register";
        Register(){
            Log.e("hey", "created");
        }

        @Override
        public void setupCall() {
            HashMap<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Content-Type", "application/json");
          //  Log.e("hey", requestJson.toString());
            call = aRequest.register(headerMap, request);
        }

        public HashMap<String, Object>  getResponse() {
            return response1;
        }
    }

    public class Me implements RequestInterface1 {
        //   public String name = "register";
        Me(){
            Log.e("hey", "created Me object");
        }

        @Override
        public void setupCall() {
            HashMap<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImJlbGEiLCJzdWIiOiJBdXRoZW50aWNhdGlvbiB0b2tlbiIsImlhdCI6MTUzNjM4NTk1OCwiZXhwIjoxNTM2NDcyMzU4fQ.Zdt0ja3q9mSyKVXogOpwZwB_Ej7cgZEgaCkGL3EjOzGF4qEltXLky7Yv3H9lTeatolifu22-etFDT_QMMJubAw");//SharedPrefs.getString(context, "token", "token"));
            call = aRequest.me(headerMap);
        }
        public HashMap<String, Object>  getResponse() {
            return response1;
        }
        public void customMethod(){}
    }
}
