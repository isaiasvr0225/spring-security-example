
# Spring security example

This is an example to show how to secure APIs in Spring Boot.

When we have a web app we must add security, and Spring knows it, for that, Spring provides a module call Spring Security, with this module we can secure our web apps using diferents mechanisms like username and password from application.yml, username and password from a data base, LDAP, JWT, OAuth 2 using third-party authentication with social media, etc.

In this case, I just used username and password, these credentials are in the application.yml, the endpoint which was secured is /index, so if you want to access to this endpoint, you shuld sign in and put the credentials wich are in the application.yml
## API Reference


```http
  GET /v1/index
```

| Parameter | Return Type     | Description                |
| :-------- | :------- | :------------------------- |
| `none` | `string` | **You must sign in before.**  Return: `"Hello Spring Security!"` |


```http
  GET /v1/index2
```

| Parameter | Return Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `none`      | `string` | **Sign in not required**. This endpoint is not secured. Return: `"Hello Spring Security! Not Secured"`|

```http
  GET /v1/session
```

| Parameter | Return Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `none`      | `json` | **Sign in not required**. This endpoint is not secured. Return: a json with user session data like sessionId|

