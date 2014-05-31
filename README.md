webproperty [![Build Status](https://travis-ci.org/ardumont/webproperty.png?branch=master)](https://travis-ci.org/ardumont/webproperty)
===========

A simple API to expose properties file.

- Read a properties file exposed on API: GET /properties/some-filename
- Read a particular key exposed on API: GET /properties/some-filename/some-key
- Update a particular properties file exposed on API: POST /properties/some-filename with standard form parameters key/value

The filename is just its name without the its path to the machine and without the .properties extension.

The folder which exposes the filename is configured through a System properties "bootstrap.webproperty".
By default, if this system property is not filled, the default folder "/tmp/bootstrap-webproperty.properties" is used.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein run

## License

Copyright © 2014 commiters
