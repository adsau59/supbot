package in.definex.ChatSystem;

import org.openqa.selenium.WebElement;

/**
 * ChatItem
 * Abstracts the client that sent the message,
 * time they sent and the webelelement of the chat bubble
 *
 * Created by adam_ on 30-11-2017.
 */
public class ChatItem {
    Client client;
    String time;
    WebElement bubble;

    public ChatItem(Client client, String time, WebElement bubble) {
        this.client = client;
        this.time = time;
        this.bubble = bubble;
    }
}
