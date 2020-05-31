package com.example.malopus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DetailActivity extends AppCompatActivity {
    ImageView mPlace;

    public static ArrayList<String> types = new ArrayList<String>();
    ;
    TextView tx;
    MaterialButton button;
    MaterialButton episode2;
    MaterialButton episode3;
    MaterialButton episode4;
    MaterialButton episode5;
    MaterialButton episode6;
    File file;
    LinearLayout episodes;
    private long downloadID;
    String GlobalName;
    private File root;
    File comicFile;
    ComicItem toReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActivityCompat.requestPermissions(DetailActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        mPlace = findViewById(R.id.imageView1);
        button = findViewById(R.id.shopButton);
        episode2 = findViewById(R.id.shopButton2);
        episode3 = findViewById(R.id.shopButton3);
        episode4 = findViewById(R.id.shopButton4);
        episode5 = findViewById(R.id.shopButton5);
        episode6 = findViewById(R.id.shopButton6);
        tx = findViewById(R.id.TX);
        root = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath());
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
//        Collections.reverse(lastViewed);

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
                    break;
                case 1:
                    tx.setText("Хто сказав, що бій із дідьками буде легким, чи безпечним? Що тонку межу між світами не можна перетнути так само як перейти на той бік вулиці у час пік? І хто би нас мав вберегти від цього… світу Нави? Від лиха, що чигає на нас за кутом?");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://bookri.com.ua/product/%d1%81hortobiy"));
                            startActivity(openlinkIntent);
                        }
                    });
                    break;
                case 2:
                    tx.setText("Щоб здолати Зло, потрібно буде принести в жертву звичний світогляд. Перша половина ХІІІ ст. Хрестоносець Тівадар повертається зі Святої Землі додому. По дорозі він натрапляє на місто, в яке заїхати можна, а виїхати – вже ні, бо заважає якась невидима стіна. Час від часу в місті з’являються істоти в чорних плащах верхи на монстрах і забирають двох або трьох людей. Багато мешканців у відчаї накладають на себе руки, вбивають своїх дітей, щоб ті не дісталися тим істотам, інші просто змирилися і чекають, коли і по них прийдуть. Яка причина цієї «бісівщини»? Чи є в цьому вина містян? Чи вдасться лицарю вибратися з цього проклятого міста?");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://bookri.com.ua/product/mor"));
                            startActivity(openlinkIntent);
                        }
                    });
                    break;
            }
        }
        else if (type1.equals("free")){
            Log.d("my test","in free" );
            MyApplication myAppClass = ((MyApplication) getApplicationContext());
            if (myAppClass.getLastViewed().contains(a)){
                Log.d("my test","contains" );
                myAppClass.removeLastViewed(new Integer(a));
            }
            Log.d("my test","in free 2" );
            myAppClass.addLastViewed(0, a);
            switch (a){
                case 0:
                    episode2.setVisibility(View.VISIBLE);
                    episode3.setVisibility(View.VISIBLE);
                    button.setText(getString(R.string.episode1));
                    tx.setText("Нас вчать пам`ятати не людину, а ідею. Людина слабка: її можна впіймати, убити та забути, але ідеї, вони й через 400 років здатні змінити світ.");
                    registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic.cbz";
                            try {
                         try{
                                        Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                        comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  "MalopusComic.cbz").getentries() );
                                        startActivity(comicintent);
                                    } catch (IOException e) {
                                        Log.d("success",   e.getMessage());
                                        e.printStackTrace();
                                    }
                        }
                              catch (NullPointerException e) {
                                    beginDownload("MalopusComic.cbz", "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNrd3QzdnlYT3BQd21kM1kt/root/content");

                            }

                        }
                    });
                    episode2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic2.cbz";
                            try {

                                    Log.d("success", "nit");
                                    try {
                                        Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                        comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  "MalopusComic2.cbz").getentries() );
                                        startActivity(comicintent);
                                    } catch (IOException e) {
                                        Log.d("success",   e.getMessage());
                                        e.printStackTrace();
                                    }

                            }  catch (NullPointerException e) {
                                beginDownload("MalopusComic2.cbz", "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNrd3lzMk1MYlZzSEpiUWxJ/root/content");
                            }

                        }
                    });
                    episode3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic3.cbz";
                            try {

                                Log.d("success", "nit");
                                try {
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  "MalopusComic3.cbz").getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }

                            }  catch (NullPointerException e) {
                                beginDownload("MalopusComic3.cbz", "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNrdzIyU2UwemRhTjVRNWkz/root/content");
                            }

                        }
                    });
                    break;
                case 1:
                    episode2.setVisibility(View.VISIBLE);
                    episode3.setVisibility(View.VISIBLE);
                    episode4.setVisibility(View.VISIBLE);
                    episode5.setVisibility(View.VISIBLE);
                    episode6.setVisibility(View.VISIBLE);
                    button.setText(getString(R.string.episode1));
                    tx.setText("Це непереможний Галк проти безсмертного Росомахи у найбільшій сутичці, яку світ тільки бачив. Нік Ф'юрі - директор З.А.Х.И.С.Т' у думав, що убив альтер его Галка, науковця Брюса Беннера. Але зараз гора люті і зелених м'язів стрибає по всій земній кулі. Ф'юрі має великий список найманців в його розпорядженні. Але лише одна людина здатна зробити цю справу правильно: Росомаха!");
                    registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic4.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  "MalopusComic4.cbz").getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload("MalopusComic4.cbz", "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNrd29HVEV6Uzh0ZXFUZ3c5/root/content");

                            }

                        }
                    });
                    episode2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic5.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreEFneTZRMUZ6aThlUjVr/root/content");

                            }

                        }
                    });
                    episode3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic6.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNrdzdUczZNQUxvZjB2cHJN/root/content");

                            }

                        }
                    });
                    episode4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic7.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNrdzlPT2FiNUJNbWJWblVT/root/content");

                            }

                        }
                    });
                    episode5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic9.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreExnYmNaVXgxVkJHLXFB/root/content");

                            }

                        }
                    });
                    episode6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic10.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreEY4a1drZVh2WVV4TDlF/root/content");

                            }

                        }
                    });
            break;
            case 2:
                button.setText(getString(R.string.episode1));
                tx.setText("Описувати сюжет немає сенсу, адже ви прочитаєте цей комікс за 5 хвилин, але все ж скажу, що це одна з історій написаних для щорічника по Бетмену.");
                registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        root = new File(Environment.getExternalStorageDirectory()
                                .getAbsolutePath());
                        GlobalName = "MalopusComic11.cbz";
                        try {
                            try{
                                Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                startActivity(comicintent);
                            } catch (IOException e) {
                                Log.d("success",   e.getMessage());
                                e.printStackTrace();
                            }
                        }
                        catch (NullPointerException e) {
                            beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreE5ickxhVDlDd1R1OUxr/root/content");

                        }

                    }
                });
                break;
                case 3:
                    button.setText(getString(R.string.episode1));
                    tx.setText("Френк Кастл був почесним морпіхом, порядним громадянином і хорошим сім'янином. Та його рідних було випадково вбито у кривавій вуличній перестрілці. З того часу, він став холодним, прораховано пильним та мстивим. Френк Кастл помер разом зі своєю сім'єю. Лишився лише \"Каратель\".");
                    registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic12.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreFZiOElmYzlVSlFCd1Bt/root/content");

                            }

                        }
                    });
                break;
                case 4:
                    button.setText(getString(R.string.episode1));
                    tx.setText("Опинившись в пастці під куполом, втративши здібності і сім'ю, Баррі намагається не втрачати надію і продовжує боротися. Як це в нього виходить, і чи виходить взагалі, можеш глянути сам, спільничку.");
                    registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic13.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreFQ0UUUwWmxHbFFLcmtw/root/content");

                            }

                        }
                    });
                break;
                case 5:
                    button.setText(getString(R.string.episode1));
                    tx.setText("Нова історія пригод Геллбоя розповідає про його зустріч із Крампусом, демоном-цапом і супротивником Санта Клауса.");
                    registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic14.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreGRlcG43ZmZWV3pBcnJt/root/content");

                            }

                        }
                    });
                break;
                case 6:
                    episode2.setVisibility(View.VISIBLE);
                    episode3.setVisibility(View.VISIBLE);
                    episode4.setVisibility(View.VISIBLE);
                    button.setText(getString(R.string.episode1));
                    tx.setText("Наташа Романова завжди була висококласним шпигуном і роками збирала компромат, як на ворогів, так і на друзів. Коли деякі із найтемніших таємниць якимось загадковим чином набувають розголосу — Чорна Вдова стає ворогом суспільства номер один. Марія Гілл та З.А.Х.И.С.Т. зобов'язались зупинити Вдову за будь-яку ціну.");
                    registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic15.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreFluTThqNmxXWl9WUzZJ/root/content");

                            }

                        }
                    });
                    episode2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic16.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreWdwSmY0UlB6RFY1SThr/root/content");

                            }

                        }
                    });
                    episode3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic17.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreW52dncxaHFPUnFBZGtV/root/content");

                            }

                        }
                    });
                    episode4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            root = new File(Environment.getExternalStorageDirectory()
                                    .getAbsolutePath());
                            GlobalName = "MalopusComic18.cbz";
                            try {
                                try{
                                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                                    startActivity(comicintent);
                                } catch (IOException e) {
                                    Log.d("success",   e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            catch (NullPointerException e) {
                                beginDownload(GlobalName, "https://api.onedrive.com/v1.0/shares/u!aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBcDMwMVN0amRycFNreWZrcDVua2hJWXBOLWNG/root/content");

                            }

                        }
                    });
                break;

            }
        }
    }
    public ComicItem getfile(File dir,  String name) throws IOException {
        File[] listFile = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {

                   break;
                } else {
                    if (listFile[i].getName().equals(name))

                    {
                        Log.d("success", dir.toString());
                        ZipFile zipFile = new ZipFile(listFile[i].getAbsolutePath());
                        Enumeration<? extends ZipEntry> entries = zipFile.entries();
                        Bitmap photo = null;
                        ArrayList<ZipEntry> entryList = new ArrayList<ZipEntry>();
                        ArrayList<String> shortish = new ArrayList<String>();
                        while(entries.hasMoreElements()) {

                            ZipEntry entry = entries.nextElement();
                            if(entry.getName().endsWith(".png")||entry.getName().endsWith(".jpg")){

                                entryList.add(entry);
                                shortish.add(entry.getName());
                            }

                        }
                        int smallestIndex = 0;
                        for (int y = 1; y < shortish.size(); y++) {
                            int compare = shortish.get(y).compareTo(shortish.get(smallestIndex));
                            if (compare < 0)
                                smallestIndex = y;
                        }
                        photo = BitmapFactory.decodeStream( zipFile.getInputStream(entryList.get(smallestIndex)));

                        Log.d("succcess", listFile[i].getAbsolutePath());
                        toReturn = new ComicItem(listFile[i].getName(),photo,listFile[i].getAbsolutePath());
                        zipFile.close();
                    }
                }

            }
        }
        return toReturn;
    }
    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Log.d("success", "XZ");
                try {
                    Intent comicintent = new Intent(getBaseContext(), Open_Comics.class);
                    comicintent.putExtra("dir",getfile(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),  GlobalName).getentries() );
                    startActivity(comicintent);
                } catch (IOException e) {
                    Log.d("success",   e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    };
    public void beginDownload(String name, String url){
        DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//                                    Log.d("success", uri.toString());
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("MalopusComic");
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
//        Uri fileLocation = Uri.fromFile(file);
        request.setDestinationInExternalFilesDir(this,Environment.DIRECTORY_DOWNLOADS, name);

        downloadID = downloadmanager.enqueue(request);
//        comicFile = new File(fileLocation.getPath());
//        Log.d("success", comicFile.toString());

    }
}
