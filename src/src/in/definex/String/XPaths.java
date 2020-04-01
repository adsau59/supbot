package in.definex.String;

/**
 * Created by adam_ on 30-11-2017.
 */
public class XPaths {

    //last updated on 01/08/2019 (dd/mm//yyyy)

    //get the name of the group
    private static final String groupName = "//div[@class='_3TEwt']//span[contains(text(),\"%s%s\")]";
    //get the bubble of chat its currently in
    public static final String inMessageBubbles = "//div[contains(@class,'message-in')]//div[@class='Tkt2p']";

    //get the time relative from chat bubble
    public static final String bubbleToTime = ".//span[@class='_3EFt_']";
    //get the text relative from chat bubble
    public static final String bubbleToText = ".//span[contains(@class,'copyable-text')]";
    //get the author name relative from chat bubble (the bubble whihc has the author name)
    public static final String bubbleToAuthorName = ".//span[contains(@class,'_2a1Yw') or contains(@class,'_1OmDL')]";

    //get the input body of the chat
    public static final String inputBody = "//div[@class='_1Plpp']";
    //the send button when text is typed inside the input body
    public static final String sendButton = "//span[contains(@data-icon,'send')]";

    //get the group list item with the new chat
    public static final String newChatGroup = "//span[contains(@class,'OUeyt')]/../../../../../..//div[contains(@class, '_3TEwt')]";
    //get the chat bubbles after the "x unread messages"
    //send a message to the group, then enter this in console `setTimeout(function(){debugger;}, 5000)`,
    // then click on the group and wait for javascript to pause
    public static final String newChatBubbles = "//div[@class='_1mq8g']//following-sibling::div//div[@class='Tkt2p']";

    //get the reference (@Adam) from relative from the chat bubble
    public static final String getClientReferenceFromBubble = ".//span[contains(@class,\"matched-mention\")]";
    //attribute that gives the phonenumber from the reference
    public static final String referenceAttribute = "data-jid";

    //element that appears when the whatsapp web is started (after qr code is scanned)
    public static final String autoStartReady = "//div[@class=\"_2LKlu\"]";

    public static String getGroupNameXPath(String groupUID){
        return String.format(groupName, Strings.titlePrefix, groupUID);
    }

}
