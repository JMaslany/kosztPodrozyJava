package com.example.kosztpodrozy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText dist, cons, passNo;
    RadioGroup group;
    Button btn;
    TextView res1, res2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dist = findViewById(R.id.distance);
        cons = findViewById(R.id.consumption);
        passNo = findViewById(R.id.passengers);

        group = findViewById(R.id.radGr);
        btn = findViewById(R.id.btn);
        res1 = findViewById(R.id.res1);
        res2 = findViewById(R.id.res2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radID = group.getCheckedRadioButtonId();
                RadioButton rad = findViewById(radID);
                int index = rad.getText().toString().indexOf("(");
                float fuelPrice = Float.parseFloat((rad.getText().toString().substring(index+1,index+4)));

                float totalCost = ((Float.parseFloat(dist.getText().toString()) / 100) * Float.parseFloat(cons.getText().toString())) * fuelPrice;
                float partialCost = totalCost / Integer.parseInt(passNo.getText().toString());

                res1.setText("Całkowity koszt:"+ String.valueOf(totalCost) +"zł");
                res2.setText("Na osobę: " + String.valueOf(partialCost) + "zł");
            }
        });
    }
}