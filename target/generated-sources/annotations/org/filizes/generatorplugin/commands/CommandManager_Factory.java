package org.filizes.generatorplugin.commands;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.filizes.generatorplugin.Main;

@ScopeMetadata("javax.inject.Singleton")
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
public final class CommandManager_Factory implements Factory<CommandManager> {
  private final Provider<Main> mainProvider;

  private final Provider<GenerateCommands> generateCommandsProvider;

  public CommandManager_Factory(Provider<Main> mainProvider,
      Provider<GenerateCommands> generateCommandsProvider) {
    this.mainProvider = mainProvider;
    this.generateCommandsProvider = generateCommandsProvider;
  }

  @Override
  public CommandManager get() {
    return newInstance(mainProvider.get(), generateCommandsProvider.get());
  }

  public static CommandManager_Factory create(Provider<Main> mainProvider,
      Provider<GenerateCommands> generateCommandsProvider) {
    return new CommandManager_Factory(mainProvider, generateCommandsProvider);
  }

  public static CommandManager newInstance(Main main, GenerateCommands generateCommands) {
    return new CommandManager(main, generateCommands);
  }
}
