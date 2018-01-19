package eus.ehu.tta.pbahillo002.aspie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import eus.ehu.tta.pbahillo002.aspie.model.RestLogic;
import eus.ehu.tta.pbahillo002.aspie.presentation.Data;
import eus.ehu.tta.pbahillo002.aspie.presentation.ProgressTask;

public class MainActivity extends AppCompatActivity {
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=new Data();
    }

    //TODO: La parte de la foto...
    public void login(View view){
        data.setLogin(((EditText)findViewById(R.id.login)).getText().toString());
        new ProgressTask<Boolean>(this){

            @Override
            protected Boolean work() throws Exception {
                RestLogic restLogic=new RestLogic();
                return restLogic.loginUser(data.getLogin());
            }

            @Override
            protected void onFinish(Boolean result) {
                if (result){
                    Intent intent=new Intent(this.context,MenuActivity.class);
                    intent.putExtra(data.DATA,data);
                    this.context.startActivity(intent);
                }else{
                    Toast.makeText(this.context,R.string.bad_login_text,Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
