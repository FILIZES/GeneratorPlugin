package org.filizes.generatorplugin.listener;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.filizes.generatorplugin.Main;
import org.filizes.generatorplugin.generate.RoomsGenerator;

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
public final class ListenerManager_Factory implements Factory<ListenerManager> {
  private final Provider<Main> pluginProvider;

  private final Provider<RoomsGenerator> roomsGeneratorProvider;

  public ListenerManager_Factory(Provider<Main> pluginProvider,
      Provider<RoomsGenerator> roomsGeneratorProvider) {
    this.pluginProvider = pluginProvider;
    this.roomsGeneratorProvider = roomsGeneratorProvider;
  }

  @Override
  public ListenerManager get() {
    return newInstance(pluginProvider.get(), roomsGeneratorProvider.get());
  }

  public static ListenerManager_Factory create(Provider<Main> pluginProvider,
      Provider<RoomsGenerator> roomsGeneratorProvider) {
    return new ListenerManager_Factory(pluginProvider, roomsGeneratorProvider);
  }

  public static ListenerManager newInstance(Main plugin, RoomsGenerator roomsGenerator) {
    return new ListenerManager(plugin, roomsGenerator);
  }
}
