package eus.ehu.tta.pbahillo002.aspie.model;


import java.io.InputStream;

interface RestServer {
    Boolean loginUser(String login);
    Game1 getJuego1(String login,int level);
    Game3 getJuego3(String login,int level);
    void addResult(Result result);
}
