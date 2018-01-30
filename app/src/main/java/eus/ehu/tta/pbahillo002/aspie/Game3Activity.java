package eus.ehu.tta.pbahillo002.aspie;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import eus.ehu.tta.pbahillo002.aspie.model.RestLogic;
import eus.ehu.tta.pbahillo002.aspie.presentation.Data;
import eus.ehu.tta.pbahillo002.aspie.presentation.ProgressTask;

public class Game3Activity extends AppCompatActivity {

    private int current;
    private Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        data=(Data) getIntent().getSerializableExtra(data.DATA);
        data.getResult().setPuntuacion(100);
        fillView();
    }

    public void check3(View view){
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.game3_radio);
        int selected=getSelected(radioGroup);
        radioGroup.getChildAt(data.getGame3().getTests().get(current-1).getCorrect()).setBackgroundColor(Color.GREEN);
        if (selected!=data.getGame3().getTests().get(current-1).getCorrect()){
            radioGroup.getChildAt(selected).setBackgroundColor(Color.RED);
            data.getResult().setPuntuacion(data.getResult().getPuntuacion()-25);
            Toast.makeText(getApplicationContext(),R.string.bad_answer,Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(),R.string.good_answer,Toast.LENGTH_SHORT).show();

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
                    data.getResult().setJuego(getResources().getString(R.string.game3));
                    new ProgressTask<Boolean>(Game3Activity.this,getResources().getString(R.string.connecting)){

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
                                Toast.makeText(getApplicationContext(),"ERROR", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Game3Activity.this,ResultActivity.class);
                            intent.putExtra(data.DATA,data);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                            finish();
                        }
                    }.execute();
                }
            }
        }.execute();


    }

    public void fillView() {
        Picasso.with(Game3Activity.this).load(data.getGame3().getTests().get(current).getUrl()).into((ImageView) findViewById(R.id.game3_image));
        findViewById(R.id.game3_radio_button_0).setBackgroundColor(Color.TRANSPARENT);
        findViewById(R.id.game3_radio_button_1).setBackgroundColor(Color.TRANSPARENT);
        findViewById(R.id.game3_radio_button_2).setBackgroundColor(Color.TRANSPARENT);

        ((RadioButton) findViewById(R.id.game3_radio_button_0)).setText(data.getGame3().getTests().get(current).getAnswer0());
        ((RadioButton) findViewById(R.id.game3_radio_button_1)).setText(data.getGame3().getTests().get(current).getAnswer1());
        ((RadioButton) findViewById(R.id.game3_radio_button_2)).setText(data.getGame3().getTests().get(current).getAnswer2());
        current++;
    }

    public int getSelected(RadioGroup radioGroup){
        for (int i=0;i<=radioGroup.getChildCount();i++)
            if(((RadioButton)radioGroup.getChildAt(i)).isChecked())
                return i;
        return -1;
    }
}
