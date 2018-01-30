package eus.ehu.tta.pbahillo002.aspie;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import eus.ehu.tta.pbahillo002.aspie.model.RestLogic;
import eus.ehu.tta.pbahillo002.aspie.presentation.Data;
import eus.ehu.tta.pbahillo002.aspie.presentation.ProgressTask;

public class Game1Activity extends AppCompatActivity {
    private int current;
    private Data data;
    MediaController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        data=(Data)getIntent().getSerializableExtra(data.DATA);
        current=0;
        VideoView videoView=(VideoView)findViewById(R.id.game1_question_video);
        controller=new MediaController(this){
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
        controller.setAnchorView(videoView);
        videoView.setMediaController(controller);
        fillView();
    }

    public void check(View view){
        switch (view.getId()){
            case R.id.game1_answer0_button:
                if (data.getGame1().getTests().get(current-1).getCorrect()==0){//Acierto
                    data.getResult().setPuntuacion(data.getResult().getPuntuacion()+25);
                    view.setBackgroundColor(Color.GREEN);
                }else
                    view.setBackgroundColor(Color.RED);
                break;
            case R.id.game1_answer1_button:
                if (data.getGame1().getTests().get(current-1).getCorrect()==1){//Acierto
                    data.getResult().setPuntuacion(data.getResult().getPuntuacion()+25);
                    view.setBackgroundColor(Color.GREEN);
                }
                else
                    view.setBackgroundColor(Color.RED);
                break;
        }
        new ProgressTask<Boolean>(this,null){
            @Override
            protected Boolean work() throws Exception {
                TimeUnit.MILLISECONDS.sleep(1000);
                return null;
            }

            @Override
            protected void onFinish(Boolean result) {
                if(current<4){
                    fillView();
                }else if (current==4){
                    data.getResult().setClave(Integer.parseInt(data.getLogin()));
                    data.getResult().setFecha(Calendar.getInstance().getTime().toString());
                    data.getResult().setJuego(getResources().getString(R.string.game1));
                    new ProgressTask<Boolean>(Game1Activity.this,getResources().getString(R.string.connecting)){

                        @Override
                        protected Boolean work() throws Exception {
                            RestLogic restLogic=new RestLogic();

                            return restLogic.addResult(data.getResult());
                        }

                        @Override
                        protected void onFinish(Boolean result) {
                            if(result)
                                Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Game1Activity.this,ResultActivity.class);
                            intent.putExtra(data.DATA,data);
                            startActivity(intent);
                        }
                    }.execute();
                }
            }
        }.execute();


    }

    public void fillView(){
        ((TextView)findViewById(R.id.game1_question_text)).setText(data.getGame1().getTests().get(current).getTextQuestion());
        ((VideoView)findViewById(R.id.game1_question_video)).setVideoURI(Uri.parse(data.getGame1().getTests().get(current).getUrlQuestion()));
        ((Button)findViewById(R.id.game1_answer0_button)).setText(data.getGame1().getTests().get(current).getTextAnswer0());
        findViewById(R.id.game1_answer0_button).setBackgroundColor(Color.GRAY);
        ((Button)findViewById(R.id.game1_answer1_button)).setText(data.getGame1().getTests().get(current).getTextAnswer1());
        findViewById(R.id.game1_answer1_button).setBackgroundColor(Color.GRAY);
        Picasso.with(this).load(data.getGame1().getTests().get(current).getUrlAnswer0()).resize(R.dimen.game_2_width_easy,R.dimen.game_2_heigh_easy).into((ImageView)findViewById(R.id.game1_answer0_image));
        Picasso.with(this).load(data.getGame1().getTests().get(current).getUrlAnswer1()).resize(R.dimen.game_2_width_easy,R.dimen.game_2_heigh_easy).into((ImageView)findViewById(R.id.game1_answer1_image));
        current++;
    }
}
