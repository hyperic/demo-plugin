demo-plugin
===========

This plugin will auto discover 2 server (Server 1 and Server 2) with a metric and 2 services each.

The servers and services availability is controled by the exitance or not of a file, if the file exists the server or service is GREEN, if not, is RED.
* /tmp/server*X*_server for servers
* /tmp/server*X*_*S* for services
Where *X* is the server numebr and *S* is the service number.
