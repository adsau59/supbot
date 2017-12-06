package in.definex.core;

import org.openqa.selenium.WebElement;

/**
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
