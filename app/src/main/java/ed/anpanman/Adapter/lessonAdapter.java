package ed.anpanman.Adapter;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Util;


import java.util.ArrayList;
import java.util.List;

import ed.anpanman.LessonList;
import ed.anpanman.Model.lessonModel;
import ed.anpanman.QBank;
import ed.anpanman.R;
import ed.anpanman.Video;
import ed.anpanman.lessons;
import ed.anpanman.quiz;

public class lessonAdapter extends FirestoreRecyclerAdapter<lessonModel, lessonAdapter.lessonHolder> {
private List<Integer>images;
private  OnItemClickListner listner;
    public lessonAdapter(@NonNull FirestoreRecyclerOptions<lessonModel> options, List<Integer>img){

        super(options);
        images=img;
    }

    @Override
    protected void onBindViewHolder(@NonNull lessonHolder holder, int position, @NonNull lessonModel model) {

        holder.lessonImag.setImageResource(images.get(position));
        holder.lessonTitle.setText(model.getTitle());
    }

    @NonNull
    @Override
    public lessonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lessonitem,viewGroup,false);
       return new lessonHolder(v);

    }

    class lessonHolder extends RecyclerView.ViewHolder{

        BoomMenuButton bmb1;
        TextView lessonTitle;
        ImageView lessonImag;
        String Choice;
        public lessonHolder(@NonNull View itemView) {
            super(itemView);
            lessonImag=itemView.findViewById(R.id.less_img);
            lessonTitle=itemView.findViewById(R.id.less_title);
            bmb1=itemView.findViewById(R.id.bmb1);
            bmb1.setButtonEnum(ButtonEnum.Ham);
            for (int i = 0; i < bmb1.getPiecePlaceEnum().pieceNumber(); i++) {

                if(i==0){
                    HamButton.Builder builder = new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_lessons)
                            .rotateImage(false)
                            .normalTextRes(R.string.explenation)
                            .shadowEffect(true)
                            .shadowOffsetX(20)
                            .shadowOffsetY(0)
                            .shadowRadius(Util.dp2px(20))
                            .shadowCornerRadius(Util.dp2px(20))
                            .shadowColor(R.color.colorPrimaryDark)
                            .textPadding(new Rect(15, 5, 15, 5))
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    String Choice="lessons";
                                    final int position=getAdapterPosition();
                                    if(position!=RecyclerView.NO_POSITION&&listner!=null)
                                    {
                                        listner.onItemClick(getSnapshots().getSnapshot(position),position,Choice);
                                    }
                                 }
                            });

                    bmb1.addBuilder(builder);
                }
                else if(i==1){
                    HamButton.Builder builder = new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_quiz)
                            .rotateImage(false)
                            .normalTextRes(R.string.quiz)
                            .shadowEffect(true)
                            .shadowOffsetX(20)
                            .shadowOffsetY(0)
                            .shadowRadius(Util.dp2px(20))
                            .shadowCornerRadius(Util.dp2px(20))
                            .shadowColor(R.color.colorPrimaryDark)
                            .textPadding(new Rect(15, 5, 15, 5))
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    String Choice="Quiz";
                                    final int position=getAdapterPosition();
                                    if(position!=RecyclerView.NO_POSITION&&listner!=null)
                                    {
                                        listner.onItemClick(getSnapshots().getSnapshot(position),position,Choice);
                                    }
                                     }
                            });

                    bmb1.addBuilder(builder);
                }
                if(i==2){
                    HamButton.Builder builder = new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_bq)
                            .rotateImage(false)
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
                                    String Choice="QBank";
                                    final int position=getAdapterPosition();
                                    if(position!=RecyclerView.NO_POSITION&&listner!=null)
                                    {
                                        listner.onItemClick(getSnapshots().getSnapshot(position),position,Choice);
                                    }
                                                                         }
                            });

                    bmb1.addBuilder(builder);
                }
                if(i==3){
                    HamButton.Builder builder = new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_video)
                            .rotateImage(false)
                            .normalTextRes(R.string.video)
                            .shadowEffect(true)
                            .shadowOffsetX(20)
                            .shadowOffsetY(0)
                            .shadowRadius(Util.dp2px(20))
                            .shadowCornerRadius(Util.dp2px(20))
                            .shadowColor(R.color.colorPrimaryDark)
                            .textPadding(new Rect(15, 5, 15, 5))
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                   Choice="video";
                                    final int position=getAdapterPosition();
                                    if(position!=RecyclerView.NO_POSITION&&listner!=null)
                                    {
                                        listner.onItemClick(getSnapshots().getSnapshot(position),position,Choice);
                                    }
                                }
                            });

                    bmb1.addBuilder(builder);
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bmb1.boom();

                }
            });

        }
    }
    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot,int position,String choice);
    }
    public void setOnItemClickListener(OnItemClickListner listener){
      this.listner=listener;
    }
}
