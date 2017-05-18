package luongvo.com.contact2;

import android.graphics.Bitmap;

/**
 * Created by luongvo on 18/05/2017.
 */

public class Contact {
    private String name;
    private String phoneNumber;
    private Bitmap avatar;

    public Contact(String name, String phoneNumber, Bitmap avatar) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }
}
