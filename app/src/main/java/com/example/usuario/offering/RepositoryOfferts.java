package com.example.usuario.offering;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by usuario on 14/12/16.
 */

public class RepositoryOfferts extends ArrayList<Offer> {


    private static RepositoryOfferts instance;

    public static RepositoryOfferts getInstance(Context context){

        if(instance == null){

            instance = new RepositoryOfferts(context);
        }

        return instance;
    }

    private RepositoryOfferts(Context context){

        String[] importance = context.getResources().getStringArray(R.array.importance);
        String[] category = context.getResources().getStringArray(R.array.categoryes);

        add(new Offer("Portatil HP", R.mipmap.ic_mobile, category[1], "02/12/2016", importance[0], "Media Mark"));
        add(new Offer("Espejo redondo", R.mipmap.ic_home, category[0], "09/12/2016", importance[1], "Carrefour"));
        add(new Offer("Chandal Adidas", R.mipmap.ic_sports, category[2], "02/12/2016", importance[2], "Milar"));
        add(new Offer("LG G3", R.mipmap.ic_mobile, category[1], "02/12/2016", importance[0], "Media Marck"));
        add(new Offer("Ordenado de sobre mesa", R.mipmap.ic_mobile, category[1], "09/12/2016", importance[1], "Carrefour"));
        add(new Offer("Chandal Adidas", R.mipmap.ic_sports, category[2], "02/12/2016", importance[2], "Carrefour"));
    }

    public boolean addOffer(Offer offer){

        for(int i = 0; i < size(); i++){

            if(offer.getCategory().equalsIgnoreCase(get(i).getCategory()) &&
                    offer.getName().equalsIgnoreCase(get(i).getName())){

                return false;
            }
        }

        add(offer);
        return true;
    }
}
