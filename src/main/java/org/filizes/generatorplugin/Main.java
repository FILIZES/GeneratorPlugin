package org.filizes.generatorplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.filizes.generatorplugin.commands.CommandManager;
import org.filizes.generatorplugin.di.component.DaggerPluginComponent;
import org.filizes.generatorplugin.listener.ListenerManager;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public final class Main extends JavaPlugin {

    @Inject
    CommandManager commandManager;

    @Inject
    ListenerManager listenerManager;

    @Override
    public void onEnable() {

        DaggerPluginComponent.factory()
                .create(this)
                .inject(this);

        saveDefaultConfig();
        getLogger().info("RoomsGenerator Запущен!.");

    }

    @Override
    public void onDisable() {

        getLogger().info("RoomsGenerator Выключен.");

    }
}
