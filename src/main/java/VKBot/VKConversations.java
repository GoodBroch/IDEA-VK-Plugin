package VKBot;

import org.json.JSONException;
import org.json.JSONObject;

public class VKConversations {
    private int peerId;
    private String type;
    private VKPerson user;

    public VKConversations()
    {
        peerId = 1;
        type = "Error";
        user = new VKPerson();
    }

    public VKConversations(JSONObject obj, VKBot creator) throws JSONException
    {
        JSONObject conversation = obj.getJSONObject("conversation");
        JSONObject last_message = obj.getJSONObject("last_message");

        JSONObject peer = conversation.getJSONObject("peer");
        peerId = peer.getInt("id");
        type = peer.getString("type");

        if (type.equals("user"))
            user = creator.getPerson(peerId);
        else
            user = new VKPerson();
    }

    public int GetPeerId()
    {
        return peerId;
    }
    public String GetType()
    {
        return type;
    }
    public String toString()
    {
        return user.toString();
    }
}
