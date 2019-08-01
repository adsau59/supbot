package in.definex.String;

/**
 * Created by adam_ on 30-11-2017.
 */
public class XPaths {

    //last updated on 01/08/2019 (dd/mm//yyyy)

    //get the name of the group
    private static final String groupName = "//div[@class='_3H4MS']//span[contains(text(),\"%s%s\")]";
    //get the bubble of chat its currently in
    public static final String inMessageBubbles = "//div[contains(@class,'message-in')]//div[@class='-N6Gq']";

    //get the time relative from chat bubble
    public static final String bubbleToTime = ".//span[@class='_3fnHB']";
    //get the text relative from chat bubble
    public static final String bubbleToText = ".//span[contains(@class,'copyable-text')]";
    //get the author name relative from chat bubble (the bubble whihc has the author name)
    public static final String bubbleToAuthorName = ".//span[@class='_1uQFN _18GNg' or @class='ZObjg']";

    //get the input body of the chat
    public static final String inputBody = "//div[@class='wjdTm']";
    //the send button when text is typed inside the input body
    public static final String sendButton = "//span[contains(@data-icon,'send')]";

    //get the group list item with the new chat
    public static final String newChatGroup = "//span[contains(@class,'P6z4j')]/../../../../../..//div[contains(@class, '_3H4MS')]";
    //get the chat bubbles after the "x unread messages"
    public static final String newChatBubbles = "//div[@class='_3Xx0y']//following-sibling::div//div[@class='-N6Gq']";
    //get the chat bubble before ethe "x unread messages"
    public static final String oldChatBubbles = "//div[@class='_3Xx0y']//preceding-sibling::div//div[@class='-N6Gq']";

    //get the reference (@Adam) from relative from the chat bubble
    public static final String getClientReferenceFromBubble = ".//span[contains(@class,\"matched-mention\")]";
    //attribute that gives the phonenumber from the reference
    public static final String referenceAttribute = "data-jid";

    //element that appears when the whatsapp web is started (after qr code is scanned)
    public static final String autoStartReady = "//h1[contains(@class,'_1cDWi')]";


    //deprecated
    public static final String ImageBubbles = "//div[@class=\"_3v3PK\"]/..//*[contains(text(), \"%s\")]/../../..";
    public static final String ImageFromImageBubble = ".//img";
    public static final String ImageDownloadIcon = "//span[@data-icon=\"download\"]";

    public static final String ClipIcon = "//span[@data-icon=\"clip\"]";
    public static final String ImageShareIcon = "//span[@data-icon=\"image\"]/..";
    public static final String ShareCaptionTextInput = "//div[contains(@class,\"CzI8E\")]//div[contains(@class,\"_2S1VP\")]";
    public static final String ShareSendIcon = "//span[@data-icon=\"send-light\"]";

    public static String getGroupNameXPath(String groupUID){
        return String.format(groupName, Strings.titlePrefix, groupUID);
    }

}
