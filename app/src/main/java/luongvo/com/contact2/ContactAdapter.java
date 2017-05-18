package luongvo.com.contact2;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by luongvo on 18/05/2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resourceID;
    private ArrayList<Contact> contacts;

    public ContactAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Contact> data) {
        super(context, resource, data);
        this.context = context;
        this.resourceID = resource;
        this.contacts = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            // convertView = LayoutInflater.from(this.context).inflate(this.resourceID, parent, false);
            LayoutInflater layoutInflater = ((Activity) this.context).getLayoutInflater();
            convertView = layoutInflater.inflate(resourceID, parent, false);
            return convertView;
        }
        Contact contact = contacts.get(position);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.itemContact);
        imageView.setImageBitmap(contact.getAvatar());
        /*TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(getItem(position).name);*/
        TextView tvName = (TextView) convertView.findViewById(R.id.itemName);
        tvName.setText(contact.getName());
        TextView tvPhone = (TextView) convertView.findViewById(R.id.itemPhoneNumber);
        tvPhone.setText(contact.getPhoneNumber());
        return convertView;
    }
}
