package ed.anpanman;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Logo extends AppCompatActivity {
    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        gifImageView=findViewById(R.id.gifImageView);


        try{
            InputStream inputStream=getAssets().open("logogif.gif");
            byte[]bytes= IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (IOException io){

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Logo.this.startActivity(new Intent(Logo.this,parent.class));


                Logo.this.finish();

            }
        },4900);

    }
}
