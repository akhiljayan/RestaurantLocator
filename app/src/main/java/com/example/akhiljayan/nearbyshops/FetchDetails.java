package com.example.akhiljayan.nearbyshops;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AkhilJayan on 10-May-17.
 */

public class FetchDetails extends java.util.HashMap<String,String>{

    final static String host = "https://appsbytsl.com/API/V1/Nearby/Food/H4D3S";

//    public String MasterCategory;
//    public String Category;
//    public String SubFilter;
//    public String EntryName;
//    public String ContactNumber;
//    public String ContactEmail;
//    public String EntryAddress;
//    public String Zone;
//    public String PostalCode;
//    public String Website;
//    public String OpeningHours;
//    public int Distance;
//    public String FacebookPage;
//    public double Latitude;
//    public double Longitude;

    public FetchDetails(){}

    public FetchDetails(Integer EntryID, String MasterCategory, String Category, String SubFilter, String EntryName, String ContactNumber, String ContactEmail, String EntryAddress, String Zone, String PostalCode, String Website, String OpeningHours, Integer Distance, String FacebookPage, Double Latitude, Double Longitude){
        put("EntryID",EntryID.toString());
        put("MasterCategory",MasterCategory);
        put("Category",Category);
        put("SubFilter",SubFilter);
        put("EntryName",EntryName);
        put("ContactNumber",ContactNumber);
        put("ContactEmail",ContactEmail);
        put("EntryAddress",EntryAddress);
        put("Zone",Zone);
        put("PostalCode",PostalCode);
        put("Website",Website);
        put("OpeningHours",OpeningHours);
        put("Distance",Distance.toString());
        put("FacebookPage",FacebookPage);
        put("Latitude",Latitude.toString());
        put("Longitude",Longitude.toString());
    }

    public static List<ShowBlock> listDetails(List<Double> latLong, String distance){
        //List<String> list = new ArrayList<String>();
        //List<FetchDetails> fetchDetailslist = new ArrayList<FetchDetails>();
        List<ShowBlock> showblklist = new ArrayList<ShowBlock>();

        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/"+latLong.toArray()[0]+"/"+latLong.toArray()[1]+"/"+distance );
            Log.i("Some:",a.toString() );
            Log.i("e:",host+"/"+latLong.toArray()[0]+"/"+latLong.toArray()[1]+"/"+distance );
            for (int i=0; i<a.length(); i++) {
                JSONObject jo = new JSONObject(a.getString(i));

                Log.i("SS", jo.getString("Latitude") +","+jo.getString("Longitude") +","+ jo.getString("Distance"));

                String name = jo.getString("EntryName");
                String category = jo.getString("Category");
                int id = Integer.parseInt(jo.getString("EntryID"));

                String lati = jo.getString("Latitude");
                String longi = jo.getString("Longitude");
                String dist = jo.getString("Distance");

                String contactnumber = jo.getString("ContactNumber")!= null ? jo.getString("ContactNumber") : "";
                String contactemail = jo.getString("ContactEmail")!= null ? jo.getString("ContactEmail") : "";
                String entryaddress = jo.getString("EntryAddress")!= null ? jo.getString("EntryAddress") : "";
                String postalcode = jo.getString("PostalCode")!= null ? jo.getString("PostalCode") : "";
                String website = jo.getString("Website")!= null ? jo.getString("Website") : "";
                String openinghours = jo.getString("OpeningHours")!= null ? jo.getString("OpeningHours") : "";
                String facebookpage = jo.getString("FacebookPage")!= null ? jo.getString("FacebookPage") : "";


                //ShowBlock sb = new ShowBlock(id, name, category);
                ShowBlock sb = new ShowBlock(id,name,category,lati,longi,contactnumber,contactemail,entryaddress,postalcode,website,openinghours,dist,facebookpage);
                showblklist.add(sb);

            }
        }
        catch (Exception e) {
        }
        //return list;
        //return fetchDetailslist;
        return showblklist;
    }

    public static ShowBlock getDetails(String latitude, String longitude,String distance,String id){
        ShowBlock sb = null;

        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/"+latitude+"/"+longitude+"/"+distance );
            Log.i("Some:",a.toString() );
            for (int i=0; i<a.length(); i++) {
                JSONObject jo = new JSONObject(a.getString(i));
                Log.i("dd:",jo.getString("EntryID"));
                Log.i("ee:",id );
                if(jo.getString("EntryID").equals(id)){
                    String name = jo.getString("EntryName") != null ? jo.getString("EntryName") : "";
                    String category = jo.getString("Category")!= null ? jo.getString("Category") : "";
                    int EntryID = Integer.parseInt(jo.getString("EntryID") != null ? jo.getString("EntryID") : "");

                    double lati = Double.parseDouble(jo.getString("Latitude") != null ? jo.getString("Latitude") : "");
                    double longi = Double.parseDouble(jo.getString("Longitude") != null ? jo.getString("Longitude") : "");
                    int dist = Integer.parseInt(jo.getString("Distance") != null ? jo.getString("Distance") : "");

                    String contactnumber = jo.getString("ContactNumber")!= null ? jo.getString("ContactNumber") : "";
                    String contactemail = jo.getString("ContactEmail")!= null ? jo.getString("ContactEmail") : "";
                    String entryaddress = jo.getString("EntryAddress")!= null ? jo.getString("EntryAddress") : "";
                    String postalcode = jo.getString("PostalCode")!= null ? jo.getString("PostalCode") : "";
                    String website = jo.getString("Website")!= null ? jo.getString("Website") : "";
                    String openinghours = jo.getString("OpeningHours")!= null ? jo.getString("OpeningHours") : "";
                    String facebookpage = jo.getString("FacebookPage")!= null ? jo.getString("FacebookPage") : "";

                    Log.i("SS", jo.getString("Latitude") +","+jo.getString("Longitude") +","+ jo.getString("Distance")
                            +","+ contactnumber+","+ contactemail+","+ entryaddress+","+ postalcode+","+ website
                            +","+openinghours +","+ facebookpage);


                    //sb = new ShowBlock(EntryID,name,category,lati,longi,contactnumber,contactemail,entryaddress,postalcode,website,openinghours,dist,facebookpage);
                    sb = null;
                }


            }
        }
        catch (Exception e) {
        }

        return sb;
    }

}
