package ed.anpanman;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ed.anpanman.Adapter.currAdapter;
import ed.anpanman.Model.currModel;

public class Curriculum extends AppCompatActivity {
     ViewPager viewPager;
     currAdapter adapter;
     static List<currModel>models;
     Integer[]colors=null;
     static  TextView sub;
     Button btnstart;
    String parent;
     List<Drawable>res;
    ArgbEvaluator argbEvaluator=new ArgbEvaluator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);
        res=new ArrayList<>();
        btnstart=findViewById(R.id.btnstart);
        sub=findViewById(R.id.sub);
        models=new ArrayList<>();
        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("parent")!= null)
            parent= bundle.getString("parent");
        try{
            InputStream inputStream1=getAssets().open("history.gif");
            byte[]bytes= IOUtils.toByteArray(inputStream1);
            InputStream inputStream2=getAssets().open("chemistry.gif");
            byte[]bytes2= IOUtils.toByteArray(inputStream2);
            models.add(new currModel(bytes,getResources().getString(R.string.history)));
            models.add(new currModel(bytes2,getResources().getString(R.string.chemical)));
            res.add(getResources().getDrawable(R.drawable.historybtn));
            res.add(getResources().getDrawable(R.drawable.chemistrybtn));

           }
        catch (IOException io){

        }

        adapter=new currAdapter(models,this);
        viewPager=findViewById(R.id.currviewpager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100,0,100,0);


        Integer[] colors_temp=
                {
                        getResources().getColor(R.color.color11),
                        getResources().getColor(R.color.color12),

                };

        colors=colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

                if(i<(adapter.getCount()-1)&&i<(colors.length -1)){
                    viewPager.setBackgroundColor(
                            (Integer)argbEvaluator.evaluate(
                                    v,
                                    colors[i],
                                    colors[i+1])
                    );

                        btnstart.setBackground(res.get(i));


                }
                else
                {
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                    btnstart.setBackground(res.get(res.size()-1));

                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnstart.getBackground()==res.get(1)) {
                    Intent it = new Intent(Curriculum.this, LessonList.class);
                    String[]arr=new String[]{
                            getResources().getString(R.string.chemical),
                            parent
                    };
                    it.putExtra("arr",arr);
                    startActivity(it);
                }
              else if(btnstart.getBackground()==res.get(0)) {
                    Intent it = new Intent(Curriculum.this, LessonList.class);
                    String[]arr=new String[]{
                            getResources().getString(R.string.history),
                            parent
                    };
                    it.putExtra("arr",arr );
                    startActivity(it);
                }
            }
        });



    }
}
