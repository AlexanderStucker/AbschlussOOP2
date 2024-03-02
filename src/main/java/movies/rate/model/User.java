package movies.rate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import movies.rate.services.MediaService;

public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String password;
  private List<Integer> mediaListIds = new ArrayList<>();
  private Map<Integer, Double> ratings = new HashMap<>();
  
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

  public Map<Integer,Double> getRatings() {
    return this.ratings;
  }

  public List<Media> getMyList() {
    return MediaService.getInstance().getMedia().stream().filter(media -> this.mediaListIds.contains((Integer) media.getId())).collect(Collectors.toList());
  }

  public void addToMyList(Media media) {
    if(!this.mediaListIds.contains(Integer.valueOf(media.getId()))) {
      this.mediaListIds.add(Integer.valueOf(media.getId()));
    }
  }

  public void removeFromMyList(Media media) {
    this.mediaListIds.remove(this.mediaListIds.indexOf(media.getId()));
  }
  
}
