package ed.anpanman;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nightonke.boommenu.BoomButtons.HamButton;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.List;

import ed.anpanman.Adapter.lessonAdapter;
import ed.anpanman.Model.Student;
import ed.anpanman.Model.lessonModel;

public class LessonList extends AppCompatActivity {

     List<Integer>imghistory;
    List<Integer>imgchemistry;
    String Sub[];
    String chem;
    String hist;
    ImageView profilepic;
    TextView nameuser;
    FirebaseUser firebaseUser;
    DatabaseReference ref;

    FirebaseFirestore d = FirebaseFirestore.getInstance();
    CollectionReference hist_ref = d.collection("/Lesson/History/الوحدة الأولى");
    CollectionReference chem_ref = d.collection("/Lesson/Chemistry  /الوحدة الاولى");
    lessonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);


        chem = getResources().getString(R.string.chemical);
        hist = getResources().getString(R.string.history);
        imghistory=new ArrayList<>();
        imgchemistry=new ArrayList<>();
        imgchemistry.add(R.drawable.chem1);
        imgchemistry.add(R.drawable.chem2);
        imgchemistry.add(R.drawable.chem3);
        imgchemistry.add(R.drawable.chem4);



        imghistory.add(R.drawable.hist1);
        imghistory.add(R.drawable.hist2);
        imghistory.add(R.drawable.hist3);
        imghistory.add(R.drawable.hist4);



        nameuser = findViewById(R.id.nameuser);
        profilepic = findViewById(R.id.imageView2);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Student").child(firebaseUser.getUid());


        Bundle bundle = getIntent().getExtras();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Student student = dataSnapshot.getValue(Student.class);
                nameuser.setText(student.getUsername());
                if (student.getImageURL().equals("default")) {

                    profilepic.setImageResource(R.drawable.circle_user);
                } else {

                    Glide.with(LessonList.this).load(student.getImageURL()).into(profilepic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (bundle.getStringArray("arr") != null) {
            Sub = bundle.getStringArray("arr");
        }
        if (Sub[1].equals("lessons")) {
            if (Sub[0].equals(chem)) {

                setUpChemistr();

                adapter.setOnItemClickListener(new lessonAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClick(DocumentSnapshot documentSnapshot, int position, String choice) {
                        lessonModel model=documentSnapshot.toObject(lessonModel.class);
                        final String current =model.getTitle();
                        if(choice=="lessons")
                        {

                        }
                        else if (choice=="Quiz")
                        {

                        }
                        else if (choice=="QBank")
                        {
                            final CollectionReference history_ref = d.collection("/Question_Bank/History/الوحدة الاولى");
                            history_ref.get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            int i=0;
                                            for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                                                lessonModel less=documentSnapshot.toObject(lessonModel.class);
                                                if (less.getTitle().contains(current))
                                                {
                                                    String temp="/Question_Bank/Chemistry/الوحدة الاولى/المادة وخواصها/الاسئلة";
                                                    Intent it = new Intent(LessonList.this,QBank.class);
                                                    it.putExtra("qb_ref",temp);
                                                    startActivity(it);

                                                }
                                                i++;
                                            }
                                        }
                                    });
                        }
                        else if (choice=="video")
                        {
                            Intent it=new Intent (LessonList.this,Video.class);
                            it.putExtra("Sub",chem);
                            startActivity(it);
                        }
                    }
                });
            }
            if (Sub[0].equals(hist)) {

                setUpHistory();
                adapter.setOnItemClickListener(new lessonAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClick(DocumentSnapshot documentSnapshot, int position, String choice) {
                        lessonModel model=documentSnapshot.toObject(lessonModel.class);
                        final String current =model.getTitle();
                        if(choice=="lessons")
                        {

                        }
                        else if (choice=="Quiz")
                        {

                        }
                        else if (choice=="QBank")
                        {
                          final CollectionReference history_ref = d.collection("/Question_Bank/History/الوحدة الاولى");
                            history_ref.get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            int i=0;
                                            for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                                                lessonModel less=documentSnapshot.toObject(lessonModel.class);
                                                    if (less.getTitle().contains(current))
                                                    {
                                                        String temp="/Question_Bank/History/الوحدة الاولى/غزوات الرسول (صلى الله عليه وسلم) :دعوته وكفاحه/الأسئلة";
                                                        Intent it = new Intent(LessonList.this,QBank.class);
                                                        it.putExtra("qb_ref",temp);
                                                        startActivity(it);

                                                    }
                                                i++;
                                            }
                                        }
                                    });


                        }
                        else if (choice=="video")
                        {
                            Intent it=new Intent (LessonList.this,Video.class);
                            it.putExtra("Sub",hist);
                            startActivity(it);
                        }
                    }
                });


            }

        }



    }
    @SuppressWarnings("unchecked")
    private void setUpHistory()
    {
        Query query=hist_ref.orderBy("no", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions options=new FirestoreRecyclerOptions.Builder<lessonModel>()
                .setQuery(query,lessonModel.class)
                .build();
        adapter=new lessonAdapter(options,imghistory);
        RecyclerView recyclerView=findViewById(R.id.lessonRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
    @SuppressWarnings("unchecked")

    public void setUpChemistr()
    {
        Query query=chem_ref.orderBy("no", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions options=new FirestoreRecyclerOptions.Builder<lessonModel>()
                .setQuery(query,lessonModel.class)
                .build();
        adapter=new lessonAdapter(options,imgchemistry);
        RecyclerView recyclerView=findViewById(R.id.lessonRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



    }
    @SuppressWarnings("unchecked")

    protected void onStart() {

        super.onStart();

        adapter.startListening();
    }
    @SuppressWarnings("unchecked")

    protected  void onStop() {

        super.onStop();
        adapter.stopListening();
    }
}

