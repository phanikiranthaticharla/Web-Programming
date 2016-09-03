# Web-Programming

Tools Used:

1. java version "1.8.0_77"
2. Bootstrap
3. Website Client Server - Apache Tomcat Version 8  (running on port 8443)
4. Webservices Server -	Apache Tomcat Version 8  (running on port 9443 )
5. SimpleCart.js for adding items to the cart
6. list.js for searching items in the cart


Steps to run:

1. The GroceryKart Client war file is copied to the WEBAPPS path of the tomcat client.
2. The GroceryKart Server war file is copied to the WEBAPPS path of the tomcat server.
3. Copy	the server and client keystore files to	their respective folders.
4. Edit the server.xml 	of webclient to use the keystore and change the configuration to use 8443 and webservices to use the keystore and change the configuration to u\
se 9443
5. Now start the browser client and webservices server.
6. Now the GroceryKart home page can be	viewed from the	below link:
https://localhost:8443/GroceryKartClient/

7. All the required functionalities like creating new user, login, shopping cart, search, adding review	for a product purchased	can be accessed	from the above link.