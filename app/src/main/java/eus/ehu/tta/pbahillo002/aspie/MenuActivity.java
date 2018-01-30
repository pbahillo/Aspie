package eus.ehu.tta.pbahillo002.aspie;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import eus.ehu.tta.pbahillo002.aspie.model.Game1;
import eus.ehu.tta.pbahillo002.aspie.model.Game2;
import eus.ehu.tta.pbahillo002.aspie.model.Game3;
import eus.ehu.tta.pbahillo002.aspie.model.RestLogic;
import eus.ehu.tta.pbahillo002.aspie.model.Result;
import eus.ehu.tta.pbahillo002.aspie.presentation.Data;
import eus.ehu.tta.pbahillo002.aspie.presentation.ProgressTask;

public class MenuActivity extends AppCompatActivity {
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        data=(Data)getIntent().getSerializableExtra(data.DATA);
        String text=getResources().getString(R.string.text_welcome).concat(data.getLogin());
        TextView textView=(TextView)findViewById(R.id.menu_text);
        textView.setText(text);
        final Button buttonGame1 =(Button)findViewById(R.id.button_game1);
        buttonGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(MenuActivity.this,buttonGame1);
                popupMenu.getMenuInflater().inflate(R.menu.level_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem menuItem){
                        switch (menuItem.getItemId()){
                            case R.id.radio_easy:
                                new ProgressTask<Game1>(MenuActivity.this,getResources().getString(R.string.connecting)){
                                    @Override
                                    protected Game1 work() throws Exception {
                                        RestLogic restLogic=new RestLogic();
                                        data.setGame1(restLogic.getJuego1(data.getLogin(),0));
                                        data.setResult(new Result());
                                        return data.getGame1();
                                    }
                                    @Override
                                    protected void onFinish(Game1 result) {
                                        Intent intent=new Intent(MenuActivity.this,Game1Activity.class);
                                        intent.putExtra(data.DATA,data);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                    }
                                }.execute();
                                break;
                            case R.id.radio_hard:
                                new ProgressTask<Game1>(MenuActivity.this,getResources().getString(R.string.connecting)){
                                    @Override
                                    protected Game1 work() throws Exception {
                                        RestLogic restLogic=new RestLogic();
                                        data.setGame1(restLogic.getJuego1(data.getLogin(),1));
                                        data.setResult(new Result());
                                        return data.getGame1();
                                    }
                                    @Override
                                    protected void onFinish(Game1 result) {
                                        Intent intent=new Intent(MenuActivity.this,Game1Activity.class);
                                        intent.putExtra(data.DATA,data);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                    }
                                }.execute();
                                break;
                        }
                        Toast.makeText(MenuActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        final Button buttonGame2 =(Button)findViewById(R.id.button_game2);
        buttonGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(MenuActivity.this,buttonGame2);
                popupMenu.getMenuInflater().inflate(R.menu.level_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem menuItem){
                        switch (menuItem.getItemId()){
                            case R.id.radio_easy:
                                data.setGame2(new Game2(0,getApplicationContext()));
                                data.setResult(new Result());
                                break;
                            case R.id.radio_hard:
                                data.setGame2(new Game2(1,getApplicationContext()));
                                data.setResult(new Result());
                                break;
                        }
                        Intent intent=new Intent(MenuActivity.this,Game2Activity.class);
                        intent.putExtra(data.DATA,data);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                        Toast.makeText(MenuActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        final Button buttonGame3 =(Button)findViewById(R.id.button_game3);
        buttonGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(MenuActivity.this,buttonGame3);
                popupMenu.getMenuInflater().inflate(R.menu.level_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem menuItem){
                        switch (menuItem.getItemId()){
                            case R.id.radio_easy:
                                new ProgressTask<Game3>(MenuActivity.this,getResources().getString(R.string.connecting)){
                                    @Override
                                    protected Game3 work() throws Exception {
                                        RestLogic restLogic=new RestLogic();
                                        data.setGame3(restLogic.getJuego3(data.getLogin(),0));
                                        data.setResult(new Result());
                                        return data.getGame3();
                                    }
                                    @Override
                                    protected void onFinish(Game3 result) {
                                        Intent intent=new Intent(MenuActivity.this,Game3Activity.class);
                                        intent.putExtra(data.DATA,data);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                    }
                                }.execute();
                                break;
                            case R.id.radio_hard:
                                new ProgressTask<Game3>(MenuActivity.this,getResources().getString(R.string.connecting)){
                                    @Override
                                    protected Game3 work() throws Exception {
                                        RestLogic restLogic=new RestLogic();
                                        data.setGame3(restLogic.getJuego3(data.getLogin(),1));
                                        data.setResult(new Result());
                                        return data.getGame3();
                                    }
                                    @Override
                                    protected void onFinish(Game3 result) {
                                        Intent intent=new Intent(MenuActivity.this,Game3Activity.class);
                                        intent.putExtra(data.DATA,data);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                    }
                                }.execute();
                                break;
                        }
                        Toast.makeText(MenuActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
    /*
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    */

}
