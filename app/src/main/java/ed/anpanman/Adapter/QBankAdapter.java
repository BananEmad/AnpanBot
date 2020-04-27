package ed.anpanman.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ed.anpanman.Model.QBank_model;
import ed.anpanman.R;

public class QBankAdapter extends PagerAdapter {

    private List<QBank_model>models;
    private LayoutInflater layoutInflater;
    private Context context;

    public QBankAdapter(List<QBank_model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.qb_item,container,false);
        ImageView imageView;
        TextView question,answer;

        imageView= view.findViewById(R.id.num_Image);
        question=view.findViewById(R.id.ques);
        answer=view.findViewById(R.id.answer);

        imageView.setImageResource(models.get(position).getImage());
        question.setText(models.get(position).getQuestion());
        answer.setText(models.get(position).getAnswer());

        container.addView(view,0);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
