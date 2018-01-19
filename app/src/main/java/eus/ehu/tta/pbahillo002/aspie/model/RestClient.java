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
    public void setHttpBasicAuth(String user, String passwd){
        String basicAuth= Base64.encodeToString(String.format("%s:%s",user,passwd).getBytes(),Base64.DEFAULT);
        properties.put(AUTH,String.format("Basic %s",basicAuth));
    }

    public String getAuthorization(){
        return properties.get(AUTH);
    }

    public void setAuthorization(String authorization){
        properties.put(AUTH,authorization);
    }

    public void setPropertiy(String name, String value){
        properties.put(name,value);
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
    public int postFile(String path, InputStream inputStream,String fileName)throws IOException{
        String boundary=Long.toString(System.currentTimeMillis());
        String newLine="\r\n";
        String prefix="--";
        HttpURLConnection connection=null;
        try {
            connection=getConnection(path);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            connection.setDoOutput(true);
            DataOutputStream outputStream=new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(newLine);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"" + fileName + "\"" + newLine);
            outputStream.writeBytes(newLine);
            byte[] data=new byte[1024*1024];
            int len;
            while((len=inputStream.read(data))>0)
                outputStream.write(data,0,len);
            outputStream.writeBytes(newLine);
            outputStream.writeBytes(prefix+boundary+prefix+newLine);
            outputStream.close();
            return connection.getResponseCode();
        }finally {
            if (connection!=null)
                connection.disconnect();
        }
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
