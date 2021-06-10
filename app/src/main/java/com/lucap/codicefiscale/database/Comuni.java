package com.lucap.codicefiscale.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comuni implements Parcelable {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="Comune")
    String Comune;

    @ColumnInfo(name="Provincia")
    String Provincia;

    @ColumnInfo(name="CodiceCatastale")
    String CodiceCatastale;

    public String getProv() {
        return Provincia;
    }

    public String getComune() {
        return Comune;
    }

    public String getCodiceCatastale() {
        return CodiceCatastale;
    }




    public static final Creator<Comuni> CREATOR = new Creator<Comuni>() {
        @Override
        public Comuni createFromParcel(Parcel in) {
            return new Comuni(in);
        }

        @Override
        public Comuni[] newArray(int size) {
            return new Comuni[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Provincia);
        dest.writeString(Comune);
        dest.writeString(CodiceCatastale);
    }


    public Comuni(){

    }

    private Comuni(Parcel in) {
        Comune = in.readString();
        Provincia = in.readString();
        CodiceCatastale = in.readString();
    }
}