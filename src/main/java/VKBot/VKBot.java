package VKBot;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class VKBot {
    private int appId = 7872887;
    private String appVersion = "5.131";
    private int groupId = 203355401;
    private String accessToken;

    public VKBot(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public VKConversations[] getConversations()
    {
        String url = "https://api.vk.com/method/messages.getConversations?";
        url += "access_token=" + accessToken;
        url += "&v=" + appVersion;

        try
        {
            JSONObject resp = new JSONObject(CreateRequest(url)).getJSONObject("response");

            VKConversations[] conversations = new VKConversations[resp.getInt("count")];
            for (int i = 0; i < resp.getInt("count"); i++) {
                conversations[i] = new VKConversations(resp.getJSONArray("items").getJSONObject(i), this);
            }
            return conversations;
        }
        catch (JSONException e)
        {
            return new VKConversations[1];
        }
    }

    public VKPerson getPerson(int id)
    {
        String url = "https://api.vk.com/method/users.get?";
        url += "access_token=" + accessToken;
        url += "&v=" + appVersion;
        url += "&user_id=" + id;

        try
        {
            JSONObject resp = new JSONObject(CreateRequest(url)).getJSONArray("response").getJSONObject(0);

            int persId = resp.getInt("id");
            String Name = new String(resp.getString("first_name").getBytes(), StandardCharsets.UTF_8);
            String LastName = new String(resp.getString("last_name").getBytes(), StandardCharsets.UTF_8);

            return new VKPerson(persId, Name, LastName);
        }
        catch (Exception e)
        {
            return new VKPerson();
        }
    }

    public void sendMessage(int peerId, String message)
    {
        try {
            String url = "https://api.vk.com/method/messages.send?";
            url += "access_token=" + accessToken;
            url += "&v=" + appVersion;
            url += "&user_id=" + peerId;
            url += "&random_id=" + new Random().nextInt();
            url += "&message=" + URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
            CreateRequest(url);
        } catch (Exception ignored)
        {}
    }

    private @NotNull String CreateRequest(String url) {

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (IOException e)
        {
            return "IOException";
        }
    }
}
