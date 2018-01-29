package eus.ehu.tta.pbahillo002.aspie.model;


import java.io.Serializable;
import java.util.List;

public class Game3 implements Serializable{

    private List<Test> tests;

    public static class Test implements Serializable{
        private String url;
        private String answer0;
        private String answer1;
        private String answer2;
        private int correct;
        private int level;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAnswer0() {
            return answer0;
        }

        public void setAnswer0(String answer0) {
            this.answer0 = answer0;
        }

        public String getAnswer1() {
            return answer1;
        }

        public void setAnswer1(String answer1) {
            this.answer1 = answer1;
        }

        public String getAnswer2() {
            return answer2;
        }

        public void setAnswer2(String answer2) {
            this.answer2 = answer2;
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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
