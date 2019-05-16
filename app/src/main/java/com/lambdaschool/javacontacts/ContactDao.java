package com.lambdaschool.javacontacts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContactDao {

//    S09M03-7 update path to return location data
    private static final String CONTACTS_URL = "https://randomuser.me/api/?format=json&inc=name,email,phone,picture,location&results=1000";

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

//                            S09M03-6 get location fields from json
                            // location
                            String street = "", city = "", state = "", postcode = "";
                            double latitude = 0.0, longitude = 0.0;

                            try {
                                JSONObject location = json.getJSONObject("location");
                                try {
                                    street = location.getString("street");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    city = location.getString("city");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    state = location.getString("state");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    postcode = location.getString("postcode");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    latitude = location.getJSONObject("coordinates").getDouble("latitude");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    longitude = location.getJSONObject("coordinates").getDouble("longitude");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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

                            contacts.add(new Contact(title, first, last, email, phone, large, medium, thumbnail, street, city, state, postcode, latitude, longitude));
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
