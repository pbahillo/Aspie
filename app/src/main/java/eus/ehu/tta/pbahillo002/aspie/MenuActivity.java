package eus.ehu.tta.pbahillo002.aspie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import eus.ehu.tta.pbahillo002.aspie.presentation.Data;

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
                        Intent intent=new Intent(MenuActivity.this,Game1Activity.class);
                        intent.putExtra(data.DATA,data);
                        startActivity(intent);
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
                        Intent intent=new Intent(MenuActivity.this,Game2Activity.class);
                        intent.putExtra(data.DATA,data);
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
                        Intent intent=new Intent(MenuActivity.this,Game3Activity.class);
                        intent.putExtra(data.DATA,data);
                        startActivity(intent);
                        Toast.makeText(MenuActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }


}
