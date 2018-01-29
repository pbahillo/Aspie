package eus.ehu.tta.pbahillo002.aspie;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import eus.ehu.tta.pbahillo002.aspie.presentation.Data;

public class Game1Activity extends AppCompatActivity {
    private Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        data=(Data)getIntent().getSerializableExtra(data.DATA);

    }

    /*
    public void showVideo(String advice){
        VideoView videoView=new VideoView(this);
        videoView.setVideoURI(Uri.parse(advice));
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        videoView.setLayoutParams(params);
        MediaController mediaController=new MediaController(this){
            @Override
            public void hide(){
            }
            @Override
            public boolean dispatchKeyEvent(KeyEvent keyEvent){
                if(keyEvent.getKeyCode()==KeyEvent.KEYCODE_BACK)
                    finish();
                return super.dispatchKeyEvent(keyEvent);
            }
        };
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        viewGroup.addView(videoView);
        videoView.start();
    }
    */
}
