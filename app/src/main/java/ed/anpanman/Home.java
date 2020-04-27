package ed.anpanman;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Util;
import ed.anpanman.Model.Student;

public class Home extends AppCompatActivity {
    TextView nameuser, network, plugins, myapps,
            pagetitle;
    Button btnguide;
    Animation atg,atg2, atg3, atg4;
    ImageView imageView3,qb,quiz,lessons,profilepic;
    BoomMenuButton bmb ;
    FirebaseUser firebaseUser;
    DatabaseReference ref;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameuser =findViewById(R.id.nameuser);
        profilepic=findViewById(R.id.imageView2);

        network = findViewById(R.id.network);
        plugins =findViewById(R.id.plugins);
        myapps = findViewById(R.id.myapps);
        pagetitle =findViewById(R.id.pagetitle);
        linearLayout=findViewById(R.id.ll2);
        btnguide =findViewById(R.id.btnguide);
        imageView3 =findViewById(R.id.imageView3);

        qb= (ImageView)findViewById(R.id.imgqb);
        quiz= (ImageView)findViewById(R.id.imgquiz);
        lessons= (ImageView)findViewById(R.id.imglessons);

//firebase
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        ref= FirebaseDatabase.getInstance().getReference("Student").child(firebaseUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Student student=dataSnapshot.getValue(Student.class);
                nameuser.setText(student.getUsername());
                if(student.getImageURL().equals("default"))
                {

                    profilepic.setImageResource(R.drawable.circle_user);
                }
                else
                {

                    Glide.with(Home.this).load(student.getImageURL()).into(profilepic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//boom menu
        bmb = (BoomMenuButton) findViewById(R.id.bmb);

        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            int position =i;
          if (position==0)
          {
              TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                      .normalImageRes(R.drawable.ic_chat)
                      .rotateImage(false)
                      .rotateText(false)
                      .normalTextRes(R.string.chat)
                      .shadowEffect(true)
                      .shadowOffsetX(20)
                      .shadowOffsetY(0)
                      .shadowRadius(Util.dp2px(20))
                      .shadowCornerRadius(Util.dp2px(20))
                      .shadowColor(R.color.colorPrimaryDark)
                      .textPadding(new Rect(5, 5, 5, 5))

                     .listener(new OnBMClickListener() {
                          @Override
                          public void onBoomButtonClick(int index) {
                              // When the boom-button corresponding this builder is clicked.
                              Intent it = new Intent(Home.this, chat.class);
                              it.putExtra("userid","l5eZsfdMjNXXw2wMyat4rEDz4SH3");
                              startActivity(it);
                          }
                      });

              bmb.addBuilder(builder);
          }
          else if (position==1)
          {
              TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                      .normalImageRes(R.drawable.ic_profile)
                      .rotateImage(false)
                      .rotateText(false)
                      .normalTextRes(R.string.profile)
                      .shadowEffect(true)
                      .shadowOffsetX(20)
                      .shadowOffsetY(0)
                      .shadowRadius(Util.dp2px(20))
                      .shadowCornerRadius(Util.dp2px(20))
                      .shadowColor(R.color.colorPrimaryDark)
                      .textPadding(new Rect(5, 5, 5, 5))
                      .listener(new OnBMClickListener() {
                          @Override
                          public void onBoomButtonClick(int index) {
                              // When the boom-button corresponding this builder is clicked.
                              Intent it = new Intent(Home.this, Profile.class);
                              startActivity(it);
                          }
                      });

              bmb.addBuilder(builder);
          }
          else if (position==2)
          {
              TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                      .normalImageRes(R.drawable.ic_quiz)
                      .rotateImage(false)
                      .rotateText(false)
                      .normalTextRes(R.string.quiz)
                      .shadowEffect(true)
                      .shadowOffsetX(20)
                      .shadowOffsetY(0)
                      .shadowRadius(Util.dp2px(20))
                      .shadowCornerRadius(Util.dp2px(20))
                      .shadowColor(R.color.colorPrimaryDark)
                      .textPadding(new Rect(5, 5, 5, 5))
                      .listener(new OnBMClickListener() {
                          @Override
                          public void onBoomButtonClick(int index) {
                              // When the boom-button corresponding this builder is clicked.
                              Intent it = new Intent(Home.this, Curriculum.class);
                              startActivity(it);
                          }
                      });

              bmb.addBuilder(builder);
          }
          else if (position==3)
          {
              TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                      .normalImageRes(R.drawable.ic_bq)
                      .rotateImage(false)
                      .rotateText(false)
                      .normalTextRes(R.string.bq)
                      .shadowEffect(true)
                      .shadowOffsetX(20)
                      .shadowOffsetY(0)
                      .shadowRadius(Util.dp2px(20))
                      .shadowCornerRadius(Util.dp2px(20))
                      .shadowColor(R.color.colorPrimaryDark)
                      .textPadding(new Rect(5, 5, 5, 5))
                      .listener(new OnBMClickListener() {
                          @Override
                          public void onBoomButtonClick(int index) {
                              // When the boom-button corresponding this builder is clicked.
                              Intent it = new Intent(Home.this, Curriculum.class);
                              startActivity(it);
                          }
                      });

              bmb.addBuilder(builder);
          }
          else if (position==4)
          {
              TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                      .normalImageRes(R.drawable.ic_lessons)
                      .rotateImage(false)
                      .rotateText(false)
                      .normalTextRes(R.string.lesson)
                      .shadowEffect(true)
                      .shadowOffsetX(20)
                      .shadowOffsetY(0)
                      .shadowRadius(Util.dp2px(20))
                      .shadowCornerRadius(Util.dp2px(20))
                      .shadowColor(R.color.colorPrimaryDark)
                      .textPadding(new Rect(5, 5, 5, 5))
                      .listener(new OnBMClickListener() {
                          @Override
                          public void onBoomButtonClick(int index) {
                              // When the boom-button corresponding this builder is clicked.
                              Intent it = new Intent(Home.this, Curriculum.class);
                              it.putExtra("parent","lessons");
                              startActivity(it);
                          }
                      });

              bmb.addBuilder(builder);
          }
          else if (position==5)

         {
              TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                      .normalImageRes(R.drawable.ic_totrank)
                      .rotateImage(false)
                      .rotateText(false)
                      .normalTextRes(R.string.totrank)
                      .shadowEffect(true)
                      .shadowOffsetX(20)
                      .shadowOffsetY(0)
                      .shadowRadius(Util.dp2px(20))
                      .shadowCornerRadius(Util.dp2px(20))
                      .shadowColor(R.color.colorPrimaryDark)
                      .textPadding(new Rect(5, 5, 5, 5))
                      .listener(new OnBMClickListener() {
                          @Override
                          public void onBoomButtonClick(int index) {
                              // When the boom-button corresponding this builder is clicked.
                              Intent it = new Intent(Home.this, tot_rank.class);
                              startActivity(it);
                          }
                      });


              bmb.addBuilder(builder);
          }
          else if (position==6)
          {
              TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                      .normalImageRes(R.drawable.ic_log_out)
                      .rotateImage(false)
                      .rotateText(false)
                      .normalTextRes(R.string.logout)
                      .shadowEffect(true)
                      .shadowOffsetX(20)
                      .shadowOffsetY(0)
                      .shadowRadius(Util.dp2px(20))
                      .shadowCornerRadius(Util.dp2px(20))
                      .shadowColor(R.color.colorPrimaryDark)
                      .textPadding(new Rect(5, 5, 5, 5))
                      .listener(new OnBMClickListener() {
                          @Override
                          public void onBoomButtonClick(int index) {
                              FirebaseAuth.getInstance().signOut();
                              Intent it = new Intent(Home.this, parent.class);
                              startActivity(it);
                          }
                      });

              bmb.addBuilder(builder);
          }
          else if (position==7)
          {
              TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                      .normalImageRes(R.drawable.ic_rateus)
                      .rotateImage(false)
                      .rotateText(false)
                      .normalTextRes(R.string.ratus)
                      .shadowEffect(true)
                      .shadowOffsetX(20)
                      .shadowOffsetY(0)
                      .shadowRadius(Util.dp2px(20))
                      .shadowCornerRadius(Util.dp2px(20))
                      .shadowColor(R.color.colorPrimaryDark)
                      .textPadding(new Rect(5, 5, 5, 5))
                      .textGravity(Gravity.CENTER)

                      .ellipsize(TextUtils.TruncateAt.MIDDLE)

                      .textSize(10)
                      .listener(new OnBMClickListener() {
                          @Override
                          public void onBoomButtonClick(int index) {
                              // When the boom-button corresponding this builder is clicked.
                              Intent it = new Intent(Home.this, Ratus.class);
                              startActivity(it);
                          }
                      });

              bmb.addBuilder(builder);
          }






        }


        btnguide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(Home.this, chat.class);
                it.putExtra("userid","l5eZsfdMjNXXw2wMyat4rEDz4SH3");
                startActivity(it);
            }
        });

        qb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(Home.this, Curriculum.class);
                it.putExtra("parent","QBank");
                startActivity(it);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(Home.this,Curriculum.class);
                it.putExtra("parent","Quiz");
                startActivity(it);
            }
        });
        lessons.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(Home.this, Curriculum.class);
                it.putExtra("parent","lessons");
                startActivity(it);
            }
        });


        //animation
        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atg2 = AnimationUtils.loadAnimation(this, R.anim.atg5);
        atg3 = AnimationUtils.loadAnimation(this, R.anim.atg3);
        atg4 = AnimationUtils.loadAnimation(this, R.anim.atg4);


        // pass an animation
        linearLayout.startAnimation(atg);
        imageView3.startAnimation(atg3);

        pagetitle.startAnimation(atg2);
        btnguide.startAnimation(atg2);
    }

}
