package com.example.malopus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ImageView mPlace;
    public static ArrayList<Integer> lastViewed = new ArrayList<Integer>();
    public static ArrayList<String> types = new ArrayList<String>();
    ;
    TextView tx;
    MaterialButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActivityCompat.requestPermissions(DetailActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        mPlace = findViewById(R.id.imageView1);
        button = findViewById(R.id.shopButton);
        tx = findViewById(R.id.TX);
        String mBundle = getIntent().getStringExtra("Image");
        if (mBundle != null) {
            Picasso.get()
                    .load(mBundle)
//                    .resize(200,200)
                    .into(mPlace);
        }
        String type1 = getIntent().getStringExtra("Type");
        Bundle mBundleInt = getIntent().getExtras();
        int a = mBundleInt.getInt("Position");
        if (lastViewed.contains(a)){
            lastViewed.remove(new Integer(a));
        }
        lastViewed.add(a);
        types.add(type1);
//        Collections.reverse(lastViewed);
        TinyDB tinydb = new TinyDB(this);
        tinydb.putListInt("MyUsers", lastViewed);
        tinydb.putListString("Types", types);
        if (type1.equals("shops")) {
            switch (a) {
                case 0:
                    tx.setText("У 1986 році Френк Міллер і Девід Маццуккеллі створили це видатне переосмислення походження Бетмена — того, ким він є, і як він таким став. «Рік перший», написаний невдовзі після «Повернення Темного Лицаря» — антиутопічної оповіді Міллера про останні дні Бетмена, створює новий погляд на легендарного героя. До цього видання входить повний графічний роман, новий вступ від Френка Міллера та нова ілюстрована післямова від художника Девіда Маццуккеллі. Це зібрання доповнюють понад 40 сторінок не публікованих раніше матеріалів — ескізи героїв і малюнків, зразки сторінок сценарію, зарисовки та інше, що дає змогу зазирнути в те, як створювалася ця сучасна класика.");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ridna-mova.com/komiksi/betmen-rik-pershij.html"));
                            startActivity(openlinkIntent);
                        }
                    });
                    break;
                case 1:
                    tx.setText("Геллбой (Hellboy) – культова серія коміксів від художника та сценариста – Майка Міньйоли." +
                            "Цей комікс, який відзначає цього року своє 25-річчя, розповідає історію червоного напівдемона Геллбоя, якого призвав в наш світ чаклун Распутін наприкінці другої світової війни задля того, щоб він став вирішальним чинником загибелі людства та початку Армагеддону. Але ще юний Геллбой потрапляє до спеціального паранормального підрозділа, який бориться із потойбічними силами, і сам згодом стає його агентом." +
                            "Серія Геллбой є багаторазовим лауреатом премії Айзнера - найвпливовішої премії у світі коміксів та графічних романів. Комікс був двічі екранізований відомим режисером Гільєрмо дель Торо («Лабіринт Фавна», «Тихоокеанський Рубіж»), а у квітні 2019 отримує нову екранізацію від Ніла Маршалла («Гра Престолів»)." +
                            "Перший том українського колекційного видання містить першу та другу частини серії, в яких події відбуваються через майже 50 років після появи червоного демона в людському світі, коли настає для Геллбоя час вирішувати - в чому саме є його Призначення. Видання містить кілька передмов від відомих авторів коміксів, в тому числі Алана Мура («Вартові», «V значить Vендетта»), а також артбук із біля двадцяти сторінок з малюнками та ескізами Майка Міньйоли із його коментарями та післямовою." +
                            "Комікс виданий за зразком американського колекційного видання збільшеного формату (А4), з чорною обкладинкою із золотим тисненням та ексклюзивною суперобкладинкою, та містить майже 300 сторінок.");
            }
        } else if (type1.equals("paid")){
            switch (a){
                case 0:
                    tx.setText("Ваша милосте! Послання це від варти древнього міста Аркмаар з сенешалем намісника на чолі ради! Місто було атаковане військовими невідомого ворога!… …Послання це нестимуть два гінці, довірені особи ради на чолі з сенешалем намісника, що смиренно просить Вашої помочі в цей скрутний час… – з послання варти Аркмаара монарху, 98 ф. п.Д.Р., пер. з родоксійського діал.");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://bookri.com.ua/product/noname"));
                            startActivity(openlinkIntent);
                        }
                    });

            }
        }
        else if (type1.equals("free")){
            switch (a){
                case 0:
                    tx.setText("Це непереможний Галк проти безсмертного Росомахи у найбільшій сутичці, яку світ тільки бачив. Нік Ф'юрі - директор З.А.Х.И.С.Т' у думав, що убив альтер его Галка, науковця Брюса Беннера. Але зараз гора люті і зелених м'язів стрибає по всій земній кулі. Ф'юрі має великий список найманців в його розпорядженні. Але лише одна людина здатна зробити цю справу правильно: Росомаха!");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });

            }
        }
    }
}
