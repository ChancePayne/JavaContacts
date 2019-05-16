package com.lambdaschool.javacontacts;

public class Contact {

    private String title, first, last;
    private String email, phone;
    private String imageLarge, imageMedium, imageThumbnail;

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
}
