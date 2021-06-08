import javax.swing.*;
import VKBot.VKBot;
import VKBot.VKConversations;
import com.intellij.openapi.ui.ComboBox;

public class Panel extends JPanel {
    JComboBox<VKConversations> peopleName;
    private VKBot bot;

    public Panel() {
        this.setLayout(null);
        this.setSize(300, 500);
        //Set ComboBox
        peopleName = new ComboBox<VKConversations>();
        add(peopleName);
        peopleName.setEditable(false);
        peopleName.setName("peopleName");
        peopleName.setBounds(15,5,getWidth()-30,30);

        //Set TextArea
        JTextArea message = new JTextArea();
        add(message);
        message.setName("message");
        message.setBounds(15,40,getWidth()-30,80);

        //Set Button
        JButton button = new JButton();
        add(button);
        button.setBounds(15,120,getWidth()-30,60);
        button.setText("Send");
        button.addActionListener(e -> {
            if (message.getText().length() > 0)
            {
                bot.sendMessage(peopleName.getItemAt(peopleName.getSelectedIndex()).GetPeerId(), message.getText());
            }
        });
        //https://oauth.vk.com/authorize?client_id=7872887&group_ids=203355401&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=messages&response_type=token&v=5.131
        bot = new VKBot("487dc93ecdedaa83a33c1e90b3c1cec63ef2d637d3aff95f7581abd2944bd54a018cede743781eb325c55");
        GetFriendsList();
    }



    private void GetFriendsList()
    {
        peopleName.setModel(new javax.swing.DefaultComboBoxModel<>(bot.getConversations()));
    }

}
