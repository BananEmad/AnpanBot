package ed.anpanman;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ed.anpanman.Model.Student;

public class Video extends AppCompatActivity {

    String chem;
    String hist ;
CardView cd1,cd2,cd3,cd4,cd5;
    ImageView profilepic;
    TextView nameuser;
    FirebaseUser firebaseUser;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        hist=  getResources().getString(R.string.history);
        nameuser = findViewById(R.id.nameuser);
        profilepic = findViewById(R.id.imageView2);
        chem = getResources().getString(R.string.chemical);
        cd1=findViewById(R.id.cd1);
        cd2=findViewById(R.id.cd2);
        cd3=findViewById(R.id.cd3);
        cd4=findViewById(R.id.cd4);
        cd5=findViewById(R.id.cd5);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Student").child(firebaseUser.getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Student student = dataSnapshot.getValue(Student.class);
                nameuser.setText(student.getUsername());
                if (student.getImageURL().equals("default")) {

                    profilepic.setImageResource(R.drawable.circle_user);
                } else {

                    Glide.with(Video.this).load(student.getImageURL()).into(profilepic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Bundle bundle = getIntent().getExtras();
       String sub=bundle.get("Sub").toString();
        if (sub.equals(chem))
        {
          cd1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent it=new Intent(Video.this,videoplayer.class);
                  it.putExtra("name","v1");
                  startActivity(it);
              }
          });
            cd2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(Video.this,videoplayer.class);
                    it.putExtra("name","v2");
                    startActivity(it);
                }
            });
            cd3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(Video.this,videoplayer.class);
                    it.putExtra("name","v4");
                    startActivity(it);
                }
            });
            cd4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(Video.this,videoplayer.class);
                    it.putExtra("name","v5");
                    startActivity(it);
                }
            });
            cd5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(Video.this,videoplayer.class);
                    it.putExtra("name","v6");
                    startActivity(it);
                }
            });
        }
        else if (sub.equals(hist))
        {

        }


    }
}
