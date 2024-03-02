package movies.rate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import movies.rate.services.MediaService;

public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String password;
  private List<Integer> mediaListIds = new ArrayList<>();
  
  public User() {
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Media> getMyList() {
    return MediaService.getInstance().getMedia().stream().filter(media -> this.mediaListIds.contains(media.getId())).collect(Collectors.toList());
  }

  public void addToMyList(Media media) {
    if(!this.mediaListIds.contains(media.getId())) {
      this.mediaListIds.add(media.getId());
    }
  }

  public void removeFromMyList(Media media) {
    this.mediaListIds.remove(media.getId());
  }
  
}
