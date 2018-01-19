package eus.ehu.tta.pbahillo002.aspie.presentation;


import java.io.Serializable;

import eus.ehu.tta.pbahillo002.aspie.model.Game1;
import eus.ehu.tta.pbahillo002.aspie.model.Game2;
import eus.ehu.tta.pbahillo002.aspie.model.Game3;
import eus.ehu.tta.pbahillo002.aspie.model.Result;

public class Data implements Serializable {
    public static final String DATA="eus.ehu.tta.pbahillo002.aspie.presentation.data";
    private Result result;
    private Game1 game1;
    private Game2 game2;
    private Game3 game3;
    private String login;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Game1 getGame1() {
        return game1;
    }

    public void setGame1(Game1 game1) {
        this.game1 = game1;
    }

    public Game2 getGame2() {
        return game2;
    }

    public void setGame2(Game2 game2) {
        this.game2 = game2;
    }

    public Game3 getGame3() {
        return game3;
    }

    public void setGame3(Game3 game3) {
        this.game3 = game3;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
