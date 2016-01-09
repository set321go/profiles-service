Profile Service
---------------

This service is designed to serve user profile data. It is designed to easily modify so that you can insert your own
profile sections. It is built using [ratpack](https://ratpack.io) an event based http framework for java.

OTB Parts
---------

* `IdentityHandler` - Provides identity resolution expects to find a `Client-Identity` header that contains a token that
can be verified by your Identity Provider. Resolution is done by the `IdentityLoader`, by default this is just a dummy
implementation.
* `ProfileCommonHandler` - Provides handling of common profile data. Currently 'name' is the only common profile data.
Again this is backed by a dummy data loader.
* `ProfileContactsHandler` - WIP

Future Work
-----------
* Docker buildable container
* Implementation backed IdentityLoaders.
  1. OpenId Connect
  2. OAUTH 2
  3. SAML
* Implementation backed DataLoader layer.
  1. RDBMS
  2. Cassandra
* Metrics

Licence
-------
This project is covered by the [Apache License 2.0](LICENCE)



