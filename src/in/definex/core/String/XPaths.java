package in.definex.core.String;

/**
 * Created by adam_ on 30-11-2017.
 */
public class XPaths {

    private static final String groupName = "//div[@class='chatlist-panel-body']//div[@class='chat-title']/span[@class='emojitext ellipsify'][contains(text(),\";;%s\")]";
    public static final String inMessageBubbles = "//div[contains(@class,'message-in')]//div[contains(concat(' ', normalize-space(@class), ' '), ' bubble ')]";

    public static final String bubbleToTime = ".//div[contains(@class,'_1DZAH')]";
    public static final String bubbleToText = ".//span[contains(@class,'copyable-text') and contains(@class, 'emojitext')]";
    public static final String bubbleToAuthorName = ".//div[contains(@class, '_111ze')]//span[not(contains(@class,'ellipsify'))]";

    public static final String inputBody = "//div[contains(@class,'pluggable-input-body')]";
    public static final String sendButton = "//span[contains(@data-icon,'send')]";

    public static final String newChatGroup = "//div[contains(@class,'_15G96')]/../../../../../../div[not(contains(@class,'active'))]//span[@class='emojitext ellipsify'][contains(text(),\"\")]";
    public static final String newChatBubbles = "//div[@class='_1mq8g']//following-sibling::div//div[contains(concat(' ', normalize-space(@class), ' '), ' bubble ')]";
    public static final String oldChatBubbles = "//div[@class='_1mq8g']//preceding-sibling::div//div[contains(concat(' ', normalize-space(@class), ' '), ' bubble ')]";

    public static final String getClientReferenceFromBubble = ".//span[contains(@class,\"matched-mention\")]";
    public static final String referenceAttribute = "data-jid";

    public static final String autoStartReady = "//h1[contains(@class,'iHhHL')]";

    public static String getGroupNameXPath(String groupUID){
        return String.format(groupName, groupUID);
    }

}
