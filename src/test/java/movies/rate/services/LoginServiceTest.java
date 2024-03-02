package movies.rate.services;

import movies.rate.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

  @Mock
  private UserService userService;
  private LoginService loginService;

  @BeforeEach
  public void setup() {
      MockitoAnnotations.openMocks(this);
      loginService = LoginService.getInstance();
      UserService.setInstance(userService);
  }

  // Test bei einem korrekten Login
  @Test
  public void testLoginSuccess() {
    // Arrange
    String username = "testUser";
    String password = "testPass";
    User mockUser = new User(username, password);
    when(userService.getUser(username)).thenReturn(mockUser);

    // Act
    User loggedInUser = loginService.login(username, password);

    // Assert
    assertNotNull(loggedInUser);
    assertEquals(username, loggedInUser.getUsername());
}

  // Test bei einem inkorrekten Login, mit falschem PW
  @Test
  public void testLoginFailureWrongPassword() {

    // Arrange
    String username = "testUser";
    String correctPassword = "correctPassword";
    String wrongPassword = "wrongPassword";
    User mockUser = new User(username, correctPassword);
    
    // Das korrekte Login wird abgerufen, damit der mockUser zurückgegeben wird
    when(userService.getUser(username)).thenReturn(mockUser);

    // Act & Assert
    // Wird mit einem flaschen Password eingelogt, sollte es ein IllegalArgumentException zurückwerfen
    assertThrows(IllegalArgumentException.class, () -> loginService.login(username, wrongPassword));
}

}


