package com.example.haley.dota2dayproj;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Hero implements Parcelable{

    /*
    * converted custom class into parcelable to pass around to and from fragments
    * gives the ability to put into bundles
    *
    * bundle is a global hashmaps with our inputs (whatever it maybe in this case it's a parcel)
    *
    *
    * */




    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Object createFromParcel(Parcel source) {
            return new Hero(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[size];
        }
    };




    private int id;
    private String name;
    private int pick_1;
    private int pick_2;
    private int pick_3;
    private int pick_4;
    private int pick_5;
    private int pick_6;
    private int pick_7;
    private int pick_all;
    private double pick_difference;

    public Hero(int id, String name, int pick_1, int pick_2, int pick_3, int pick_4, int pick_5, int pick_6, int pick_7) {
        this.id = id;
        this.name = name;
        this.pick_1 = pick_1;
        this.pick_2 = pick_2;
        this.pick_3 = pick_3;
        this.pick_4 = pick_4;
        this.pick_5 = pick_5;
        this.pick_6 = pick_6;
        this.pick_7 = pick_7;
        this.pick_all = pick_1 + pick_2 + pick_3 + pick_4 + pick_5 + pick_6 + pick_7;
        this.pick_difference = 0;
    }

    public Hero(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        this.pick_1 = in.readInt();
        this.pick_2 = in.readInt();
        this.pick_3 = in.readInt();
        this.pick_4 = in.readInt();
        this.pick_5 = in.readInt();
        this.pick_6 = in.readInt();
        this.pick_7 = in.readInt();
        this.pick_all = pick_1 + pick_2 + pick_3 + pick_4 + pick_5 + pick_6 + pick_7;
        this.pick_difference = 0;
    }



    //Populates the pick_difference variables of each hero
    //this pick_difference is the RAW PERCENTAGE DIFFERENCE between the lowest and highest brackets.
    //IE, a pick rate of 13% in bracket 1 and 19% in bracket 7 would give us a 6% difference.
    //A positive difference means that the pick rate INCREASES from 1 to 7
    //A negative difference means that the pick rate DECREASes from 1 to 7
    public static void GenerateDifference(List<Hero> list){
        int pick_1_total = 0;
        int pick_7_total = 0;

        //add up total picks for ALL heroes
        for (Hero h : list){
            pick_1_total += h.getPick_1();
            pick_7_total += h.getPick_7();
        }

        //total games = total picks / 10
        //this is because games can only have one copy of each hero
        double games_1_total = (double) pick_1_total / 10;
        double games_7_total = (double) pick_7_total / 10;


            //now we calculate the differences between the pick rates of heroes in the lowest to highest brackets.
        for (Hero h : list) {
            //p1p - pick 1 percentage
            //p7p - pick 7 percentage
            //these numbers are generated from (times picked) / (total games)
            double p1p = h.getPick_1() / games_1_total;
            double p7p = h.getPick_7() / games_7_total;

            h.pick_difference = -(p1p - p7p) * 100;
        }

        //System.out.println("games_1_total = " + games_1_total);
        //System.out.println("games_7_total = " + games_7_total);
        //System.out.println("this data was generated from list of size " + list.size());
        System.out.println(list);

    }

    public int getID(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPick_1() {
        return pick_1;
    }

    public int getPick_2() {
        return pick_2;
    }

    public int getPick_3() {
        return pick_3;
    }

    public int getPick_4() {
        return pick_4;
    }

    public int getPick_5() {
        return pick_5;
    }

    public int getPick_6() {
        return pick_6;
    }

    public int getPick_7() {
        return pick_7;
    }

    public int getPick_all() { return pick_all; }

    public double getPick_difference() {return pick_difference; }

    @Override
    public String toString() {
        return "\nHero{ " +
                "name = " + name +
                "," + '\n' + "ID = " + id +
                "," + '\n' + "pick_1=" + pick_1 +
                "," + '\n' + "pick_2=" + pick_2 +
                "," + '\n' + "pick_3=" + pick_3 +
                "," + '\n' + "pick_4=" + pick_4 +
                "," + '\n' + "pick_5=" + pick_5 +
                "," + '\n' + "pick_6=" + pick_6 +
                "," + '\n' + "pick_7=" + pick_7 +
                "," + '\n' + "pick_all=" + pick_all +
                "," + '\n' + "pick_difference=" + pick_difference +
                " }" + '\n';
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(pick_1);
        dest.writeInt(pick_2);
        dest.writeInt(pick_3);
        dest.writeInt(pick_4);
        dest.writeInt(pick_5);
        dest.writeInt(pick_6);
        dest.writeInt(pick_7);

    }
}
