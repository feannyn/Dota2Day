package com.example.haley.dota2dayproj;

public class Hero {

    private String name;
    private String image;
    private double pro_pick;
    private double pick_1;
    private double pick_2;
    private double pick_3;
    private double pick_4;
    private double pick_5;
    private double pick_6;
    private double pick_7;

    public Hero(String name, String image, double pro_pick, double pick_1, double pick_2, double pick_3, double pick_4, double pick_5, double pick_6, double pick_7) {
        this.name = name;
        this.image = image;
        this.pro_pick = pro_pick;
        this.pick_1 = pick_1;
        this.pick_2 = pick_2;
        this.pick_3 = pick_3;
        this.pick_4 = pick_4;
        this.pick_5 = pick_5;
        this.pick_6 = pick_6;
        this.pick_7 = pick_7;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public double getPro_pick() {
        return pro_pick;
    }

    public double getPick_1() {
        return pick_1;
    }

    public double getPick_2() {
        return pick_2;
    }

    public double getPick_3() {
        return pick_3;
    }

    public double getPick_4() {
        return pick_4;
    }

    public double getPick_5() {
        return pick_5;
    }

    public double getPick_6() {
        return pick_6;
    }

    public double getPick_7() {
        return pick_7;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", pro_pick=" + pro_pick +
                ", pick_1=" + pick_1 +
                ", pick_2=" + pick_2 +
                ", pick_3=" + pick_3 +
                ", pick_4=" + pick_4 +
                ", pick_5=" + pick_5 +
                ", pick_6=" + pick_6 +
                ", pick_7=" + pick_7 +
                '}';
    }
}
