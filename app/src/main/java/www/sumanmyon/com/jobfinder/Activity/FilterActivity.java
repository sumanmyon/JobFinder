package www.sumanmyon.com.jobfinder.Activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.Locale;

import www.sumanmyon.com.jobfinder.Adapter.PlaceAutocompleteAdapter;
import www.sumanmyon.com.jobfinder.CheckNetwork.NetworkConnection;
import www.sumanmyon.com.jobfinder.ErrorAndExceptionHandler.ToastShow;
import www.sumanmyon.com.jobfinder.FetchData.GetDataFromProvider;
import www.sumanmyon.com.jobfinder.MainActivity;
import www.sumanmyon.com.jobfinder.R;

import static www.sumanmyon.com.jobfinder.URLs.ProviderURLs.GitHubURL;
import static www.sumanmyon.com.jobfinder.URLs.ProviderURLs.searchGovURL;

public class FilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        PlaceAutocompleteAdapter.PlaceAutoCompleteInterface, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, View.OnClickListener{
    String[] provider = { "GitHub", "searchGov" };

    Spinner spin;

    EditText positionEditText, mSearchEdittext;
    Button searchButton;
    ImageView mClear;
    RecyclerView mRecyclerView;

    NetworkConnection connection;

    GoogleApiClient mGoogleApiClient;
    LinearLayoutManager llm;
    PlaceAutocompleteAdapter mAdapter;

    private static final LatLngBounds BOUNDS_NEPAL = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));


    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();

    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        spin = (Spinner) findViewById(R.id.spinner1);
        positionEditText = (EditText)findViewById(R.id.filter_job_position_editText);
        searchButton = (Button)findViewById(R.id.filter_job_search_button);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_search);
        mSearchEdittext = (EditText) findViewById(R.id.filter_job_location_editText);
        mClear = (ImageView) findViewById(R.id.clear);

        connection = new NetworkConnection(getApplicationContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provider);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        //For google place api integration
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();

        Geocoder geo = new Geocoder(this, new Locale("fa"));

        googleApiIntegrate();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connection.isNetworkConnection()){
                    search();
                }else {
                    new ToastShow(FilterActivity.this,"Please check your network.");
                }

            }
        });

    }

    private void googleApiIntegrate() {
        mRecyclerView.setHasFixedSize(true);
        llm = new LinearLayoutManager(FilterActivity.this);
        mRecyclerView.setLayoutManager(llm);

        mClear.setOnClickListener(this);

        mAdapter = new PlaceAutocompleteAdapter(this, R.layout.view_placesearch, mGoogleApiClient, BOUNDS_NEPAL, null);
        mRecyclerView.setAdapter(mAdapter);

        mSearchEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    mClear.setVisibility(View.VISIBLE);
                    if (mAdapter != null) {
                        mRecyclerView.setAdapter(mAdapter);
                        if (mRecyclerView.getVisibility() == View.GONE)
                            mRecyclerView.setVisibility(View.VISIBLE);
                    }
                } else {
                    mClear.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.GONE);
                }
                if (!s.toString().equals("") && mGoogleApiClient.isConnected()) {
                    mAdapter.getFilter().filter(s.toString());
                } else if (!mGoogleApiClient.isConnected()) {
                    Log.e("", "NOT CONNECTED");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == mClear) {
            mSearchEdittext.setText("");
            if (mAdapter != null) {
                mAdapter.clearList();
            }

        }
    }

    private void search() {
        String item = (String) spin.getSelectedItem();
        String term = positionEditText.getText().toString();
        String locationR = mSearchEdittext.getText().toString();

        Intent filterIntent = new Intent(FilterActivity.this, ShowDataUsingFilter.class);
        filterIntent.putExtra("provider",item);
        filterIntent.putExtra("query", term);
        filterIntent.putExtra("location", locationR);
        startActivity(filterIntent);
        //new ToastShow(this,item);
    }


    @Override
    public void onPlaceClick(ArrayList<PlaceAutocompleteAdapter.PlaceAutocomplete> mResultList, int position) {
        if (mResultList != null) {
            try {
                final String placeId = String.valueOf(mResultList.get(position).placeId);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a
                             Place object with additional details about the place.
                         */

                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(mGoogleApiClient, placeId);
                placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getCount() == 1) {
                            //Do the things here on Click.....
                            Intent data = new Intent();
                            data.putExtra("lat", String.valueOf(places.get(0).getLatLng().latitude));
                            data.putExtra("lng", String.valueOf(places.get(0).getLatLng().longitude));
                            setResult(FilterActivity.RESULT_OK, data);
                            String location = "";
                            location = places.get(0).getName().toString();

                            mSearchEdittext.setText(location);
                            mRecyclerView.setVisibility(View.GONE);

                            hideSoftInput(FilterActivity.this);
                            //finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (Exception e) {

            }

        }
    }

    public static void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String item = provider[position];
        //Toast.makeText(getApplicationContext(), "Selected User: "+item , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
