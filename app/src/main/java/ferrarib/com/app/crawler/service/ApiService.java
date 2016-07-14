package ferrarib.com.app.crawler.service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import ferrarib.com.app.crawler.model.Response;
import ferrarib.com.app.crawler.model.WebHoseResource;
import ferrarib.com.app.crawler.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bruno on 7/13/16.
 */
public class ApiService {

    private Context ctx;

    public ApiService(Context ctx) {
        this.ctx = ctx;
    }

    private WebHoseResource buildService() {
        try {
            TOKEN = Utils.getProperty("api_token", ctx.getApplicationContext());
            BASE_URL = Utils.getProperty("api_url", ctx.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WebHoseResource.class);
    }

    public void getSimpleQuery(String query) {
        Call<ferrarib.com.app.crawler.model.Response> call
                = mApiService.simpleQuery(query, TOKEN, RESPONSE_TYPE, QUERY_SIZE);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                int statusCode = response.code();
                Log.d(TAG, String.valueOf(response.body()));
                Log.d(TAG, String.valueOf(statusCode));
                Log.d(TAG, String.valueOf(response.raw()));
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e(TAG, t.getMessage() + "Caused by: " + t.getCause());
            }
        });

    }

    public void queryByPerformanceScore(int score, String query) throws UnsupportedEncodingException {
        query = validateQueryPerformanceScore(score, query);

        mApiService = this.buildService();
        Call<Response> call
                = mApiService.queryByPerformanceMeasure(query, TOKEN, RESPONSE_TYPE, QUERY_SIZE);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                int statusCode = response.code();
                Response resp = response.body();
                Log.d(TAG, String.valueOf(resp.getPosts()));
                Log.d(TAG, String.valueOf(statusCode));
                Log.d(TAG, String.valueOf(response.raw()));
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e(TAG, t.getMessage() + "Caused by: " + t.getCause());
            }
        });
    }

    public void queryByLocation(int score, String query, LatLng latLng) {
        //TODO: implement
    }

    @NonNull
    private String validateQueryPerformanceScore(int score, String query)
            throws UnsupportedEncodingException {
        if (score < 0 || score > 10)
            throw new RuntimeException("Score must be a positive number between 0 and 10");
        else if (score == 10)
            query += " performance_score:" + score;
        query += " performance_score:>" + score;

        return query;
    }

    private static final String TAG = ApiService.class.getSimpleName();
    private static final int QUERY_SIZE = 10;
    private static final String RESPONSE_TYPE = "json";
    private static String TOKEN = null;
    private static String BASE_URL = null;
    private WebHoseResource mApiService;
}
