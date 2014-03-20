demo-plugin
===========

This plugin will auto discover 2 server (Server 1 and Server 2) with a metric and 2 services each.

The servers and services availability is controled by the content of a file:
* 1 for UP
* 0 for Down
* 0.02 for power-off
* 0.01 for paused
* any other value for unknown

Each resource have its own file:
* /tmp/serverX_server for servers
* /tmp/serverX_S for services

Where *X* is the server numebr and *S* is the service number.

examples:
========

```
echo 1 > /tmp/server1_server
```

Will make "Demo Server 1" UP.

```
echo 0 > /tmp/server1_server
```

Will make "Demo Server 1" DOWN.