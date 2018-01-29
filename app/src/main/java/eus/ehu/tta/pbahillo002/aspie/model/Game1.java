package eus.ehu.tta.pbahillo002.aspie.model;


import java.io.Serializable;

public class Game1 implements Serializable{
    private String textQuestion;
    private String textAnswer0;
    private String textAnswer1;
    private String urlQuestion;
    private String urlAnswer0;
    private String urlAnswer1;
    private int correct;
    private int level;

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public String getTextAnswer0() {
        return textAnswer0;
    }

    public void setTextAnswer0(String textAnswer0) {
        this.textAnswer0 = textAnswer0;
    }

    public String getTextAnswer1() {
        return textAnswer1;
    }

    public void setTextAnswer1(String textAnswer1) {
        this.textAnswer1 = textAnswer1;
    }

    public String getUrlQuestion() {
        return urlQuestion;
    }

    public void setUrlQuestion(String urlQuestion) {
        this.urlQuestion = urlQuestion;
    }

    public String getUrlAnswer0() {
        return urlAnswer0;
    }

    public void setUrlAnswer0(String urlAnswer0) {
        this.urlAnswer0 = urlAnswer0;
    }

    public String getUrlAnswer1() {
        return urlAnswer1;
    }

    public void setUrlAnswer1(String urlAnswer1) {
        this.urlAnswer1 = urlAnswer1;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
