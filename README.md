# ThreadoSocialLogin
Build a login and registration functionality using Google. 
On the frontend there will be just a simple link which says 'Login via Google'. 
On clicking the link users will be prompted to enter their Google username and password.
After successful authentication users should be logged in.
If the user is a first time user, his profile should also get created in the database and he should get logged in.


A customer implememntation of Oauth2.0 grantype is implemented, with grant_type as "social".
Front end uses a auth clientId that is registered in a personal googleconsole auth api.

Current codebase uses inbuilt oauth storage.

Backend is a springBoot project.
Frontend is a single web page developed in vanilla JS, using axios.
Database used is MySQL, using hibernate.

Recommended urls:
Backend default url: http://localhost:8080/
Frontend deafult url: http://localhost:5500/

