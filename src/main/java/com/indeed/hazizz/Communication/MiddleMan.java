package com.indeed.hazizz.Communication;

import android.util.Log;

import com.google.gson.Gson;
//import com.indeed.hazizz.Communication.POJO.ParentPOJO;
import com.indeed.hazizz.Communication.POJO.Requests.RequestInterface;
import com.indeed.hazizz.Communication.POJO.Response.CustomResponseHandler;
import com.indeed.hazizz.Communication.POJO.Response.ResponseInterface;
import com.indeed.hazizz.Communication.Requests.Request;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MiddleMan{

    private Request newRequest;
    private String requestType;
    public MiddleMan(String requestType, HashMap<String, Object>  pp, CustomResponseHandler cOnResponse) {
        this.requestType = requestType;
        newRequest = new Request(requestType, pp, cOnResponse);
    }

    public void sendRequest() {
        newRequest.requestType.setupCall();
        newRequest.makeCall();
    }

    public HashMap<String, Object>  getResponse() {
        return newRequest.getResponse();
    }
    public void getResponse1() {
        try {
            String a = newRequest.getResponse().toString();
            Log.e("hey", newRequest.getResponse().toString() + "yes");
        }catch (NullPointerException e){
            Log.e("hey", "not abble to: " + e.toString());
        }
    }




    /*
    private final String BASE_URL = "http://80.98.42.103:8080/";

    private JSONObject respondBody;
    private JsonBuilder requestBody;
    public MiddleMan(JsonBuilder requestBody){
        this.requestBody = requestBody;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserClient userClient = retrofit.create(UserClient.class);


        public void makeRequest(Call<JSONObject> call) {

            Call<JSONObject> call = userClient.register(headerMap, user); // Minden külön requestnek lesz
            //egy classa ahol a külömbözö
            // preferenciáít beálithatja
            //és akkor csak annyit kell hogy
            //register.setupPrefrences()
            // errorCallback = new MyCallback<ResponseBodies.Error>(responseHandler);

            // call.enqueue(errorCallback);

            call.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                    respondBody = response.body();

                    //    response.body().getTitle();
                    // responseHandler.checkErrorCode(response.body().getErrorCode());
                    // responseHandler.checkHttpStatus(response.code());

                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {
                    // responseHandler.checkHttpStatus(call.code());
                    Log.e("hey", "Registration was successful");
                    respondBody = null;
                }
            });
        }

    }



    }


*/


}
