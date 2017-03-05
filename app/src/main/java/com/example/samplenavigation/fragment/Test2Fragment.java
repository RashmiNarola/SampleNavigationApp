package com.example.samplenavigation.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.samplenavigation.GoogleMapActivity;
import com.example.samplenavigation.R;
import com.example.samplenavigation.model.TransportModel;
import com.example.samplenavigation.util.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rashmi on 3/4/2017.
 */

public class Test2Fragment  extends Fragment {

    AppCompatButton btnNavigate;
    AppCompatTextView tvCar, tvTrain;
    AppCompatSpinner spnrName;

    // URL to get JSON Data
    private static String url = "http://express-it.optusnet.com.au/sample.json";
    ArrayList<TransportModel> contactList = new ArrayList<>();

    public Test2Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transport_view, container, false);

        setView(rootView);
        return rootView;
    }

    private void setView(View view) {
        tvCar = (AppCompatTextView) view.findViewById(R.id.tvCar);
        tvTrain = (AppCompatTextView) view.findViewById(R.id.tvTrain);

        spnrName = (AppCompatSpinner) view.findViewById(R.id.spnrName);

        btnNavigate = (AppCompatButton) view.findViewById(R.id.btnNavigate);
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoogleMapActivity.class);//
                intent.putExtra("latitude", contactList.get(spnrName.getSelectedItemPosition()).getLocation().getLatitude());
                intent.putExtra("longitude", contactList.get(spnrName.getSelectedItemPosition()).getLocation().getLongitude());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_exit, R.anim.activity_enter);
            }
        });

        new GetTransportDetailsAsync().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetTransportDetailsAsync extends AsyncTask<Object, Object, String> {

        ProgressDialog pDialog;
        String TAG = "GetTransportDetailsAsy";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(Object... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.v(TAG, "Response from url: " + jsonStr);

            return jsonStr;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            if (result != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(result);

                    // Getting JSON Array node
                    JSONArray contacts = new JSONArray(result);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        TransportModel transportModel = new TransportModel();
                        transportModel.setId(c.getInt("id"));
                        transportModel.setName(c.getString("name"));

                        TransportModel.FromCentralModel fromCentralModel = new TransportModel(). new FromCentralModel();
                        JSONObject fromcentral = c.getJSONObject("fromcentral");
                        if(fromcentral.has("car"))
                            fromCentralModel.setCar(fromcentral.getString("car"));
                        if(fromcentral.has("train"))
                            fromCentralModel.setTrain(fromcentral.getString("train"));
                        transportModel.setFromcentral(fromCentralModel);

                        TransportModel.LocationModel locationModel = new TransportModel(). new LocationModel();
                        JSONObject location = c.getJSONObject("location");
                        locationModel.setLatitude(location.getString("latitude"));
                        locationModel.setLongitude(location.getString("longitude"));
                        transportModel.setLocation(locationModel);

                        contactList.add(transportModel);
                    }
                } catch (final JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Couldn't get json from server. Check LogCat for possible errors!", Toast.LENGTH_LONG).show();
                    }
                });
            }

            try {
                List<String> list = new ArrayList<String>();
//                list.add("Select");
                for (int i = 0; i < contactList.size(); i++) {
                    list.add(contactList.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_selected_item_view, list);
                adapter.setDropDownViewResource(R.layout.spinner_drop_down_list_item);
                spnrName.setAdapter(adapter);

                spnrName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(contactList.get(position).getFromcentral() != null && contactList.get(position).getFromcentral().getCar() != null) {
                            tvCar.setText(contactList.get(position).getFromcentral().getCar());
                            tvCar.setVisibility(View.VISIBLE);
                        }
                        else
                            tvCar.setVisibility(View.GONE);
                        if(contactList.get(position).getFromcentral() != null && contactList.get(position).getFromcentral().getTrain() != null) {
                            tvTrain.setText(contactList.get(position).getFromcentral().getTrain());
                            tvTrain.setVisibility(View.VISIBLE);
                        }
                        else
                            tvTrain.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
