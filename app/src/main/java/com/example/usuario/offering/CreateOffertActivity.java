package com.example.usuario.offering;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * Clase para el manejo del formulario que añade las ofertas
 *
 * */
public class CreateOffertActivity extends AppCompatActivity {

    private EditText edtName, edtTienda, edtFecha;
    private Spinner spTipo, spImportancia;
    private Button btnAdd;
    private int categorySelect;
    private String importance;
    private String categoryName;
    public static final String OFFER_KEY = "offer";




    /**
     *
     * Metodo que da memoria a los elementos de la interfaz, captura los eventos
     * y recoge las clases anonimas para el manejo de los eventos.
     *
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offert);

        edtName = (EditText)findViewById(R.id.edtName);
        edtTienda = (EditText)findViewById(R.id.edtTienda);
        edtFecha = (EditText)findViewById(R.id.edtFecha);
        categorySelect = 0;

        spTipo = (Spinner)findViewById(R.id.spinerTipo);
        spImportancia = (Spinner)findViewById(R.id.spinerImportancia);

        ArrayAdapter<String> adapterTipos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.categoryes));
        spTipo.setAdapter(adapterTipos);

        ArrayAdapter<String> adapterImportancia = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.importance));
        spImportancia.setAdapter(adapterImportancia);

        spImportancia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String[] importances = getResources().getStringArray(R.array.importance);

                importance = importances[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String[] tipos = getResources().getStringArray(R.array.categoryes);

                categoryName = tipos[i];
                categorySelect = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RepositoryOfferts repositoryOfferts = RepositoryOfferts.getInstance(CreateOffertActivity.this);
                int iconOffert = 0;

                switch (categorySelect){

                    case 0:
                        iconOffert = R.mipmap.ic_home;
                        break;
                    case 1:
                        iconOffert = R.mipmap.ic_mobile;
                        break;
                    case 2:
                        iconOffert = R.mipmap.ic_sports;
                        break;
                }

                Offer offer = new Offer(edtName.getText().toString(),iconOffert, categoryName, edtFecha.getText().toString(),
                        importance, edtTienda.getText().toString());

                if(!repositoryOfferts.addOffer(offer)){

                    showMessageNoAdd();

                }else {

                    Intent i = new Intent();
                    i.putExtra(OFFER_KEY, offer);
                    setResult(RESULT_OK, i);
                    finish();
                }

            }
        });


    }


    /**
     * Metodo que muestrea un menssaje cuando no se puede añadir una oferta
     * */
    private void showMessageNoAdd() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alert_title));
        builder.setMessage(getString(R.string.no_action));
        builder.setPositiveButton(getString(R.string.acept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }

        }).show();

    }
}
