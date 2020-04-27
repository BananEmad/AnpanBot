package ed.anpanman;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import ed.anpanman.Adapter.QBankAdapter;
import ed.anpanman.Model.QBank_model;

public class QBank extends AppCompatActivity {
    static ViewPager viewPager;
    static QBankAdapter adapter;
    List<QBank_model> models;
   static List<QBank_model>QA;
   static Context context;
   List <Integer>imag;
    Integer[]colors=null;
    ArgbEvaluator argbEvaluator=new ArgbEvaluator();
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    CollectionReference qb_ref=db.collection("/Question_Bank/Chemistry/الوحدة الاولى_محوله تصنيف العناصر");
    private DocumentReference ques=db.document("Question_Bank/تاريخ_غزوات الرسول");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qbank);
        imag=new ArrayList<>();
        QA=new ArrayList<>();
        context=this;
        models=new ArrayList<>();
        imag.add(R.drawable.num1);
        imag.add(R.drawable.num2);
        imag.add(R.drawable.num3);
        imag.add(R.drawable.num4);
        imag.add(R.drawable.num5);
        imag.add(R.drawable.num6);
        imag.add(R.drawable.num7);
        imag.add(R.drawable.num8);
        imag.add(R.drawable.num9);
        imag.add(R.drawable.num10);
        imag.add(R.drawable.num11);
        imag.add(R.drawable.num12);
        imag.add(R.drawable.num13);
        imag.add(R.drawable.num14);
        imag.add(R.drawable.num15);
        imag.add(R.drawable.num16);
        imag.add(R.drawable.num17);
        imag.add(R.drawable.num18);
        imag.add(R.drawable.num19);
        imag.add(R.drawable.num2);
        imag.add(R.drawable.num10);
        imag.add(R.drawable.num11);
        imag.add(R.drawable.num12);
        imag.add(R.drawable.num13);
        imag.add(R.drawable.num14);
        imag.add(R.drawable.num15);
        imag.add(R.drawable.num16);
        imag.add(R.drawable.num17);
        imag.add(R.drawable.num18);
        imag.add(R.drawable.num19);
        imag.add(R.drawable.num10);
        imag.add(R.drawable.num11);
        imag.add(R.drawable.num12);
        imag.add(R.drawable.num13);
        imag.add(R.drawable.num14);
        imag.add(R.drawable.num15);
        imag.add(R.drawable.num16);
        imag.add(R.drawable.num17);
        imag.add(R.drawable.num18);
        imag.add(R.drawable.num19);

        Bundle bundle = getIntent().getExtras();
        qb_ref= db.collection(bundle.getString("qb_ref"));
        qb_ref.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                         int i=0;
                        for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                        {
                            QBank_model qb=documentSnapshot.toObject(QBank_model.class);

                            models.add(new QBank_model(imag.get(i),qb.getQuestion(),qb.getAnswer()));
                            i++;
                        }
                        adapter =new QBankAdapter(models,context) ;
                        viewPager=findViewById(R.id.qbviewpager);
                        viewPager.setAdapter(adapter);
                        viewPager.setPadding(130,0,130,0);
                    }

                });


        viewPager=findViewById(R.id.qbviewpager);
        viewPager.setAdapter(adapter);




        Integer[] colors_temp=
                {
                        getResources().getColor(R.color.color1),
                        getResources().getColor(R.color.color2),
                        getResources().getColor(R.color.color03),
                        getResources().getColor(R.color.color4),
                        getResources().getColor(R.color.color5),
                        getResources().getColor(R.color.color6),
                        getResources().getColor(R.color.color7),
                        getResources().getColor(R.color.color8),
                        getResources().getColor(R.color.color9),
                        getResources().getColor(R.color.color10),
                        getResources().getColor(R.color.color1),
                        getResources().getColor(R.color.color2),
                        getResources().getColor(R.color.color03),
                        getResources().getColor(R.color.color4),
                        getResources().getColor(R.color.color5),
                        getResources().getColor(R.color.color6),
                        getResources().getColor(R.color.color7),
                        getResources().getColor(R.color.color8),
                        getResources().getColor(R.color.color9)
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
                }
                else
                {
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });



    }


}
