package com.lambdaschool.javacontacts;

import android.os.Parcel;
import android.os.Parcelable;

//S09M03-5b add parcelable implementation to pass object
public class Contact implements Parcelable {

    private String title, first, last;
    private String email, phone;
    private String imageLarge, imageMedium, imageThumbnail;

//    S09M03-5 add location fields to contact
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

    protected Contact(Parcel in) {
        title = in.readString();
        first = in.readString();
        last = in.readString();
        email = in.readString();
        phone = in.readString();
        imageLarge = in.readString();
        imageMedium = in.readString();
        imageThumbnail = in.readString();
        street = in.readString();
        city = in.readString();
        state = in.readString();
        postcode = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

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

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(first);
        dest.writeString(last);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(imageLarge);
        dest.writeString(imageMedium);
        dest.writeString(imageThumbnail);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(postcode);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
