package eus.ehu.tta.pbahillo002.aspie.model;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    private final static String AUTH="Authorization";
    private final String baseURL;
    private final Map<String,String> properties=new HashMap<>();

    public RestClient(String baseURL){
        this.baseURL=baseURL;
    }

    public HttpURLConnection getConnection(String path) throws IOException {
        URL url=new URL(String.format("%s/%s",baseURL,path));
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        for (Map.Entry<String,String> property:properties.entrySet())
            connection.setRequestProperty(property.getKey(),property.getValue());
        connection.setUseCaches(false);
        return connection;
    }
    public String getString(String path)throws IOException{
        HttpURLConnection connection=null;
        try{
            connection=getConnection(path);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            try {
                return bufferedReader.readLine();
            }finally {
                bufferedReader.close();
            }
        }finally {
            if (connection!=null)
                connection.disconnect();
        }
    }
    JSONObject getJson(String path)throws IOException,JSONException{
        return new JSONObject(getString(path));

    }

    public int postJson(final JSONObject jsonObject, String path)throws IOException{
        HttpURLConnection connection=null;
        try {
            connection=getConnection(path);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            PrintWriter printWriter=new PrintWriter(connection.getOutputStream());
            try {
                printWriter.print(jsonObject.toString());
                return connection.getResponseCode();
            }finally {
                printWriter.close();
            }
        }finally {
            if(connection!=null)
                connection.disconnect();
        }
    }
}
