package com.example.akhiljayan.nearbyshops;

import java.util.HashMap;

/**
 * Created by AkhilJayan on 10-May-17.
 */

public class ShowBlock extends HashMap<String, String>{

    public ShowBlock(int id,String name, String category) {
        put("id", Integer.toString(id));
        put("name", name);
        put("category", category);
    }


    public ShowBlock(int id,String name, String category, String lati, String longi, String contactnumber, String contactemail, String entryaddress, String postalcode,String website, String openinghours, String dist, String facebookpage) {
        put("id", Integer.toString(id));
        put("name", name);
        put("lati", lati);
        put("longi", longi);
        put("contactnumber", contactnumber);
        put("contactemail", contactemail);
        put("category", category);
        put("entryaddress", entryaddress);
        put("postalcode", postalcode);
        put("website", website);
        put("openinghours", openinghours);
        put("dist", dist);
        put("facebookpage", facebookpage);
    }

}


