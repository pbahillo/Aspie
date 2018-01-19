
package eus.ehu.tta.pbahillo002.aspie.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;


public class RestLogic implements RestServer {

    private RestClient client;
    private final static String restUrl="http://u017633.ehu.eus:28080/ServidorTta/rest/tta";


    public RestLogic(){

        client=new RestClient(restUrl);
    }


    @Override
    public Boolean loginUser(String login) {
        return true;
    }

    @Override
    public Game1 getJuego1(String login, int level) {
        return null;
    }

    @Override
    public Game3 getJuego3(String login, int level) {
        return null;
    }

    @Override
    public void addResult(Result result) {

    }
}

