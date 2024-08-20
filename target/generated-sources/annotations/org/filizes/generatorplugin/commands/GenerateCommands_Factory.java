package org.filizes.generatorplugin.commands;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class GenerateCommands_Factory implements Factory<GenerateCommands> {
  private final Provider<RoomsGenerator> roomsGeneratorProvider;

  public GenerateCommands_Factory(Provider<RoomsGenerator> roomsGeneratorProvider) {
    this.roomsGeneratorProvider = roomsGeneratorProvider;
  }

  @Override
  public GenerateCommands get() {
    return newInstance(roomsGeneratorProvider.get());
  }

  public static GenerateCommands_Factory create(Provider<RoomsGenerator> roomsGeneratorProvider) {
    return new GenerateCommands_Factory(roomsGeneratorProvider);
  }

  public static GenerateCommands newInstance(RoomsGenerator roomsGenerator) {
    return new GenerateCommands(roomsGenerator);
  }
}
