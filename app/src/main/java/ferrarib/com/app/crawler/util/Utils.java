package ferrarib.com.app.crawler.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ferrarib.com.app.crawler.FetchAddressIntentService;

/**
 * Created by bruno on 7/11/16.
 */
public class Utils implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String PROPS_FILE_NAME = "api.properties";
    private static final String TAG = Utils.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST_FINE_LOCATION = 0;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String mAddressOutput;
    private ResultReceiver mAddressResultReceiver = new AddressResultReceiver(new Handler());
    private Activity activity;

    public Utils(Activity activity) {
        this.activity = activity;
        this.buildApiClient();
    }

    public static String getProperty(String key, Context ctx) throws IOException {
        Properties props = new Properties();
        AssetManager assetManager = ctx.getAssets();
        InputStream stream = assetManager.open(PROPS_FILE_NAME);
        props.load(stream);

        return props.getProperty(key);
    }

    protected synchronized void buildApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private void requestLocationPermissions() {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_FINE_LOCATION);
    }

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    protected void startIntentService() {
        Intent intent = new Intent(activity, FetchAddressIntentService.class);
        intent.putExtra(FetchAddressIntentService.Constants.RECEIVER, mAddressResultReceiver);
        intent.putExtra(FetchAddressIntentService.Constants.LOCATION_DATA_EXTRA, mLastLocation);
        activity.startService(intent);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestLocationPermissions();
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            Log.d(TAG, "LatLng: " + mLastLocation.getLatitude() + " " + mLastLocation.getLongitude());
            startIntentService();
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "Google Api connection suspended by cause " + cause);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Failed while trying to establish a connection with Google Api Client.\n"
                + "(" +connectionResult.getErrorCode()+ "): " + connectionResult.getErrorMessage());
    }

    @SuppressLint("ParcelCreator")
    class AddressResultReceiver extends ResultReceiver {

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            mAddressOutput = resultData.getString(FetchAddressIntentService.Constants.RESULT_DATA_KEY);
            Log.d(TAG, "ADDRESS: " + mAddressOutput);

            if (resultCode == FetchAddressIntentService.Constants.SUCCESS_RESULT) {
                //TODO: do something if address was found.
            }
        }
    }

}
