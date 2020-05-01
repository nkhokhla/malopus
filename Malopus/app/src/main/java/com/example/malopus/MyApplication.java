package com.example.malopus;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    public String[] PaidOnlineLinks = new String[]{"https://www.onthebus.com.ua/wa-data/public/shop/products/04/webp/76/78/7876/images/37938/37938.0x450@2x.webp", "https://static.wixstatic.com/media/f41107_c2d46f2bded14525b99cf3d87a25ec40~mv2.jpg/v1/fill/w_528,h_798,al_c,q_85,usm_0.66_1.00_0.01/f41107_c2d46f2bded14525b99cf3d87a25ec40~mv2.webp","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg"};
    public String[] FreeOnlineLinks = new String[]{"https://static.wixstatic.com/media/f41107_9a665a16f5e54d8b934a4c506a25a376~mv2_d_1427_2160_s_2.jpg/v1/fill/w_528,h_798,al_c,q_85,usm_0.66_1.00_0.01/f41107_9a665a16f5e54d8b934a4c506a25a376~mv2_d_1427_2160_s_2.webp"};
    public String[] ShopsLinks = new String[]{"https://www.onthebus.com.ua/wa-data/public/shop/products/00/webp/57/48/4857/images/13535/13535.0x970.webp", "https://www.onthebus.com.ua/wa-data/public/shop/products/00/webp/17/82/8217/images/32771/32771.0x970.webp","https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg","https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg","https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg"};
    public String[] getOnlineLinks(String type) {
        if(type.equals("paid")) {
            return PaidOnlineLinks;
        } else{
            return FreeOnlineLinks;
        }
    }
    public String[] getShopsLinks() {
        return ShopsLinks;
    }
    public String getOnlineLink(int i, String type){
        if(type.equals("paid")) {
            return PaidOnlineLinks[i];
        } else{
            return FreeOnlineLinks[i];
        }}
    public String getShopLink(int i){return ShopsLinks[i];}
    public void setOnlineLinks(String[] onlineLinks, String type) {
        if(type.equals("paid")){
        this.PaidOnlineLinks = onlineLinks;
        } else{
            this.FreeOnlineLinks = onlineLinks;
        }
    }
    public void setShopsLinks(String[] shopsLinks) {
        this.ShopsLinks = shopsLinks;
    }
}

