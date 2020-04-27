package ed.anpanman;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Profile extends AppCompatActivity {
    FirebaseUser firebaseUser;
    DatabaseReference ref;
    ImageView propic;
    TextView name,finished_lessons,points,email,term,rank;
   int actualrank=0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name =findViewById(R.id.profileUserName);
        propic=findViewById(R.id.propic);
        finished_lessons=findViewById(R.id.finished_lessons);
        points=findViewById(R.id.points);
        email=findViewById(R.id.pemail);
        term=findViewById(R.id.pterm);
        rank=findViewById(R.id.prank);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        ref= FirebaseDatabase.getInstance().getReference("Student").child(firebaseUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Student student=dataSnapshot.getValue(Student.class);
                name.setText(student.getUsername());
                points.setText((student.getPoints()));
                email.setText(student.getEmail());
                term.setText(student.getGrade());
                rank.setText("ترتيبك الحالى رقم: "+actualrank);
                if(student.getImageURL().equals("default"))
                {

                    propic.setImageResource(R.drawable.circle_user);

                }
                else
                {

                    Glide.with(Profile.this).load(student.getImageURL()).into(propic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
