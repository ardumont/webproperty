(defproject webproperty "0.1.0-SNAPSHOT"
  :description "A simple API to expose some properties file."
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure     "1.6.0"]
                 [compojure               "1.1.8"]
                 [clojurewerkz/propertied "1.2.0"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [ring/ring-devel         "1.2.1"]
                 [environ                 "0.4.0"]
                 [clj-http                "0.9.2"]
                 [org.clojure/data.json   "0.2.4"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler webproperty.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}
   :uberjar {:aot :all}}
  :main webproperty.main)
