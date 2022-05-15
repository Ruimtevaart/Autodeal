package lt.vu.decorator;

import javax.enterprise.context.ApplicationScoped;
import lt.vu.interceptors.Logger;

@ApplicationScoped
public class PartNameCapitalizerLogic implements PartNameCapitalizer{

  @Override
  @Logger
  public String capitalizeName(String name) {
    return Character.toUpperCase(name.charAt(0)) + name.substring(1);
  }

}
