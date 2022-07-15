package ru.nanit.limbo.server.commands;

import ru.nanit.limbo.server.Command;

public class StopCommand implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String description() {
        return "Stop the server";
    }
}
