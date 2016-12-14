package com.example.usuario.offering;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by usuario on 14/12/16.
 */

public class Offer implements Parcelable {

    private String name;
    private int icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public Offer(String name, int icon, String category, String date, String importance, String tienda) {

        this.name = name;
        this.icon = icon;
        this.category = category;
        this.date = date;
        this.importance = importance;
        this.tienda = tienda;
    }

    private String category;
    private String date;
    private String importance;

    public static final Comparator<Offer> ORDER_BY_ASC = new Comparator<Offer>() {
        @Override
        public int compare(Offer offer, Offer t1) {
            return offer.getName().compareToIgnoreCase(t1.getName());
        }
    };

    public static final Comparator<Offer> ORDER_BY_DES = new Comparator<Offer>() {
        @Override
        public int compare(Offer offer, Offer t1) {
            return t1.getName().compareToIgnoreCase(offer.getName());
        }
    };

    public static final Comparator<Offer> GOUB_BY_CATEGORY = new Comparator<Offer>() {
        @Override
        public int compare(Offer offer, Offer t1) {
            return offer.getCategory().compareToIgnoreCase(t1.getCategory());
        }
    };

    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if(obj != null){

            if(obj instanceof Offer){

                result = ((Offer) obj).getName().equalsIgnoreCase(this.getName()) &&
                        ((Offer) obj).getCategory().equalsIgnoreCase(this.category);
            }
        }

        return result;
    }

    private String tienda;

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.icon);
        dest.writeString(this.category);
        dest.writeString(this.date);
        dest.writeString(this.importance);
        dest.writeString(this.tienda);
    }

    protected Offer(Parcel in) {
        this.name = in.readString();
        this.icon = in.readInt();
        this.category = in.readString();
        this.date = in.readString();
        this.importance = in.readString();
        this.tienda = in.readString();
    }

    public static final Creator<Offer> CREATOR = new Creator<Offer>() {
        @Override
        public Offer createFromParcel(Parcel source) {
            return new Offer(source);
        }

        @Override
        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };
}
