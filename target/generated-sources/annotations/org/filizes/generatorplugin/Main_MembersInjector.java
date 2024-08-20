package org.filizes.generatorplugin;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.filizes.generatorplugin.commands.CommandManager;
import org.filizes.generatorplugin.listener.ListenerManager;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class Main_MembersInjector implements MembersInjector<Main> {
  private final Provider<CommandManager> commandManagerProvider;

  private final Provider<ListenerManager> listenerManagerProvider;

  public Main_MembersInjector(Provider<CommandManager> commandManagerProvider,
      Provider<ListenerManager> listenerManagerProvider) {
    this.commandManagerProvider = commandManagerProvider;
    this.listenerManagerProvider = listenerManagerProvider;
  }

  public static MembersInjector<Main> create(Provider<CommandManager> commandManagerProvider,
      Provider<ListenerManager> listenerManagerProvider) {
    return new Main_MembersInjector(commandManagerProvider, listenerManagerProvider);
  }

  @Override
  public void injectMembers(Main instance) {
    injectCommandManager(instance, commandManagerProvider.get());
    injectListenerManager(instance, listenerManagerProvider.get());
  }

  @InjectedFieldSignature("org.filizes.generatorplugin.Main.commandManager")
  public static void injectCommandManager(Main instance, CommandManager commandManager) {
    instance.commandManager = commandManager;
  }

  @InjectedFieldSignature("org.filizes.generatorplugin.Main.listenerManager")
  public static void injectListenerManager(Main instance, ListenerManager listenerManager) {
    instance.listenerManager = listenerManager;
  }
}
