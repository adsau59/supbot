package in.definex.ChatSystem;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {

    private List<Client> clients;

    public ClientManager(){
        clients = new ArrayList<>();
    }

    public void addClient(Client client){
        clients.add(client);
    }

    public Client findClientByName(String name){

        for(Client c: clients) {
            if (c.getName().equals(name))
                return c;
        }
        return Client.createTempAccount(name);
    }

}
