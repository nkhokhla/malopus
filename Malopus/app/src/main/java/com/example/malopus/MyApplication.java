package com.example.malopus;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    public String[] PaidOnlineLinks = new String[]{"https://www.onthebus.com.ua/wa-data/public/shop/products/04/webp/76/78/7876/images/37938/37938.0x450@2x.webp", "http://bookri.com.ua/wp-content/uploads/2019/11/UA-Comix-%D0%9F%D1%80%D0%B5%D0%B7%D0%B5%D0%BD%D1%82%D1%83%D1%94-%D0%A7%D0%BE%D1%80%D1%82%D0%BE%D0%B1%D1%96%D0%B9-1.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/76653010_421187405224516_6105789812243955712_n.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2020/04/95062538_363508391289748_6895486443729190912_n.jpg", "http://bookri.com.ua/wp-content/uploads/2020/03/5-6%D1%96%D0%B2%D0%B0%D0%BF.jpg","https://mk0fireclawukra1xcqb.kinstacdn.com/wp-content/uploads/2020/04/cover.vol_.1.jpg", "https://mk0fireclawukra1xcqb.kinstacdn.com/wp-content/uploads/2020/04/01-vol.2.chap_.8-10_hello_to_blackjack-main-675x1024.jpg", "https://mk0fireclawukra1xcqb.kinstacdn.com/wp-content/uploads/2019/09/01_chase-cover-min.jpg"};
    public String[] FreeOnlineLinks = new String[]{"https://static.wixstatic.com/media/f41107_c2d46f2bded14525b99cf3d87a25ec40~mv2.jpg/v1/fill/w_528,h_798,al_c,q_85,usm_0.66_1.00_0.01/f41107_c2d46f2bded14525b99cf3d87a25ec40~mv2.webp", "https://static.wixstatic.com/media/f41107_9a665a16f5e54d8b934a4c506a25a376~mv2_d_1427_2160_s_2.jpg/v1/fill/w_528,h_798,al_c,q_85,usm_0.66_1.00_0.01/f41107_9a665a16f5e54d8b934a4c506a25a376~mv2_d_1427_2160_s_2.webp", "https://static.wixstatic.com/media/f41107_2715a240b30142bfb47a98bb4b18e387~mv2.png/v1/fill/w_300,h_462,al_c,q_85,usm_0.66_1.00_0.01/f41107_2715a240b30142bfb47a98bb4b18e387~mv2.webp", "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreGprRlowZXB3RE1KYWtw/root/content", "https://static.wixstatic.com/media/f41107_0179bbdbe0724f0c802df3e7f2139f40~mv2.jpg/v1/fill/w_528,h_798,al_c,q_85,usm_0.66_1.00_0.01/f41107_0179bbdbe0724f0c802df3e7f2139f40~mv2.webp", "https://p.calameoassets.com/181002111210-d53469b09be86398579ae8a4ecae1216/p1.jpg", "https://static.wixstatic.com/media/f41107_f9076ac3358d4fe98d9011ec1e7659b5~mv2.jpg/v1/fill/w_528,h_798,al_c,q_85,usm_0.66_1.00_0.01/f41107_f9076ac3358d4fe98d9011ec1e7659b5~mv2.webp"};
    public String[] ShopsLinks = new String[]{"https://www.onthebus.com.ua/wa-data/public/shop/products/00/webp/57/48/4857/images/13535/13535.0x970.webp", "https://www.onthebus.com.ua/wa-data/public/shop/products/00/webp/17/82/8217/images/32771/32771.0x970.webp","https://www.onthebus.com.ua/wa-data/public/shop/products/00/webp/82/80/8082/images/33012/33012.0x970.webp", "https://www.onthebus.com.ua/wa-data/public/shop/products/05/95/9505/images/33924/33924.0x970.jpg", "https://www.onthebus.com.ua/wa-data/public/shop/products/53/56/5653/images/17229/17229.0x450.png", "https://www.onthebus.com.ua/wa-data/public/shop/products/72/97/9772/images/34917/34917.0x970.jpg","https://www.onthebus.com.ua/wa-data/public/shop/products/92/01/10192/images/36655/36655.0x970.png", "https://www.onthebus.com.ua/wa-data/public/shop/products/28/55/5528/images/16736/16736.0x970.jpg", "https://www.onthebus.com.ua/wa-data/public/shop/products/00/webp/55/48/4855/images/13532/13532.0x970.webp", "https://www.onthebus.com.ua/wa-data/public/shop/products/25/63/6325/images/20199/20199.0x970.jpg","https://www.onthebus.com.ua/wa-data/public/shop/products/04/webp/38/78/7838/images/27067/27067.0x970.webp", "https://www.onthebus.com.ua/wa-data/public/shop/products/43/58/5843/images/18112/18112.0x970.jpg"};
    public static ArrayList<Integer> lastViewed = new ArrayList<Integer>();
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
    public int getLastViewed(int position) {
        return lastViewed.get(position);
    }
    public ArrayList<Integer> getLastViewed(){return lastViewed;}
    public void addLastViewed(int position, int element) {
        Log.d("my test", String.valueOf(position));
        lastViewed.add(position, element);
    }
    public void removeLastViewed(Integer toRemove) {lastViewed.remove(toRemove);}
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
    public boolean isEmpty() {
        if(lastViewed.size()==0){
            return true;
        }
        return false;
    }
    public void setShopsLinks(String[] shopsLinks) {
        this.ShopsLinks = shopsLinks;
    }
}

