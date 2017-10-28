package dexter.com.isec17.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import dexter.com.isec17.R;

import static android.R.id.message;

public class ContactUsActivity extends AppCompatActivity {
    LinearLayout callUsLayout,emailUsLayout;
    TextView contactNo,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        callUsLayout = (LinearLayout) findViewById(R.id.call_us_layout);
        emailUsLayout = (LinearLayout) findViewById(R.id.email_us_layout);
        final Context context=ContactUsActivity.this;



        callUsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.callus_custom_dialog);
                dialog.show();
                contactNo = (TextView) dialog.findViewById(R.id.call_us_no);
                final String phone = contactNo.getText().toString();
                contactNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+phone));
                        startActivity(intent);
                    }
                });
            }
        });
        emailUsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.emailus_custom_dialog);
                dialog.show();
                email = (TextView) dialog.findViewById(R.id.email_us_link);
                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto",email.getText().toString(), null));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "");
                        intent.putExtra(Intent.EXTRA_TEXT, message);
                        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                    }
                });
            }
        });

    }
}
