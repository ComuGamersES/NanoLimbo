package ru.nanit.limbo.server.commands;

import ru.nanit.limbo.server.Command;
import ru.nanit.limbo.server.LimboServer;
import ru.nanit.limbo.server.Logger;

public class ConnectionsCommand implements Command {

    private final LimboServer server;

    public ConnectionsCommand(LimboServer server) {
        this.server = server;
    }

    @Override
    public void execute() {
        Logger.info("Connections: %d", server.getConnections().getCount());
    }

    @Override
    public String description() {
        return "Display connections count";
    }
}
