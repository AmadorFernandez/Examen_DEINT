package com.example.usuario.offering;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Clase para la genstion de los elementos que se muestran en la lista
 *
 */

public class ListOffertAdapter extends ArrayAdapter<Offer> {

    private Context context;
    private ArrayList<Offer> localList;
    private boolean seeImportance;
    public final static int ORDER_BY_ASC = 1;
    public final static int ORDER_BY_DESC = 2;
    public final static int GROUP_BY_CAT = 3;



    /**
     * Constructor que recoge los datos que ha de mostrar la lista
     * y si cambia o no los colores
     *
     * */
    public ListOffertAdapter(Context context, ArrayList<Offer> datas, boolean seeImportance) {
        super(context, R.layout.item_list, new ArrayList<Offer>(datas));
        this.context = context;
        this.localList = datas;
        this.seeImportance = seeImportance;
    }


    /**
     *
     * Metod que restaura los item de la lista
     *
     *
     * */
    public void restoreState(ArrayList<Offer> restore){

        clear();
        addAll(restore);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = convertView;
        OffertHolder holder;

        if(rootView == null){

            holder = new OffertHolder();
            rootView = LayoutInflater.from(this.context).inflate(R.layout.item_list, null);
            holder.imageView = (ImageView)rootView.findViewById(R.id.imvOfertList);
            holder.txvNameOffer = (TextView)rootView.findViewById(R.id.txvNameOfer);
            holder.txvDate = (TextView)rootView.findViewById(R.id.txvItemData);
            holder.txvTienda = (TextView)rootView.findViewById(R.id.txvTienda);

            rootView.setTag(holder);

        }else{

            holder = (OffertHolder)rootView.getTag();
        }

        holder.imageView.setImageResource(getItem(position).getIcon());
        holder.txvNameOffer.setText(getItem(position).getName());
        holder.txvDate.setText(getItem(position).getDate());
        holder.txvTienda.setText(getItem(position).getTienda());

        String[] importance = context.getResources().getStringArray(R.array.importance);
        if(seeImportance){

            if(getItem(position).getImportance().equalsIgnoreCase(importance[0])){

                rootView.setBackgroundColor(Color.RED);

            }else if(getItem(position).getImportance().equalsIgnoreCase(importance[1])){

                rootView.setBackgroundColor(Color.GRAY);

            }else if(getItem(position).getImportance().equalsIgnoreCase(importance[2])){

                rootView.setBackgroundColor(Color.WHITE);
            }
        }


        return rootView;
    }



    /**
     * Permite filtrar que elementos se muestran en la lista
     * */
    public void seeOffert(ArrayList<String> offertSee){

        ArrayList<Offer> tmp = new ArrayList<Offer>();

        for(int i = 0; i < getCount(); i++){

            for(int j = 0; j < offertSee.size(); j++){

                if(getItem(i).getCategory().equals(offertSee.get(j))){

                    tmp.add(getItem(i));
                }
            }
        }
        clear();
        addAll(tmp);
    }

    /**
     * Ordena segun se le indique
     * */
    public void orderBy(int typeOrder){

        switch (typeOrder){

            case ORDER_BY_ASC:
                sort(Offer.ORDER_BY_ASC);
                break;
            case ORDER_BY_DESC:
                sort(Offer.ORDER_BY_DES);
                break;
            case GROUP_BY_CAT:
                sort(Offer.GOUB_BY_CATEGORY);
                break;
        }
    }



    class OffertHolder{

        ImageView imageView;
        TextView txvNameOffer;
        TextView txvDate;
        TextView txvTienda;

    }
}
