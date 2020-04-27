package ed.anpanman;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoplayer extends AppCompatActivity {

VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        Bundle bundle=getIntent().getExtras();
        String fileName=bundle.get("name").toString();
        String filePath="android.resource://"+getPackageName()+"/raw/"+fileName;

        videoView=findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse(filePath));
        videoView.setMediaController(new MediaController(this));
        videoView.start();

    }
}
