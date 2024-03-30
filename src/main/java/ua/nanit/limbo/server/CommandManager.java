package ua.nanit.limbo.server;

import ua.nanit.limbo.server.commands.ConnectionsCommand;
import ua.nanit.limbo.server.commands.HelpCommand;
import ua.nanit.limbo.server.commands.MemoryCommand;
import ua.nanit.limbo.server.commands.StopCommand;
import ua.nanit.limbo.server.commands.*;

import java.util.*;

public final class CommandManager extends Thread {

    private final Map<String, Command> commands = new HashMap<>();

    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(commands);
    }

    public Command getCommand(String name) {
        return commands.get(name.toLowerCase());
    }

    public void register(Command cmd, String... aliases) {
        for (String alias : aliases) {
            commands.put(alias.toLowerCase(), cmd);
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            try {
                command = scanner.nextLine().trim();
            } catch (NoSuchElementException e) {
                break;
            }

            Command handler = getCommand(command);

            if (handler != null) {
                try {
                    handler.execute();
                } catch (Throwable t) {
                    Logger.error("Cannot execute command:", t);
                }
                continue;
            }

            Logger.info("Unknown command. Type \"help\" to get a list of available commands.");
        }
    }

    public void registerAll(LimboServer server) {
        register(new CmdHelp(server), "help");
        register(new CmdConn(server), "conn");
        register(new CmdMem(), "mem");
        register(new CmdStop(), "stop");
        register(new CmdVersion(), "version", "ver");
        register("help", new HelpCommand(server));
        register("conn", new ConnectionsCommand(server));
        register("mem", new MemoryCommand());
        register("stop", new StopCommand());
    }
}
