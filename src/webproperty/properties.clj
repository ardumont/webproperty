(ns webproperty.properties
  "API around the properties manipulation."
  (:require [clojurewerkz.propertied.properties :as p]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn load-properties-file [filepath]
  "Load properties file from disk into a clojure map."
  (->> filepath
       io/file
       p/load-from
       (into {})))

(defn write-properties-file [filepath m]
  "Given a map m and a filepath, dump the content of such map as a properties file."
  (p/store-to m filepath))

(defn keywordize [s]
  "Given a string, keywordize it."
  (-> (str/lower-case s)
      (str/replace "_" ".")
      (str/replace "-" ".")))

(defn merge-properties-file [filepath m]
  "Given a filepath and a map, merge the properties from filepath and m into filepath."
  (->> m
       (merge (load-properties-file filepath) )
       (write-properties-file filepath)))

(comment
  (let [filepath (format "%s/repo/perso/webproperty/resources/public/bootstrap-webproperty.properties" (System/getProperty "user.home"))]
    (->> filepath
         load-properties-file))

  (let [filepath (format "%s/repo/perso/webproperty/resources/public/bootstrap-webproperty.properties" (System/getProperty "user.home"))]
    (->> filepath
         load-properties-file
         (write-properties-file filepath)))

  (let [filepath (format "%s/repo/perso/webproperty/resources/public/bootstrap-webproperty.properties" (System/getProperty "user.home"))]
    (merge-properties-file filepath {"some-new-key" "with-value"}))
  )
