package eus.ehu.tta.pbahillo002.aspie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v7.widget.GridLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import eus.ehu.tta.pbahillo002.aspie.model.Game2;
import eus.ehu.tta.pbahillo002.aspie.model.RestLogic;
import eus.ehu.tta.pbahillo002.aspie.model.Result;
import eus.ehu.tta.pbahillo002.aspie.presentation.Data;
import eus.ehu.tta.pbahillo002.aspie.presentation.ProgressTask;

public class Game2Activity extends AppCompatActivity {
    private Data data;
    private int numclick=0;
    private int match=0;
    private Game2.Peer peer=new Game2.Peer();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        data=(Data)getIntent().getSerializableExtra(data.DATA);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.game2_grid);
        gridLayout.setColumnCount(data.getGame2().getNumColums());
        gridLayout.setRowCount(data.getGame2().getNumColums()+1);
        for(int i=0;i<data.getGame2().getCards().size();i++){
            ImageButton imageButton=new ImageButton(this);
            imageButton.setId(i);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f));
            params.width = 0;
            params.height=0;
            imageButton.setLayoutParams(params);
            Picasso.with(this).load(R.drawable.img).into(imageButton);
            imageButton.setBackground(null);
            imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int cardIndex=view.getId();
                    if (numclick==0){
                        peer.setCard1(data.getGame2().getCards().get(cardIndex));
                        numclick=1;
                        Picasso.with(Game2Activity.this).load(data.getGame2().getCards().get(cardIndex)).into((ImageButton)view);
                    }else if(numclick==1&&(!peer.getCard1().equals(data.getGame2().getCards().get(cardIndex)))) {
                        peer.setCard2(data.getGame2().getCards().get(cardIndex));
                        numclick=2;
                        Picasso.with(Game2Activity.this).load(data.getGame2().getCards().get(cardIndex)).into((ImageButton)view);
                        new ProgressTask<Integer>(Game2Activity.this,null){
                            @Override
                            protected Integer work() throws Exception {
                                TimeUnit.MILLISECONDS.sleep(1000);
                                numclick=0;
                                if (isPeer(data.getGame2().getPeers(),peer)){
                                    match++;
                                    if (match==data.getGame2().getPeers().size()){
                                        data.getResult().setPuntuacion(100);
                                        data.getResult().setClave(Integer.parseInt(data.getLogin()));
                                        data.getResult().setJuego(getResources().getString(R.string.game2));
                                        data.getResult().setFecha(Calendar.getInstance().getTime().toString());
                                        return 2;
                                    }
                                    return 1;
                                }else
                                    return 0;
                            }
                            @Override
                            protected void onFinish(Integer result){
                                switch (result){
                                    case 0:
                                        Picasso.with(Game2Activity.this).load(R.drawable.img).into((ImageButton)findViewById(data.getGame2().getCards().indexOf(peer.getCard1())));
                                        Picasso.with(Game2Activity.this).load(R.drawable.img).into((ImageButton)findViewById(data.getGame2().getCards().indexOf(peer.getCard2())));
                                        break;
                                    case 1:
                                        break;
                                    case 2:
                                        new ProgressTask<Boolean>(Game2Activity.this,getResources().getString(R.string.connecting)){

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
                                                Intent intent=new Intent(Game2Activity.this,ResultActivity.class);
                                                intent.putExtra(data.DATA,data);
                                                startActivity(intent);
                                            }
                                        }.execute();
                                        break;
                                }

                            }
                        }.execute();

                    }
                }
            });
            gridLayout.addView(imageButton);
        }
        data.setResult(new Result());
    }

    public boolean isPeer(List<Game2.Peer> peers, Game2.Peer peer){
        for (Game2.Peer p:peers) {
            if (p.getCard1().equals(peer.getCard1())&&p.getCard2().equals(peer.getCard2())||p.getCard1().equals(peer.getCard2())&&p.getCard2().equals(peer.getCard1()))
                return true;
        }
        return false;
    }


}
