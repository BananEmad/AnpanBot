package ed.anpanman.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;

import java.util.List;


import ed.anpanman.Model.currModel;
import ed.anpanman.R;

public class currAdapter extends PagerAdapter {
    private List<currModel> models;
    private LayoutInflater layoutInflater;
    private Context context;


    public currAdapter(List<currModel> models, Context context) {
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
        View view=layoutInflater.inflate(R.layout.curr_item,container,false);
         GifImageView gifImageView;
         TextView sub;

        gifImageView= view.findViewById(R.id.curr_gif);
        sub=view.findViewById(R.id.sub);

        gifImageView.setBytes(models.get(position).getImage());
        gifImageView.startAnimation();

        sub.setText(models.get(position).getName());

        container.addView(view,0);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
