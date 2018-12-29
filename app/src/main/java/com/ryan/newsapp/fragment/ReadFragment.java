package com.ryan.newsapp.fragment;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ryan.newsapp.R;
import com.ryan.newsapp.adapter.GridViewItem;
import com.ryan.newsapp.adapter.LegendAdapter;
import com.ryan.newsapp.model.DbHelper;
import com.ryan.newsapp.model.DbManager;

import org.w3c.dom.Text;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class ReadFragment  extends Fragment {

    private int sign ;
    private DbManager mgr;
    private DbHelper dbHelper;
    private ImageView imageView ;
    private GridView gridView;
    private Spinner spinner_position ;
    private Spinner spinner_damage;
    private Spinner spinner_point ;
    private Spinner spinner_gold ;
    private String string_position="位置";
    private String string_damage="伤害";
    private String int_point="点券";
    private String int_gold="金币";
    private List<HashMap<String,GridViewItem>> hashMapList ;
    private Bitmap icon ;
    private String [] iconName = {"xxxxxx"};
    private SimpleAdapter simpleAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        spinner_position = getView().findViewById(R.id.legend_position);
        spinner_damage = getView().findViewById(R.id.legend_damage_type);
        spinner_point = getView().findViewById(R.id.legend_point);
        spinner_gold = getView().findViewById(R.id.legend_gold);
    }




    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);


        //根据下拉表内容改变icon和iconName的内容
        //   init_array()

        gridView = getView().findViewById(R.id.legend_grid);
        hashMapList = new ArrayList<HashMap<String, GridViewItem>>();
        //datalist = new ArrayList<Map<String,Object>>();
        final LegendAdapter legendAdapter = new LegendAdapter(getContext(),hashMapList);
        //simpleAdapter = new SimpleAdapter(getActivity(),datalist,R.layout.grid_item,new String[] {"image","text"},new int[] {R.id.grid_item_image,R.id.grid_item_text});
        gridView.setAdapter(legendAdapter);
        imageView  = getView().findViewById(R.id.legend_img);
        mgr = new DbManager(getActivity());
        init_data();


        //设置下拉表监听获取文本；
        spinner_position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                string_position=(String) spinner_position.getSelectedItem();
                hashMapList.clear();
                legendAdapter.notifyDataSetChanged();
                data_search();


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_damage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                string_damage=(String) spinner_damage.getSelectedItem();
                hashMapList.clear();
                legendAdapter.notifyDataSetChanged();
                data_search();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_point.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int_point = (String)spinner_point.getSelectedItem();
                hashMapList.clear();
                legendAdapter.notifyDataSetChanged();
                data_search();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_gold.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int_gold=(String) spinner_gold.getSelectedItem();
                hashMapList.clear();
                legendAdapter.notifyDataSetChanged();
                data_search();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        //readImage();


    }



    private void init_data(){
        /*for(int i = 0;i<icon.length;i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);

            datalist.add(map);
        }*/

        if(ifNull()==0){
            legend_img_init();
        }




    }

    private void readImage(){
        byte[] imgData = mgr.readImage();
        if(imgData!=null){
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imgData,0,imgData.length);
            imageView.setImageBitmap(imageBitmap);
        }
        else{
            imageView.setBackgroundResource(R.drawable.legend_vyen);
        }
    }

    private int ifNull(){
        dbHelper = new DbHelper(getContext());
        SQLiteDatabase db  = dbHelper.getWritableDatabase();
        Cursor cur = db.query("legend_table",null,null,null,null,null,null);
        sign = cur.getCount();
        cur.close();
        db.close();
        return sign;


    }

    private void legend_img_init(){
        mgr.saveImage(R.drawable.legend_img_fatiao,"发条魔灵","mid","ap",3000,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_moganna,"堕落天使","sup","ap",2000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_kamier,"青钢影","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_bobi,"圣锤之毅","top","ad",1000,450,getContext());
        mgr.saveImage(R.drawable.legend_img_daomei,"刀锋意志","mid","ad",4000,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_weilusi,"惩戒之箭","adc","ad",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_xiweier,"战争女神","adc","ad",1000,450,getContext());
        mgr.saveImage(R.drawable.legend_img_longgui,"披甲龙龟","jug","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_saien,"亡灵战神","top","ad",2000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_kasading,"虚空行者","mid","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_zhaoxin,"德邦总管","jug","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_namei,"唤潮鲛姬","sup","ap",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_fengnv,"风暴之怒","sup","ap",2000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_timo,"迅捷斥候","top","ap",3500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_aixi,"寒冰射手","adc","ad",1000,450,getContext());
        mgr.saveImage(R.drawable.legend_img_gailun,"德玛西亚之力","top","ad",1000,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_ruizi,"符文法师","mid","ap",1000,450,getContext());
        mgr.saveImage(R.drawable.legend_img_paike,"血港鬼影","sup","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_kasa,"虚空之女","adc","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_zuoyi,"暮光星灵","mid","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_aoen,"山隐之焰","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_kaiyin,"影流之镰","jug","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_luo,"幻翎","sup","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_xia,"逆羽","adc","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_aiweng,"翠神","jug","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_kelie,"暴怒骑士","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_yanque,"岩雀","mid","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_jin,"戏命师","adc","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_eluoyi,"海兽祭司","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_qianjue,"永猎双子","jug","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_tamu,"河流之主","sup","ad",4500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_aike,"时间刺客","mid","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_bade,"星界游神","sup","ap",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_leikesai,"虚空遁地兽","jug","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_kalisita,"复仇之矛","adc","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_shahuang,"沙漠皇帝","mid","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_naer,"迷失之牙","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_bulong,"弗雷尔卓德之心","sup","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_dayan,"虚空之眼","mid","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_yasuo,"疾风剑豪","mid","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_jinkesi,"暴走萝莉","adc","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_luxian,"圣枪游侠","adc","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_jianmo,"暗裔剑魔","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_lisangzhuo,"冰霜女巫","mid","ap",3000,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_zhake,"生化魔人","jug","ap",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_kuiyin,"德玛西亚之翼","adc","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_chuishi,"魂锁典狱长","sup","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_wei,"皮城执法官","jug","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_jie,"影流之主","mid","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_zhizhu,"蜘蛛女皇","jug","ap",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_tanglang,"虚空掠夺者","jug","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_xindela,"暗黑元首","mid","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_shizigou,"傲之追猎者","jug","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_jiaoyue,"皎月女神","mid","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_jiela,"荆棘之兴","sup","ap",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_jiesi,"未来守护者","top","ad",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_delaiwen,"荣耀行刑官","adc","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_nuoshou,"诺克萨斯之手","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_renma,"战争之影","jug","ad",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_lulu,"仙灵女巫","mid","ap",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_jianji,"无双剑姬","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_taitan,"深海泰坦","top","ap",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_zhumei,"北地之怒","jug","ap",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_zhadanren,"爆破鬼才","mid","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_weiketuo,"机械先驱","mid","ap",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_ali,"九尾妖狐","mid","ap",3500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_gouxiong,"雷霆咆哮","top","ad",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_zelasi,"远古巫灵","mid","ap",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_xiaoyuren,"潮汐海灵","mid","ap",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_longnv,"龙血武姬","jug","ad",4500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_nanqiang,"法外狂徒","jug","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_ruiwen,"放逐之刃","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_nandao,"刀锋之影","mid","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_xiezi,"水晶先锋","jug","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_rinv,"曙光女神","sup","ad",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_houzi,"齐天大圣","top","ad",4500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_yuelike,"牧魂人","top","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_weien,"暗夜猎手","adc","ad",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_lanbo,"机械公敌","top","ap",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_xiazi,"盲僧","jug","ad",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_huonan,"复仇焰魂","mid","ap",2000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_mengyan,"永恒梦魇","jug","ad",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_huangzi,"德玛西亚皇子","jug","ad",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_eyu,"荒漠屠夫","top","ad",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_dashu,"扭曲树精","top","ap",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_nvjing,"皮城女警","adc","ad",3000,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_kaerma,"天启者","sup","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_shenv,"魔蛇之拥","mid","ap",2500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_jumo,"巨魔之王","top","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_lakesi,"光辉女郎","mid","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_shen,"暮光之眼","top","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_dazui,"深渊巨口","adc","ad",2000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_maerzhaha,"虚空先知","mid","ap",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_kainan,"狂暴之心","top","ad",2500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_akali,"离群之刺","mid","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_tienan,"铁铠冥魂","adc","ad",1500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_ez,"探险家","adc","ad",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_pansen,"战争之王","top","ad",1500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_jiutong,"酒桶","jug","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_wudier,"兽灵行者","jug","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_baonv,"狂野女猎手","jug","ap",3500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_goutou,"沙漠死神","top","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_datou,"大发明家","mid","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_kate,"不祥之刃","mid","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_shitouren,"熔岩巨兽","top","ad",1000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_jiqiren,"蒸汽机器人","sup","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_wuya,"诺克萨斯统领","mid","ap",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_xiaofa,"邪恶小法师","mid","ap",2000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_baoshi,"瓦罗兰之盾","sup","ap",1500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_feiji,"英勇投弹手","mid","ap",3500,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_chuanzhang,"海洋之灾","top","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_qinnv,"琴瑟仙女","sup","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_mengduo,"祖安狂人","top","ad",2000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_xiaochou,"恶魔小丑","jug","ad",2000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_fenghuang,"冰晶凤凰","mid","ap",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_amumu,"殇之木乃伊","jug","ap",2000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_dachongzi,"虚空恐惧","top","ap",1500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_sige,"死亡颂唱者","mid","ap",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_guafu,"痛苦之拥","jug","ap",2000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_lianjin,"炼金术士","top","ap",2000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_jilan,"时光守护者","mid","ap",1000,450,getContext());
        mgr.saveImage(R.drawable.legend_img_jiakesi,"武器大师","top","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_manwang,"蛮族之王","top","ad",3500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_nvqiang,"赏金猎人","adc","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_nunu,"雪原双子","jug","ap",1000,450,getContext());
        mgr.saveImage(R.drawable.legend_img_langren,"祖安巨兽","jug","ad",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_xiaopao,"麦林炮手","adc","ad",1000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_naima,"众星之子","sup","ap",1000,450,getContext());
        mgr.saveImage(R.drawable.legend_img_niutou,"牛头酋长","sup","ap",1000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_jiansheng,"无极剑圣","jug","ad",1000,450,getContext());
        mgr.saveImage(R.drawable.legend_img_tianshi,"审判天使","mid","ap",1000,450,getContext());
        mgr.saveImage(R.drawable.legend_img_yaoji,"诡术妖姬","mid","ap",2500,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_daocaoren,"末日使者","jug","ap",2000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_xixuegui,"猩红收割者","mid","ap",2500,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_ejiate,"无畏战车","top","ad",1000,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_kapai,"卡牌大师","mid","ap",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_jialiao,"正义巨像","mid","ap",2000,3150,getContext());
        mgr.saveImage(R.drawable.legend_img_aolafu,"狂战士","jug","ad",1500,1350,getContext());
        mgr.saveImage(R.drawable.legend_img_anni,"黑暗之女","mid","ap",2000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_nikou,"万花通灵","mid","ap",4800,6300,getContext());
        mgr.saveImage(R.drawable.legend_img_laoshu,"瘟疫之源","adc","ad",3000,4800,getContext());
        mgr.saveImage(R.drawable.legend_img_longwang,"铸星龙王","mid","ap",4500,6300,getContext());
    }



    private void data_search(){



        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM LEGEND_TABLE WHERE 1=1 ");
        if(!string_position.equals("位置")){
            sql.append(" AND LEGEND_POSITION = '"+string_position+"' ");

        }
        if(!string_damage.equals("伤害")){
            sql.append(" AND LEGEND_DAMAGE = '"+string_damage+"' ");
        }
        if(!int_gold.equals("金币")){
            sql.append(" AND LEGEND_GOLD = '"+int_gold+"' ");

        }
        if(!int_point.equals("点券")){
            sql.append(" AND LEGEND_POINT = '"+int_point+"'");
        }
        dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cur = db.rawQuery(sql.toString(),null);
        //testText.setText(sql.toString());
       while (cur.moveToNext()){

           /*for(int i=0;i<cur.getCount();i++)
           {*/
             //  Map<String,Object> map = new HashMap<String,Object>();
               HashMap<String,GridViewItem> tempHashMap = new HashMap<String, GridViewItem>();
            //   map.put("image",cur.getInt(cur.getColumnIndex("legend_img")));
               byte[] imgData ;
               imgData = cur.getBlob(cur.getColumnIndex("legend_img"));
               Bitmap imgBitmap = BitmapFactory.decodeByteArray(imgData,0,imgData.length);

               GridViewItem tempItem = new GridViewItem(imgBitmap,cur.getString(cur.getColumnIndex("legend_name")));

                tempHashMap.put("item",tempItem);
                hashMapList.add(tempHashMap);
               //map.put("image",imgBitmap);
            //   map.put("text",cur.getString(cur.getColumnIndex("legend_name")));

              // datalist.add(map);
           //}
           /*byte[] imgData ;
           imgData = cur.getBlob(cur.getColumnIndex("legend_img"));
           Bitmap imgBitmap = BitmapFactory.decodeByteArray(imgData,0,imgData.length);
           imageView.setImageBitmap(imgBitmap);*/
       }
        cur.close();
        db.close();



    }


}
