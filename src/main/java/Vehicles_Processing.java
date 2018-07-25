import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class Vehicles_Processing
{
    public String buildJsonString(String val1, String val2, String val3, String val4, String val5)
    {
        String jsonStr = "";

        JSONObject jsonObj = new JSONObject();

        //TODO: Schalk change the FieldName to the name of the json field that needs to be posted to.
        jsonObj.put("FieldName", val1);
        jsonObj.put("FieldName", val2);
        jsonObj.put("FieldName", val3);
        jsonObj.put("FieldName", val4);
        jsonObj.put("FieldName", val5);

        jsonStr = jsonObj.toString();

        return jsonStr;
    }

    private void copy(InputStream in, OutputStream out) throws IOException {
        synchronized (in) {
            synchronized (out) {

                byte[] buffer = new byte[256];
                while (true) {
                    int bytesRead = in.read(buffer);
                    if (bytesRead == -1) {
                        break;
                    }
                    out.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    public void postVehicle(String jsonStr)
    {
        DataOutputStream dataOut = null;
        BufferedReader in = null;

        try
        {
            String url = "Put the URL HERE"; //TODO: Schalk
            URL urlObj = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();

            ByteArrayInputStream bin = new ByteArrayInputStream(jsonStr.getBytes());
            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            copy(bin, bout);

            byte[] b = bout.toByteArray();

            connection.setRequestProperty("Content-Length", String.valueOf(b.length));
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("x-csrf-token", "Put the token here"); //TODO: Schalk
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream w = connection.getOutputStream();
            w.write(b);
            w.close();

            int responseCode = connection.getResponseCode();
            System.out.println(responseCode); //If 201 then record created successfully

            connection.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (dataOut != null)
                {
                    dataOut.close();
                }
                if (in != null)
                {
                    in.close();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
