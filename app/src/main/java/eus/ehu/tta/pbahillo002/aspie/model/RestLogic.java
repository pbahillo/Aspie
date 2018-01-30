
package eus.ehu.tta.pbahillo002.aspie.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class RestLogic implements RestServer {

    private RestClient client;
    private final static String restUrl="http://u017633.ehu.eus:28080/Aspie/rest/Aspie";
    private final static String contentUrl="http://github.com/pbahillo/AspieContent/raw/master/";


    public RestLogic(){

        client=new RestClient(restUrl);
    }


    @Override
    public Boolean loginUser(String login) {
        return true;
    }

    @Override
    public Game1 getJuego1(String login, int level) {
        Game1 game1=new Game1();
        try {
            JSONObject jsonObject=client.getJson("getJuego1/"+Integer.toString(level)+"/"+login);
            JSONArray array=jsonObject.getJSONArray("juego1");
            List<Game1.Test> tests=new ArrayList<>();
            for(int i=0;i<array.length();i++){
                JSONObject item=array.getJSONObject(i);
                Game1.Test test =new Game1.Test();
                test.setCorrect(item.getInt("correcta"));
                test.setLevel(item.getInt("nivel"));
                test.setUrlQuestion(contentUrl.concat((item.getString("url").split("\\|"))[0]));
                test.setTextQuestion((item.getString("url").split("\\|"))[1]);
                test.setUrlAnswer0(contentUrl.concat((item.getString("respuesta1").split("\\|"))[0]));
                test.setTextAnswer0((item.getString("respuesta1").split("\\|"))[1]);
                test.setUrlAnswer1(contentUrl.concat((item.getString("respuesta2").split("\\|"))[0]));
                test.setTextAnswer1((item.getString("respuesta2").split("\\|"))[1]);
                tests.add(test);
            }
            game1.setTests(tests);
            return game1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Game3 getJuego3(String login, int level) {
        Game3 game3=new Game3();
        try {
            JSONObject jsonObject=client.getJson("getJuego3/"+Integer.toString(level)+"/"+login);
            JSONArray array=jsonObject.getJSONArray("juego3");
            List<Game3.Test> tests=new ArrayList<>();
            for(int i=0;i<array.length();i++){
                JSONObject item=array.getJSONObject(i);
                Game3.Test test =new Game3.Test();
                test.setCorrect(item.getInt("correcta"));
                test.setLevel(item.getInt("nivel"));
                test.setUrl(contentUrl.concat(item.getString("url")));
                test.setAnswer0(item.getString("respuesta1"));
                test.setAnswer1(item.getString("respuesta2"));
                test.setAnswer2(item.getString("respuesta3"));
                tests.add(test);
            }
            game3.setTests(tests);
            return game3;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;    }

    @Override
    public Boolean addResult(Result result) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("clave",result.getClave());
            jsonObject.put("fecha",result.getFecha());
            jsonObject.put("juego",result.getJuego());
            jsonObject.put("puntuacion",result.getPuntuacion());
            int code=client.postJson(jsonObject,"addResult");
            if (code==HttpURLConnection.HTTP_OK||code==HttpURLConnection.HTTP_NO_CONTENT)
                return Boolean.TRUE;
            return Boolean.FALSE;
        } catch (JSONException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}

