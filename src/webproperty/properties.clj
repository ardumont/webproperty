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

(defn write-properties-file [filepath m]
  "Given a map m and a filepath, dump the content of such map as a properties file."
  (p/store-to m filepath))

(comment
  (let [filepath (format "%s/repo/perso/webproperty/resources/public/bootstrap-webproperty.properties" (System/getProperty "user.home"))]
    (->> filepath
         load-properties-file
         (write-properties-file filepath))))
