# INACTIVE - this library is no longer used. The single class provided by this library has been moved into bank-account-reputation, the only project that used this library.

# play-random-json-filter

[![Build Status](https://travis-ci.org/hmrc/play-random-json-filter.svg?branch=master)](https://travis-ci.org/hmrc/play-random-json-filter) [ ![Download](https://api.bintray.com/packages/hmrc/releases/play-random-json-filter/images/download.svg) ](https://bintray.com/hmrc/releases/play-random-json-filter/_latestVersion)

Simple Play filter which introduces a random additional node to each Json object which is returned by the called Microservice.
 
The purpose of this is to ensure that calling clients of the microservice have not tightly coupled their service to the exact Json returned
and are tolerant of additional, unexpected, fields.  The intent is for this only to be run in development and not in production environments.

##Usage

To use the filter create as per below and then add `randomJsonFilter` into your filters collection.  

```
  lazy val randomJsonFilterConfig = Play.current.configuration.getConfig(s"$env.randomJsonFilter")
  lazy val randomJsonFilter = new RandomJsonFilter(randomJsonFilterConfig) with MicroserviceFilterSupport
```

The example below uses a config item based on the environment - e.g. Dev - so to enable the filter you would set:

```
Dev {
  randomJsonFilter.enabled = true
}
 ```


### Licence

This code is open source software licensed under the 
[Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
    
