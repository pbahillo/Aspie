package eus.ehu.tta.pbahillo002.aspie.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Game2 {
    private class Peer{
        private String card1;
        private String card2;

        public String getCard1() {
            return card1;
        }

        public void setCard1(String card1) {
            this.card1 = card1;
        }

        public String getCard2() {
            return card2;
        }

        public void setCard2(String card2) {
            this.card2 = card2;
        }
    }

    private List<String> cards;
    private List<Peer> peers;
    private int numclick=0;
    private int match=0;
    private Peer peer;

    public Game2(int level){
        peers=new ArrayList<>();
        cards=new ArrayList<>();
        peer=new Peer();
        switch(level){
            case 0:
                for(int i=0;i<6;i++){
                    Peer peer=new Peer();
                    peer.setCard1("easy/img"+i+"0.jpeg");
                    peer.setCard2("easy/img"+i+"1.jpeg");
                    if (i==2||i==3)
                        peer.setCard2("easy/img"+i+"1.png");
                    peers.add(peer);
                    cards.add(peer.getCard1());
                    cards.add(peer.getCard2());
                }
                Collections.shuffle(cards);
                break;
            case 1:
                for(int i=0;i<10;i++){
                    Peer peer=new Peer();
                    peer.setCard1("hard/img"+i+"0.jpeg");
                    peer.setCard2("hard/img"+i+"1.png");
                    peers.add(peer);
                    cards.add(peer.getCard1());
                    cards.add(peer.getCard2());
                }
                Collections.shuffle(cards);
                break;
            default:
                break;
        }
    }


    public void choose(int cardIndex){
        if (numclick==0){
            peer.setCard1(cards.get(cardIndex));
            numclick=1;
            //TODO cahnge the view
        }else if(numclick==1&&(!peer.getCard1().equals(cards.get(cardIndex)))) {
            peer.setCard2(cards.get(cardIndex));
            numclick=2;
            //TODO change the view
            Timer timer=new Timer();
            TimerTask timerTask=new TimerTask() {
                @Override
                public void run() {
                    numclick=0;
                    if (isPeer(peer)){
                        match++;
                        if (match==peers.size()){
                            //TODO the result part
                        }
                    }
                }
            };timer.schedule(timerTask,750);
        }
    }


    private boolean isPeer(Peer paramPeer){
        for (Peer peer:peers) {
            if (peer.equals(paramPeer))
                return true;
        }
        return false;
    }
}
