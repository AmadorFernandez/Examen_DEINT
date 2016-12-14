package com.example.usuario.offering;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ListOffertAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private RepositoryOfferts repositoryOfferts;
    public static final String RESTORE_ADAPTER_KEY = "adapter";
    private boolean[] seeItems;


    /**
     *
     * Metod que permite salvar el estado de la lista al girar el dispositivo
     *
     *
     * */
    @Override
    public void onSaveInstanceState(Bundle outState) {

        ArrayList<Offer> offers = new ArrayList<Offer>();

        for(int i = 0; i < adapter.getCount(); i++){

            offers.add(adapter.getItem(i));
        }

        outState.putParcelableArrayList(RESTORE_ADAPTER_KEY, offers);

        super.onSaveInstanceState(outState);
    }

    /**
     * Metodo que da memoria a los elementos y asigna el adapter
     * además de controlas que que elementos mostrara el adapter
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ArrayList<String> seeOferts = new ArrayList<String>();
        String[] ofertTypes = getResources().getStringArray(R.array.categoryes);
        seeItems = getIntent().getBooleanArrayExtra(MainActivity.SELECTEC_KEYS);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatList);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(ListActivity.this, CreateOffertActivity.class), 1);
            }
        });

        repositoryOfferts = RepositoryOfferts.getInstance(this);

        if(savedInstanceState == null){

            adapter = new ListOffertAdapter(this, repositoryOfferts, seeItems[3]);
            adapter.orderBy(ListOffertAdapter.ORDER_BY_ASC);

        }else{

            adapter = new ListOffertAdapter(this, repositoryOfferts, seeItems[3]);
            adapter.restoreState(savedInstanceState.<Offer>getParcelableArrayList(RESTORE_ADAPTER_KEY));
        }

        listView = (ListView)findViewById(R.id.listOffert);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);


        if(seeItems[0]){

            seeOferts.add(ofertTypes[0]);
        }

        if(seeItems[1]){

            seeOferts.add(ofertTypes[1]);
        }

        if(seeItems[2]){

            seeOferts.add(ofertTypes[2]);
        }

        adapter.seeOffert(seeOferts);


    }

    /**
     * Recoge la oferta creada y la añade
     *
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<String> seeOferts = new ArrayList<String>();
        String[] ofertTypes = getResources().getStringArray(R.array.categoryes);
        seeItems = getIntent().getBooleanArrayExtra(MainActivity.SELECTEC_KEYS);

        if(seeItems[0]){

            seeOferts.add(ofertTypes[0]);
        }

        if(seeItems[1]){

            seeOferts.add(ofertTypes[1]);
        }

        if(seeItems[2]){

            seeOferts.add(ofertTypes[2]);
        }

        adapter = new ListOffertAdapter(ListActivity.this, RepositoryOfferts.getInstance(ListActivity.this), seeItems[3]);
        listView.setAdapter(adapter);
        adapter.seeOffert(seeOferts);


    }


    /**
     *
     * Lanza el la accion al lanzar el menu contextual
     * */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.alert_title));
        builder.setMessage(getString(R.string.app_info_text));

        builder.setPositiveButton(getString(R.string.acept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        }).show();

        return super.onContextItemSelected(item);
    }


    /**
     * Infla el menu contextual
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    /**
     *
     * Infla el menu de la aplicacion
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_order_list, menu);

        return super.onCreateOptionsMenu(menu);
    }


    /**
     * Ordena al adapter el tipo de ordenamiento que debe realizar
     *
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_order_asc:
                adapter.orderBy(ListOffertAdapter.ORDER_BY_ASC);
                break;
            case R.id.action_order_des:
                adapter.orderBy(ListOffertAdapter.ORDER_BY_DESC);
                break;
            case R.id.action_type:
                adapter.orderBy(ListOffertAdapter.GROUP_BY_CAT);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
