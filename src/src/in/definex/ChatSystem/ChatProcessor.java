package in.definex.ChatSystem;


/**
 * ChatProcessor
 * Abstract class.
 *
 * Used to create a ChatProcessor which is used to process the chat sent by user.
 */
public abstract class ChatProcessor {

    private boolean multithreaded;

    /**
     * Constructor
     * @param multithreaded set to true if want to use multithreading.
     */
    public ChatProcessor(boolean multithreaded) {
        this.multithreaded = multithreaded;
    }

    /**
     * Called by Chatgroup manager to process the chat
     *
     * @param bubble whatsapp chat bubble
     * @param client client who sent the chat
     */
    void process(Bubble bubble, Client client){

        if(multithreaded)
            new Thread(this.getClass().getName()+" Thread"){
                @Override
                public void run() {
                    task(bubble,client);
                }
            }.start();
        else
            task(bubble,client);
    }

    /**
     * override in implementation
     * @param bubble whatsapp chat bubble
     * @param client client who sent the chat
     */
    public abstract void task(Bubble bubble, Client client);

}
