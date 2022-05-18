package lt.vu.service;

import javax.enterprise.context.ApplicationScoped;
import lt.vu.interceptors.Logger;

@ApplicationScoped
public class NameSlashChecker implements NameChecker2 {

  @Override
  @Logger
  public boolean containsSlash(String name) {
    return name.matches(".*[/].*");
  }

}
