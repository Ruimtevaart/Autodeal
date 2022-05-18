package lt.vu.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import lt.vu.interceptors.Logger;

@Default
@ApplicationScoped
public class NameNumberChecker implements NameChecker {

  @Override
  @Logger
  public boolean containsSpecialChars(String name) {
    return name.matches(".*[0-9].*");
  }

}
