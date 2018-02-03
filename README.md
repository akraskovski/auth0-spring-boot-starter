# auth0-spring-boot-starter
Starter project for using [Auth0](https://auth0.com/) security in spring boot based application

Environment:
---
- Java 1.8
- Maven 3.3.9

[Regular Spring Application](https://github.com/akraskovski/auth0-spring-boot-starter/tree/master/sso)
---
Example of usage Single Sign On authentication based on third-party authentication server (Auth0 in this example).

More suitable for the clean MVC projects with permanent session, because after each successfully login 
auth server redirects to the specified your application callback link with id and access tokens.

[Server Client + API](https://github.com/akraskovski/auth0-spring-boot-starter/tree/master/server-client)
---
Example of authentication flow, in which server app presents as client to access resource server.

For authorizing a client Auth0 supports the Client Credentials grant.
The Server Client API will be secured by ensuring that a valid Access Token (JSON Web Token) 
is passed in the HTTP Authorization header when calls are made to the API.

In this example JWT encoding provided by the RS256 algorithm.
