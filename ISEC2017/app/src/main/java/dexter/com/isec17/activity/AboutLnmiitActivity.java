package dexter.com.isec17.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import dexter.com.isec17.R;

public class AboutLnmiitActivity extends AppCompatActivity {
    LinearLayout headerLnm;
    TextView aboutLnm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_lnmiit);
        headerLnm = (LinearLayout) findViewById(R.id.aboutLnm_header);
        headerLnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent abtlnmiit = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lnmiit_weblink)));
                    startActivity(abtlnmiit);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText( AboutLnmiitActivity.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
