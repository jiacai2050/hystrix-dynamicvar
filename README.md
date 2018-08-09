# hystrix-dynamicvar [![Build Status](https://travis-ci.org/jiacai2050/hystrix-dynamicvar.svg?branch=master)](https://travis-ci.org/jiacai2050/hystrix-dynamicvar) [![Clojars Project](https://img.shields.io/clojars/v/hystrix-dynamicvar.svg)](https://clojars.org/hystrix-dynamicvar)

Clojure dynamic var binding in hystrix command.

## Usage

```
(ns your-ns
  (:require [hystrix-dynamicvar.core :refer [register-dynamicvar-strategy]]))]

;then at the beginning of your app

(register-dynamicvar-strategy #'*foo* #'*bar* #'*any-dynamic-var*)
```

## License

Copyright © 2018 Jiacai Liu

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
