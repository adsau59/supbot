package in.definex.NetworkJob;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import in.definex.Console.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * NetworkJobManager
 *
 * Manages a list of NetworkJob,
 * when a NetworkJob is requested, via add method a request string is created and sent to the service.
 * after the service returns a response, the NetworkJob with specified id is searched and its response is handled
 *
 * for more info, read NetworkJob.java
 *
 */

public class NetworkJobManager {


    private List<NetworkJob> jobList;
    private Thread networkThread;
    private static final int port = 12345;
    private ServerSocket serverSocket;

    private int responseByteSize = 1024;

    private static final String ID = "id";
    private static final String REQUEST = "request";
    private static final String RESPONSE = "response";

    /**
     * Constructor
     */
    public NetworkJobManager()
    {
        jobList = new ArrayList<>();
        networkThread = new Thread("NetworkJobThread"){
            @Override
            public void run() {
                startServer();
            }
        };
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change the default number of bytes(1024) for the incoming response.
     * @param responseByteSize size of bytes
     */
    public void setResponseByteSize(int responseByteSize) {
        this.responseByteSize = responseByteSize;
    }

    /**
     * add a Network job to manager.
     * @param job NetworkJob to add
     */
    public void add(NetworkJob job){
        jobList.add(job);
        sendRequest(job);
        if(jobList.size()==1)
            networkThread.start();
    }

    /**
     * Used to send Request to the server.
     * @param job Network job object
     */
    private void sendRequest(NetworkJob job)
    {
        try {
            Socket client = new Socket(job.getIp(), job.getPort());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(ID, jobList.indexOf(job));
            jsonObject.addProperty(REQUEST, job.requestString());
            out.write(jsonObject.toString().getBytes("utf-8"));
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the server if job list has any job, and quits if there are no longer any job in it.
     */
    private void startServer() {

        while(jobList.size() > 0) {
            try {
                Socket server = serverSocket.accept();
                DataInputStream in = new DataInputStream(server.getInputStream());
                byte[] array = new byte[responseByteSize];
                in.read(array);
                String response = new String(array, "utf-8").trim();
                JsonObject json = new JsonParser().parse(response).getAsJsonObject();

                responseJob(json.get(ID).getAsInt(), json.get(RESPONSE).getAsString());

                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

    }

    /**
     * Calls the onResponse of job, and removes it from the list.
     * @param id id of the job.
     * @param response response string from the service.
     */
    private void responseJob(int id, String response) {
        NetworkJob job = jobList.remove(id);
        job.onResponse(response);
    }


}
