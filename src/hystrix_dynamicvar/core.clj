(ns hystrix-dynamicvar.core
  (:import [com.netflix.hystrix.strategy HystrixPlugins]
           [hystrix_dynamicvar ClojureDynamicVarConcurrencyStrategy]))

(defn register-dynamicvar-strategy
  "Enable dynamic var binding work in hystrix command.
  Use this method use register dynamic vars required to pass through hystrix command at the beginning of your app. eg
  (register-dynamicvar-strategy #'*foo* #'*bar* #'*any-dynamic-var*)"
  [& dynamicvars]
  (.. HystrixPlugins getInstance (registerConcurrencyStrategy (ClojureDynamicVarConcurrencyStrategy. dynamicvars))))

