package com.jakeesveld.javacontacts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    private static final String CONTACTS_URL = "https://randomuser.me/api/?format=json&inc=name,email,phone,picture&results=1000";

    interface ContactCallback {
        void processContacts(List<Contact> contacts);
    }


    public static void getContacts(final ContactCallback callback) {
        NetworkAdapter.backgroundHttpRequest(CONTACTS_URL, new NetworkAdapter.NetworkCallback() {
            @Override
            public void processResult(String result) {
                ArrayList<Contact> contacts = new ArrayList<>();
                try {
                    JSONObject resultJson = new JSONObject(result);
                    JSONArray  jsonArray  = resultJson.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); ++i) {
                        try {
                            JSONObject json = jsonArray.getJSONObject(i);

                            String title = "", first = "", last = "";
                            String email = "", phone = "";
                            String large = "", medium = "", thumbnail = "";

                            // parsing individual contact

                            try {
                                title = json.getJSONObject("name").getString("title");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                first = json.getJSONObject("name").getString("first");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                last = json.getJSONObject("name").getString("last");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                email = json.getString("email");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                phone = json.getString("phone");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                large = json.getJSONObject("picture").getString("large");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                medium = json.getJSONObject("picture").getString("medium");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                thumbnail = json.getJSONObject("picture").getString("thumbnail");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            contacts.add(new Contact(title, first, last, email, phone, large, medium, thumbnail));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callback.processContacts(contacts);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}