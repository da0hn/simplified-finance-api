package dev.da0hn.simplified.finance.core.usecases;

@FunctionalInterface
public interface QueryUseCase<OUTPUT extends QueryUseCase.Output> {

  OUTPUT execute();

  interface Output { }

}
