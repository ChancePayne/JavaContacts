package com.lambdaschool.javacontacts;

public class Contact {
    //https://randomuser.me/api/?format=json&inc=name,email,phone,picture&results=1000

/* "name": {
    "title": "mr",
    "first": "shane",
    "last": "fitzpatrick"
},
"email": "shane.fitzpatrick@example.com",
"phone": "061-466-1369",
"picture": {
    "large": "https://randomuser.me/api/portraits/men/20.jpg",
    "medium": "https://randomuser.me/api/portraits/med/men/20.jpg",
    "thumbnail": "https://randomuser.me/api/portraits/thumb/men/20.jpg"*/

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
