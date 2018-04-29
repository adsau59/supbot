package in.definex.NetworkJob;


/**
 * NetworkJob
 *
 * NetworkJob helps requesting services through networking.
 * It creates a response string and by the help of NetworkJobManager,
 * sends it to the service inform json:
 *
 * {
 *     "id":23,
 *     "request":"this is a request string"
 * }
 *
 * where id is an integer that shows for which job in the list that the NetworkJobManager the request belongs,
 * which have to be included in the response.
 *
 * response should be of format:
 *
 * {
 *     "id":23,
 *     "response":"this is a response string"
 * }
 *
 * After a response is returned, the response is processed by NetworkJob.
 *
 */
public abstract class NetworkJob {

    private String ip;
    private int port;

    /**
     * Constructor
     *
     * @param ip IP to connect for requesting service
     * @param port Port to connect for requesting service
     */
    public NetworkJob(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * Getters
     */
    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }


    /**
     * Called to create a request string.
     * @return request string.
     */
    protected abstract String requestString();

    /**
     * Called when repose is received from the service.
     * @param response
     */
    protected abstract void onResponse(String response);

}
