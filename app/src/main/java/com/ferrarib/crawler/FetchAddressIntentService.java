package com.ferrarib.crawler;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by bruno on 7/14/16.
 */
public class FetchAddressIntentService extends IntentService {

    private String errorMessage;
    private List<Address> addresses;
    protected ResultReceiver mResultReceiver;

    public FetchAddressIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mResultReceiver = intent.getParcelableExtra(Constants.RECEIVER);

        if (mResultReceiver == null) {
            Log.wtf(TAG, "No receiver received. There is nowhere to send the results.");
            return;
        }

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        Location location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);

        if (location == null) {
            errorMessage = getString(R.string.no_location_data);
            Log.e(TAG, errorMessage);
            deliverResultsToReceiver(Constants.FAILURE_RESULT, errorMessage);
        }

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            errorMessage = getString(R.string.service_not_available);
            Log.e(TAG, errorMessage , e);
        } catch (IllegalArgumentException i) {
            Log.e(TAG, errorMessage + "Latitude: " + location.getLatitude()
                    + ", Longitude: " + location.getLongitude(), i);
        }

        if (noAddressesFound()) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_addresses_found);
                Log.d(TAG, errorMessage);
            }

            deliverResultsToReceiver(Constants.FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<>();

            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i(TAG, getString(R.string.address_found));

            deliverResultsToReceiver(Constants.SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"), addressFragments));
        }
    }

    private void deliverResultsToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY, message);
        mResultReceiver.send(resultCode, bundle);
    }

    private boolean noAddressesFound() {
        return addresses == null || addresses.size() == 0;
    }

    private static final String TAG = FetchAddressIntentService.class.getSimpleName();

    public final class Constants {
        public static final int SUCCESS_RESULT = 0;
        public static final int FAILURE_RESULT = 1;
        public static final String PACKAGE_NAME = "com.ferrarib.crawler";
        public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
        public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
        public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";
    }
}
