(ns webproperty.config
  "Configuration regarding where the properties file are stored.
The application is setuped through a property file referenced by default in /tmp/bootstrap-webproperty.properties.
This bootstrap configuration file can be overriden by a jvm property bootstrap-webproperty.
`-Dbootstrap-webproperty=/some/path/to/bootstrap/properties/file.`"
  (:require [webproperty.properties :refer [load-properties-file]]))

;; the default filepath to the bootstrap webproperty configuration file
(def ^:private default-webproperty-filepath "/tmp/bootstrap-webproperty.properties")

;; for dev
;; (System/setProperty "bootstrap-webproperty" "/home/tony/repo/perso/webproperty/resources/public/bootstrap-webproperty.properties")

;; Determine the path to the bootstrap webproperty configuration file
;; If a jvm property exists referencing it, we use it
;; otherwise, we default to the value of `default-webproperty-filepath`
(def ^:private bootstrap-webproperty-filepath (if-let [property-filepath (System/getProperty "bootstrap-webproperty")]
                                                property-filepath
                                                default-webproperty-filepath))

;; The key referencing the folder that exposes the properties to expose
(def ^:private path-to-properties-folder-key "path.to.properties.folder")

;; Load the bootstrap configuration file from the system
(def ^:private bootstrap-webproperty-config (load-properties-file bootstrap-webproperty-filepath))

;; Retrieve the folder that exposes the properties files
(def webproperty-properties-folder (bootstrap-webproperty-config path-to-properties-folder-key))
