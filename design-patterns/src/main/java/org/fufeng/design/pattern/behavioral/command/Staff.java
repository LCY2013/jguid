package org.fufeng.design.pattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 17:16
 */
public class Staff {
    private List<Command> commandList = new ArrayList<>();
    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void executeCommand() {
        for (Command command : commandList) {
            command.execute();
        }
        commandList.clear();
    }
}
