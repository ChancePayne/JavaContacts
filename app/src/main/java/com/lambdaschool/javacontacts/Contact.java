package com.lambdaschool.javacontacts;

public class Contact {

    private String title, first, last;
    private String email, phone;
    private String imageLarge, imageMedium, imageThumbnail;

    private String street, city, state, postcode;
    private double latitude, longitude;

    public Contact(String title, String first, String last, String email, String phone, String imageLarge, String imageMedium, String imageThumbnail) {
        this.title = title;
        this.first = first;
        this.last = last;
        this.email = email;
        this.phone = phone;
        this.imageLarge = imageLarge;
        this.imageMedium = imageMedium;
        this.imageThumbnail = imageThumbnail;
    }

    public Contact(String title, String first, String last, String email, String phone, String imageLarge, String imageMedium, String imageThumbnail, String street, String city, String state, String postcode, double latitude, double longitude) {
        this.title = title;
        this.first = first;
        this.last = last;
        this.email = email;
        this.phone = phone;
        this.imageLarge = imageLarge;
        this.imageMedium = imageMedium;
        this.imageThumbnail = imageThumbnail;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return title + " " + first + " " + last;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public String getImageMedium() {
        return imageMedium;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
