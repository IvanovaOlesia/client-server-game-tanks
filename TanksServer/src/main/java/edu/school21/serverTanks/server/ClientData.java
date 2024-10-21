package edu.school21.serverTanks.server;

public class ClientData {
    private String action;
    private Integer id;

    public ClientData(String action, Integer id) {
        this.action = action;
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
