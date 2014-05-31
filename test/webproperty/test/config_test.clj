(ns webproperty.test.config-test
  (:require [webproperty.config :refer :all]
            [midje.sweet :refer :all]
            [webproperty.properties :as properties]
            [environ.core :as env]))

(fact "Properties configuration file loaded from environment variable."
  (webproperty-properties-folder) => :some-value
  (provided
    (env/env "bootstrap.webproperty") => :some-filename
    (properties/load-properties-file :some-filename) => {"path.to.properties.folder" :some-value}))

(fact "Properties configuration file loaded from default bootstrap properties file."
  (webproperty-properties-folder) => :some-value
  (provided
    (env/env "bootstrap.webproperty") => nil
    (properties/load-properties-file "/tmp/bootstrap-webproperty.properties") => {"path.to.properties.folder" :some-value}))
