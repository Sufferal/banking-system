package Customer.Strategy;

public class AuthenticationContext {
  private Authentication authentication;

  public AuthenticationContext(Authentication authentication) {
    this.authentication = authentication;
  }

  public boolean authenticate() {
    return authentication.authenticate();
  }

  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }
}
