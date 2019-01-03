package com.ryan.newsapp.adapter;

public class LegendInfo {

    public String legend_name = null;
    public String legend_true_name = null;
    public String ability_q = null;
    public String ability_q_more = null;
    public String ability_w = null;
    public String ability_w_more = null;
    public String ability_e  = null;
    public String ability_e_more  = null;
    public String ability_r = null;
    public String ability_r_more = null;
    public String ability_talent = null;
    public String ability_talent_more = null;
    public String legend_story = null;

    public LegendInfo(){

    }

    public LegendInfo(String legend_name,String legend_true_name,String
                      ability_q,String ability_q_more,String ability_w,String ability_w_more,
                      String ability_e,String ability_e_more,String ability_r,String ability_r_more,
                      String ability_talent,String ability_talent_more,String legend_story){
        this.legend_name = legend_name;
        this.legend_true_name=legend_true_name;
        this.ability_q=ability_q;
        this.ability_q_more=ability_q_more;
        this.ability_w=ability_w;
        this.ability_w_more=ability_w_more;
        this.ability_e=ability_e;
        this.ability_e_more=ability_e_more;
        this.ability_r=ability_r;
        this.ability_r_more=ability_r_more;
        this.ability_talent=ability_talent;
        this.ability_talent_more=ability_talent_more;
        this.legend_story=legend_story;

    }
}
