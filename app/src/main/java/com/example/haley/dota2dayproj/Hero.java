package com.example.haley.dota2dayproj;

public class Hero {
    private int id;
    private String name;
    private int pick_1;
    private int pick_2;
    private int pick_3;
    private int pick_4;
    private int pick_5;
    private int pick_6;
    private int pick_7;

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

    @Override
    public String toString() {
        return "\nHero{ " +
                "name=" + name +
                "," + '\n' + "pick_1=" + pick_1 +
                "," + '\n' + "pick_2=" + pick_2 +
                "," + '\n' + "pick_3=" + pick_3 +
                "," + '\n' + "pick_4=" + pick_4 +
                "," + '\n' + "pick_5=" + pick_5 +
                "," + '\n' + "pick_6=" + pick_6 +
                "," + '\n' + "pick_7=" + pick_7 +
                " }" + '\n';
    }
}
