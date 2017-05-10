package com.example.akhiljayan.nearbyshops;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class detailsActivity extends ListActivity {

    public String location = "";
    List<Double> latLong = null;
    String distance = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        location = i.getStringExtra("location");
        distance = i.getStringExtra("distance");
        latLong = this.getLocationFromAddress(location);

        //setContentView(R.layout.activity_details);
        new AsyncTask<Void, Void, List<ShowBlock>>() {
            @Override
            protected List<ShowBlock> doInBackground(Void... params) {
                return FetchDetails.listDetails(latLong, distance);
            }
            @Override
            protected void onPostExecute(List<ShowBlock> result) {
//                ArrayAdapter<String> adapter =
//                        new ArrayAdapter<String>(getApplicationContext(),
//                                R.layout.row, R.id.textView, result);
//                setListAdapter(adapter);

                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), result,
                        android.R.layout.simple_list_item_2, new String[]{"name", "category"},
                        new int[]{ android.R.id.text1, android.R.id.text2}) {

                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                        text1.setTextColor(Color.RED);
                        text2.setTextColor(Color.RED);
                        return view;
                    }
                };

                setListAdapter(adapter);
            }
        }.execute();
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        ShowBlock s = (ShowBlock) getListAdapter().getItem(position);
        Toast.makeText(getApplicationContext(), s.get("name") + " selected.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(v.getContext(), MapsActivity.class);
        String selected =  s.get("id");

        i.putExtra("id",selected);
        i.putExtra("name", s.get("name"));
        i.putExtra("lati", s.get("lati"));
        i.putExtra("longi", s.get("longi"));
        i.putExtra("contactnumber", s.get("contactnumber"));
        i.putExtra("contactemail", s.get("contactemail"));
        i.putExtra("category", s.get("category"));
        i.putExtra("entryaddress", s.get("entryaddress"));
        i.putExtra("postalcode", s.get("postalcode"));
        i.putExtra("website", s.get("website"));
        i.putExtra("openinghours", s.get("openinghours"));
        i.putExtra("dist", s.get("dist"));
        i.putExtra("facebookpage", s.get("facebookpage"));

        startActivity(i);
    }

    public List<Double> getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        List<Double> latlong = new ArrayList<Double>();

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            latlong.add(latitude);
            latlong.add(longitude);


        }catch(Exception e){

        }


        return latlong;
    }
}
