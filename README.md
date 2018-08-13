# hystrix-dynamicvar [![Build Status](https://travis-ci.org/jiacai2050/hystrix-dynamicvar.svg?branch=master)](https://travis-ci.org/jiacai2050/hystrix-dynamicvar) [![Clojars Project](https://img.shields.io/clojars/v/hystrix-dynamicvar.svg)](https://clojars.org/hystrix-dynamicvar)

[Hystrix](https://github.com/Netflix/Hystrix) does its job quiet good. However, its thread-style isolation makes dynamic var not work any more.

Hystrix-dynamicvar use a customized [concurrency strategy](https://github.com/Netflix/Hystrix/wiki/Plugins#concurrencystrategy) to pass value of caller's dynamic var to hystrix command. More refer:

- https://github.com/Netflix/Hystrix/issues/92

## Usage

First add dependency
```
[hystrix-dynamicvar "0.1.1"]
```
Then
```clj
(ns your-ns
  (:require [hystrix-dynamicvar.core :refer [register-dynamicvar-strategy]]))

;; then at the beginning of your app
(register-dynamicvar-strategy #'*foo* #'*bar* #'*any-dynamic-var*)

;; it works!
```

Refer [core_test.clj](./test/hystrix_dynamicvar/core_test.clj) for concrete demo.

## License

Copyright © 2018 Jiacai Liu

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
