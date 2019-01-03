package com.ryan.newsapp.adapter;



public class LegendPics {


    public byte[] ability_q_pic = null;
    public byte[] ability_w_pic = null;
    public byte[] ability_e_pic = null;
    public byte[] ability_r_pic = null;
    public byte[] ability_passive_pic = null;
    public byte[] head_pic = null;
    public byte[] best_rune = null;
    public byte[] ability_chart = null;
    public byte[] time_win_rate = null;

    public LegendPics(){

    }

    public LegendPics(byte[] ability_q_pic,byte[] ability_w_pic,byte[] ability_e_pic,
                      byte[] ability_r_pic,byte[] ability_passive_pic,byte[] head_pic,
                      byte[] best_rune,byte[] ability_chart,byte[] time_win_rate){
        this.ability_q_pic=ability_q_pic;
        this.ability_w_pic=ability_w_pic;
        this.ability_e_pic=ability_e_pic;
        this.ability_r_pic=ability_r_pic;
        this.ability_passive_pic=ability_passive_pic;
        this.head_pic=head_pic;
        this.best_rune=best_rune;
        this.ability_chart=ability_chart;
        this.time_win_rate=time_win_rate;


    }

}
