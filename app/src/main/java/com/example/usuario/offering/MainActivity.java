package com.example.usuario.offering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAcept;
    private CheckBox chkSeeHome, chkSeeElectronict, chkSeeSports, chkImportance;
    public static final String SELECTEC_KEYS = "selectect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkSeeHome = (CheckBox)findViewById(R.id.chkSeeHome);
        chkSeeElectronict = (CheckBox)findViewById(R.id.chkSeeElectronict);
        chkSeeSports = (CheckBox)findViewById(R.id.chkSeeSportsList);
        chkImportance = (CheckBox)findViewById(R.id.chkImportance);
        btnAcept = (Button)findViewById(R.id.btnOk);

        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!chkSeeHome.isChecked() && !chkSeeElectronict.isChecked() && !chkSeeSports.isChecked()){

                    Toast.makeText(MainActivity.this, getString(R.string.only_select), Toast.LENGTH_LONG).show();



                }else {

                    boolean[] selectect = new boolean[4];
                    selectect[0] = chkSeeHome.isChecked();
                    selectect[1] = chkSeeElectronict.isChecked();
                    selectect[2] = chkSeeSports.isChecked();
                    selectect[3] = chkImportance.isChecked();

                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra(SELECTEC_KEYS, selectect);
                    startActivity(intent);

                }
            }
        });
    }
}
