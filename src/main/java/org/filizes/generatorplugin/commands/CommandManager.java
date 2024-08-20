package org.filizes.generatorplugin.commands;

import org.filizes.generatorplugin.Main;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CommandManager {

    @Inject
    public CommandManager(Main main, GenerateCommands generateCommands) {
        main.getCommand("generaterooms").setExecutor(generateCommands);
        main.getCommand("clearroom").setExecutor(generateCommands);
    }
}
