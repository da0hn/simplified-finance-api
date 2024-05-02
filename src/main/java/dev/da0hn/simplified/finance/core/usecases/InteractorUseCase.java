package dev.da0hn.simplified.finance.core.usecases;

@FunctionalInterface
public interface InteractorUseCase<INPUT extends InteractorUseCase.Input, OUTPUT extends InteractorUseCase.Output> {

  OUTPUT execute(INPUT input);

  interface Input { }

  interface Output { }

}
