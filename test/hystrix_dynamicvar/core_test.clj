(ns hystrix-dynamicvar.core-test
  (:require [clojure.test :refer :all]
            [hystrix-dynamicvar.core :refer :all]
            [com.netflix.hystrix.core :refer [defcommand]])
  (:import java.util.concurrent.Executors
           com.netflix.hystrix.strategy.HystrixPlugins))

(def ^:dynamic *dynvar* "root")

(defcommand foo-command []
  *dynvar*)

(deftest test-dynvar-binding
  (testing "success in future for binding-conveyance introduced in Clojure 1.3"
    (binding [*dynvar* "binding-value"]
      (is (= "binding-value" @(future *dynvar*)))))
  (testing "fail in threadpool"
    (binding [*dynvar* "binding-value"]
      (let [exec (Executors/newFixedThreadPool 1)]
        (is (= "root" @(.submit exec ^Callable (fn [] *dynvar*)))))))
  (testing "fail in original hystrix command"
    (HystrixPlugins/reset)
    (binding [*dynvar* "binding-value"]
      (is (= "root" (foo-command)))))
  (testing "success in hystrix command with hystrix-dynamicvar"
    (HystrixPlugins/reset)
    (register-dynamicvar-strategy #'*dynvar*)
    (binding [*dynvar* "binding-value"]
      (is (= "binding-value" (foo-command))))))
