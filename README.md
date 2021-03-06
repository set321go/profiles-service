[![Build Status](https://travis-ci.org/set321go/profiles-service.svg?branch=master)](https://travis-ci.org/set321go/profiles-service)

Profile Service
---------------

This service is designed to serve user profile data. It is designed to easily modify so that you can insert your own
profile sections. It is built using [ratpack](https://ratpack.io) an event based http framework for java.

Getting Started
---------------
__OTB Server starts in Development mode, this leaks implementation details to the client that you probably don't want in
production, these can be changed in the [application.yml](src/main/resources/application.yml)__

To startup the server from the command line, navigate to the root of hte project and execute:

    ./gradlew run

To build an executable jar:

    ./gradlew shadowJar

To debug add `--debug-jvm` to the end of your gradle command.

OTB Parts
---------

* `IdentityHandler` - Provides identity resolution expects to find a `Client-Identity` header that contains a token that
can be verified by your Identity Provider. Resolution is done by the `IdentityLoader`, by default this is just a dummy
implementation.
* `ProfileCommonHandler` - Provides handling of common profile data. Currently 'name' is the only common profile data.
Again this is backed by a dummy data loader.
* `ProfileContactsHandler` - WIP

Using With Docker
-----------------

You can easily create a docker image using the included `Dockerfile`. The `Dockerfile` is using shadow jar so need to have
first build it then you can create the image.

    ./gradlew installShadow
    docker build -t <name of your image>:<tag name> .

Features & Bugs
---------------

Feel free to submit a PR or open an issue. Feedback on code and docs would be appreciated as well as some snippets about
what your using the project for.

Future Work
-----------
* Implementation backed IdentityLoaders. (this is probably actually separate Identity Provider service)
  1. OpenId Connect
  2. OAUTH 2
  3. SAML
* Implementation backed DataLoader layer.
  2. Cassandra
* Metrics

Licence
-------
This project is covered by the [Apache License 2.0](LICENCE)



