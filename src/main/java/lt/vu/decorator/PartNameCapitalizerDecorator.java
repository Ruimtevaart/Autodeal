package lt.vu.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import lt.vu.interceptors.Logger;

@Decorator
public class PartNameCapitalizerDecorator implements PartNameCapitalizer{

  @Inject
  @Delegate
  @Any
  private PartNameCapitalizer partNameCapitalizer;

  @Override
  @Logger
  public String capitalizeName(String name) {
    return partNameCapitalizer.capitalizeName(name).toUpperCase();
  }

}
