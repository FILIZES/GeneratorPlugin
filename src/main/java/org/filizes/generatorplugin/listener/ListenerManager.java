package org.filizes.generatorplugin.listener;

import org.bukkit.plugin.PluginManager;
import org.filizes.generatorplugin.Main;
import org.filizes.generatorplugin.generate.RoomsGenerator;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ListenerManager {

    @Inject
    public ListenerManager(Main plugin,RoomsGenerator roomsGenerator) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(roomsGenerator, plugin);
    }
}
