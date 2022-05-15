package lt.vu.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import lt.vu.interceptors.Logger;

@Alternative
@ApplicationScoped
public class NameNumberChecker implements NameChecker {

  @Override
  @Logger
  public boolean containsSpecialChars(String name) {
    return name.matches(".*[0-9].*");
  }

}
