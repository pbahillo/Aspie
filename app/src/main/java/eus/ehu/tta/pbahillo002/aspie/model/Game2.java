package eus.ehu.tta.pbahillo002.aspie.model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import eus.ehu.tta.pbahillo002.aspie.R;


public class Game2 implements Serializable{
    public static class Peer implements Serializable{
        private Integer card1;
        private Integer card2;

        public Integer getCard1() {
            return card1;
        }

        public void setCard1(Integer card1) {
            this.card1 = card1;
        }

        public Integer getCard2() {
            return card2;
        }

        public void setCard2(Integer card2) {
            this.card2 = card2;
        }
    }

    private int numColums;
    private List<Integer> cards;
    private List<Peer> peers;


    public Game2(int level,Context context){
        peers=new ArrayList<>();
        cards=new ArrayList<>();
        switch(level){
            case 0:
                numColums=3;
                for(int i=0;i<6;i++){
                    Peer peer=new Peer();
                    peer.setCard1(context.getResources().getIdentifier("img0" + i + "0", "drawable", context.getPackageName()));
                    if (i==2||i==3)
                        peer.setCard2(context.getResources().getIdentifier("img0" + i + "1", "drawable", context.getPackageName()));
                    else
                        peer.setCard2(context.getResources().getIdentifier("img0" + i + "1", "drawable", context.getPackageName()));
                    peers.add(peer);
                    cards.add(peer.getCard1());
                    cards.add(peer.getCard2());
                }
                Collections.shuffle(cards);
                break;
            case 1:
                numColums=4;
                for(int i=0;i<10;i++){
                    Peer peer=new Peer();
                    peer.setCard1(context.getResources().getIdentifier("img1" + i + "0", "drawable", context.getPackageName()));
                    peer.setCard2(context.getResources().getIdentifier("img1" + i + "1", "drawable", context.getPackageName()));
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

    public int getNumColums() {
        return numColums;
    }

    public void setNumColums(int numColums) {
        this.numColums = numColums;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    public List<Peer> getPeers() {
        return peers;
    }

    public void setPeers(List<Peer> peers) {
        this.peers = peers;
    }


}
