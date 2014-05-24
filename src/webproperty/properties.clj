(ns webproperty.properties
  "API around the properties manipulation."
  (:require [clojurewerkz.propertied.properties :as p]
            [clojure.java.io :as io]))

(defn load-properties-file [filepath]
  "Load properties file from disk into a clojure map."
  (->> filepath
       io/file
       p/load-from
       (into {})))

;; (load-properties-file "/home/tony/repo/perso/webproperty/resources/public/bootstrap-webproperty.properties")
