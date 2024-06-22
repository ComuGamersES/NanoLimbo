package ua.nanit.limbo.server.commands;

import ua.nanit.limbo.server.Command;
import ua.nanit.limbo.server.LimboServer;
import ua.nanit.limbo.server.Log;

public class ConnectionsCommand implements Command {

    private final LimboServer server;

    public ConnectionsCommand(LimboServer server) {
        this.server = server;
    }

    @Override
    public void execute() {
        Log.info("Connections: %d", server.getConnections().getCount());
    }

    @Override
    public String description() {
        return "Display connections count";
    }
}
