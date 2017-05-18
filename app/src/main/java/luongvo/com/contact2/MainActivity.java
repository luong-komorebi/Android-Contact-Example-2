package luongvo.com.contact2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Contact> contacts;
    ContactAdapter contactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        loadData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact item = contacts.get(position);
                Intent intent = new Intent(MainActivity.this, ContactDetail.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("phoneNumber", item.getPhoneNumber());

                //convert bitmap to byte[] in order to send through intent
                Bitmap bmp = item.getAvatar();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("avatar", byteArray);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        contacts = new ArrayList<>();
        contacts.add(new Contact("Vo Tran Thanh Luong", "01203430075", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        contacts.add(new Contact("Nguyen Quoc Bao", "01203430075", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        contacts.add(new Contact("Luong Bang Quang", "01203430075", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        contacts.add(new Contact("Nguyen Le Duy", "01203430075", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        contacts.add(new Contact("Mai Thi Luu", "01203430075", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));

        contactAdapter = new ContactAdapter(this, R.layout.item_contact, contacts);
        listView.setAdapter(contactAdapter);
    }

    private void initComponent() {
        listView = (ListView)findViewById(R.id.listView);
    }
}
