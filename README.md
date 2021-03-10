Readme
For the setup:
1.	Import the attached zip to Intelij/ide.
2.	Run the application.
3.	Once application starts, copy the highlighted value, for example: jdbc:h2:mem:c218ba9d-1445-4df9-bcad-8e3b518469c2
 

4.	To open h2 DB, type in your browser: http://localhost:8080/h2/
The following window will appear:
 

In the highlighted, paste the value copied in a previous step.



The tasks:

1.	For Shortening: Please run in postman following cuRL:
curl --location --request POST 'http://localhost:8080/shorten' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--header 'Accept-Language: en-US' \
--data-raw '{
    "originalUrl": "https://www.baeldung.com/"
}
'

 


See the results in DB in URL table:
 
2.	For Redirection 
In your browser enter: http://localhost:8080/<the short Link>, for example:
http://localhost:8080/8839a548 and you will be redirected to original URL.
3.	After 1 and 2 steps, please Check the Statistics table in h2 DB, for example:
 






