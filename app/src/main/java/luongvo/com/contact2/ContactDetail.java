package luongvo.com.contact2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetail extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 0;
    ImageView avatar;
    TextView name, phoneNumber;

    Contact contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        initComponents();
        getData();
        updateLayout();
    }

    private void updateLayout() {
        avatar.setImageBitmap(contact.getAvatar());
        name.setText(contact.getName());
        phoneNumber.setText(contact.getPhoneNumber());

    }

    private void getData() {
        Intent intent = getIntent();
        byte[] byteArr = intent.getByteArrayExtra("avatar");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
        contact = new Contact(intent.getStringExtra("name"), intent.getStringExtra("phoneNumber"), bmp);

    }

    private void initComponents() {
        avatar = (ImageView) findViewById(R.id.contactAvatar);
        name = (TextView) findViewById(R.id.contactName);
        phoneNumber = (TextView) findViewById(R.id.contactPhoneNumber);
    }

    public void onClickBtnCall(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + contact.getPhoneNumber()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    public void onClickBtnSms(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + contact.getPhoneNumber()));
        startActivity(intent);
    }

    public void onClickChangeImage(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                contact.setAvatar(bmp);
                avatar.setImageBitmap(bmp);
            }
        }
    }
}
