vaadin-spring-no-boot
==============

run `mvn clean install` and deploy to Tomcat 8.0.50

1) Push working:  

Access http://localhost:8080/foo with the browser developer tools open and note the logged messages:

Fri Mar 23 13:24:13 GMT-500 2018 com.vaadin.client.communication.AtmospherePushConnection
INFO: Establishing push connection
com.vaadin.DefaultWidgetSet-0.js:6333 Fri Mar 23 13:24:13 GMT-500 2018 com.vaadin.client.communication.AtmospherePushConnection
INFO: Push connection established using websocket

=========

2) Push not working

Access http://localhost:8080/bar with the browser developer tools open and note the logged messages:

Fri Mar 23 13:29:20 GMT-500 2018 com.vaadin.client.communication.AtmospherePushConnection
INFO: Establishing push connection
vaadinPush.debug.js?v=7.7.13:1195 WebSocket connection to 'ws://localhost:8080/bar/PUSH?v-uiId=4&v-pushId=b59f64cf-4cd2-462f-9155-6bf3919f8e21&X-Atmosphere-tracking-id=0&X-Atmosphere-Framework=2.2.13.vaadin5-javascript&X-Atmosphere-Transport=websocket&X-Atmosphere-TrackMessageSize=true&Content-Type=application/json;%20charset=UTF-8&X-atmo-protocol=true' failed: Error during WebSocket handshake: Unexpected response code: 200

========== 

Access http://localhost:8080/VAADIN with the browser developer tools open and note the logged messages:

Fri Mar 23 13:30:39 GMT-500 2018 com.vaadin.client.communication.AtmospherePushConnection
INFO: Establishing push connection
vaadinPush.debug.js?v=7.7.13:1195 WebSocket connection to 'ws://localhost:8080/VAADIN/PUSH?v-uiId=6&v-pushId=b59f64cf-4cd2-462f-9155-6bf3919f8e21&X-Atmosphere-tracking-id=0&X-Atmosphere-Framework=2.2.13.vaadin5-javascript&X-Atmosphere-Transport=websocket&X-Atmosphere-TrackMessageSize=true&Content-Type=application/json;%20charset=UTF-8&X-atmo-protocol=true' failed: Error during WebSocket handshake: Unexpected response code: 404