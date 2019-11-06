package codes.umair.whatsdirect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    EditText edt_number;
    CountryCodePicker ccp;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_number = findViewById(R.id.edt_number);
        ccp = findViewById(R.id.ccp);
        btn_submit = findViewById(R.id.btn_submit);

        ccp.registerPhoneNumberTextView(edt_number);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone_number = ccp.getFullNumberWithPlus();
                if (!edt_number.getText().toString().isEmpty()) {
                    openWhatsApp(phone_number);
                }else {
                    Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void openWhatsApp(String toNumber) {
        try {
            String msg = "";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + msg));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

