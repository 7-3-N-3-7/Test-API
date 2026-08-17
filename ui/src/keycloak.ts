import Keycloak from 'keycloak-js';

// 1. Configure the Keycloak connection
const keycloakConfig = {
  url: 'http://localhost:8080', // The URL where Keycloak is running
  realm: 'myrealm',             // The realm we created
  clientId: 'myclient'          // The client we created
};

// 2. Create the instance
const keycloak = new Keycloak(keycloakConfig);

// 3. Create an initialization function that disables the broken iframe
export const initKeycloak = (onAuthenticatedCallback: () => void) => {
  keycloak.init({
    onLoad: 'login-required',
    checkLoginIframe: false,    // <-- THIS FIXES THE ERROR!
    pkceMethod: 'S256'          // Best practice for modern React apps
  })
  .then((authenticated) => {
    if (authenticated) {
      // If login is successful, trigger the callback to render the React App
      onAuthenticatedCallback();
    } else {
      console.warn("Not authenticated! Redirecting to Keycloak...");
      keycloak.login();
    }
  })
  .catch((err) => {
    console.error("Keycloak initialization failed", err);
  });
};

export default keycloak;