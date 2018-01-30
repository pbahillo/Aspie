package eus.ehu.tta.pbahillo002.aspie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import eus.ehu.tta.pbahillo002.aspie.presentation.Data;

public class ResultActivity extends AppCompatActivity {

    private Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        data=(Data)getIntent().getSerializableExtra(data.DATA);
        ((TextView)findViewById(R.id.text_result)).setText("Resultado: "+data.getResult().getPuntuacion());
    }
    public void back(View view){
        Intent intent=new Intent(ResultActivity.this,MenuActivity.class);
        intent.putExtra(data.DATA,data);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}

