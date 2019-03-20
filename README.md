Demonstrates the use of Envoy with static config to route between 2 Java services

This project roughly follows the [example](https://www.envoyproxy.io/docs/envoy/latest/start/sandboxes/front_proxy.html) in the Envoy docs for running Envoy as a front proxy.

## Pre-reqs

`docker`, `docker-compose` and `docker-machine`

A simple way to achieve this is via the [Docker Toolbox](https://www.docker.com/products/docker-desktop).

## Running the example

1. Create a virtual machine to run the containers in

```bash
$ docker-machine create --driver virtualbox default
$ eval $(docker-machine env default)
```

2. Start the services

```bash
$ docker-compose up --build -d
...
...
$ docker-compose ps
          Name                        Command               State                            Ports
---------------------------------------------------------------------------------------------------------------------------
static-mvp_front-envoy_1   /docker-entrypoint.sh /bin ...   Up      10000/tcp, 0.0.0.0:8000->80/tcp, 0.0.0.0:8001->8001/tcp
static-mvp_service1_1      java -jar /opt/app/app.jar       Up      80/tcp
static-mvp_service2_1      java -jar /opt/app/app.jar       Up      80/tcp
```

3. Make some requests

```bash
$ curl -v $(docker-machine ip default):8000/service/1/
*   Trying 192.168.99.100...
* TCP_NODELAY set
* Connected to 192.168.99.100 (192.168.99.100) port 8000 (#0)
> GET /service/1/ HTTP/1.1
> Host: 192.168.99.100:8000
> User-Agent: curl/7.54.0
> Accept: */*
>
< HTTP/1.1 200 OK
< content-type: text/plain;charset=UTF-8
< content-length: 13
< date: Wed, 20 Mar 2019 17:30:30 GMT
< x-envoy-upstream-service-time: 27
< server: envoy
<
* Connection #0 to host 192.168.99.100 left intact
Hello message%
```
