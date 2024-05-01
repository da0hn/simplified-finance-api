package dev.da0hn.simplified.finance.core.usecases;

@FunctionalInterface
public interface CommandUseCase<INPUT extends CommandUseCase.Input> {

  void execute(INPUT input);

  interface Input { }

}
