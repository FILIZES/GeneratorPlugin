package org.filizes.generatorplugin.di.component;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.bukkit.plugin.Plugin;
import org.filizes.generatorplugin.Main;
import org.filizes.generatorplugin.Main_MembersInjector;
import org.filizes.generatorplugin.commands.CommandManager;
import org.filizes.generatorplugin.commands.CommandManager_Factory;
import org.filizes.generatorplugin.commands.GenerateCommands;
import org.filizes.generatorplugin.commands.GenerateCommands_Factory;
import org.filizes.generatorplugin.di.module.PluginModule;
import org.filizes.generatorplugin.di.module.PluginModule_ProvidePluginFactory;
import org.filizes.generatorplugin.generate.RoomsGenerator;
import org.filizes.generatorplugin.generate.RoomsGenerator_Factory;
import org.filizes.generatorplugin.listener.ListenerManager;
import org.filizes.generatorplugin.listener.ListenerManager_Factory;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerPluginComponent {
  private DaggerPluginComponent() {
  }

  public static PluginComponent.Factory factory() {
    return new Factory();
  }

  private static final class Factory implements PluginComponent.Factory {
    @Override
    public PluginComponent create(Main plugin) {
      Preconditions.checkNotNull(plugin);
      return new PluginComponentImpl(new PluginModule(), plugin);
    }
  }

  private static final class PluginComponentImpl implements PluginComponent {
    private final PluginComponentImpl pluginComponentImpl = this;

    private Provider<Main> pluginProvider;

    private Provider<Plugin> providePluginProvider;

    private Provider<RoomsGenerator> roomsGeneratorProvider;

    private Provider<GenerateCommands> generateCommandsProvider;

    private Provider<CommandManager> commandManagerProvider;

    private Provider<ListenerManager> listenerManagerProvider;

    private PluginComponentImpl(PluginModule pluginModuleParam, Main pluginParam) {

      initialize(pluginModuleParam, pluginParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final PluginModule pluginModuleParam, final Main pluginParam) {
      this.pluginProvider = InstanceFactory.create(pluginParam);
      this.providePluginProvider = DoubleCheck.provider(PluginModule_ProvidePluginFactory.create(pluginModuleParam, pluginProvider));
      this.roomsGeneratorProvider = DoubleCheck.provider(RoomsGenerator_Factory.create(providePluginProvider));
      this.generateCommandsProvider = DoubleCheck.provider(GenerateCommands_Factory.create(roomsGeneratorProvider));
      this.commandManagerProvider = DoubleCheck.provider(CommandManager_Factory.create(pluginProvider, generateCommandsProvider));
      this.listenerManagerProvider = DoubleCheck.provider(ListenerManager_Factory.create(pluginProvider, roomsGeneratorProvider));
    }

    @Override
    public void inject(Main plugin) {
      injectMain(plugin);
    }

    @CanIgnoreReturnValue
    private Main injectMain(Main instance) {
      Main_MembersInjector.injectCommandManager(instance, commandManagerProvider.get());
      Main_MembersInjector.injectListenerManager(instance, listenerManagerProvider.get());
      return instance;
    }
  }
}
