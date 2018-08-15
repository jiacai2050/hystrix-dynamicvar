(defproject hystrix-dynamicvar "0.1.2"
  :description "Clojure dynamic var binding in hystrix command"
  :url "https://github.com/jiacai2050/hystrix-dynamicvar"
  :scm {:url "https://github.com/jiacai2050/hystrix-dynamicvar"
        :name "github"}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :java-source-paths ["java_src"]
  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [com.netflix.hystrix/hystrix-clj "1.5.11" :scope "provided"]]
  :deploy-repositories [["releases" :clojars]])
