RADAR test
==========
Generates a random field of aircraft with random velocities.

Build
-----
mvn package builds the war file RADAR.war in target.

Deployment
----------
docker build --tag website:latest . 
docker run -d -p 8888:8080 website:latest

It waits for the PPI display to read the tracks.