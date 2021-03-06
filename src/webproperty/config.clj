(ns webproperty.config
  "Configuration regarding where the properties file are stored.
The application is setuped through a property file referenced by default in /tmp/bootstrap-webproperty.properties.
This bootstrap configuration file can be overriden by a jvm property bootstrap-webproperty.
`-Dbootstrap.webproperty=/some/path/to/bootstrap/properties/file.`"
  (:require [webproperty.properties :refer [load-properties-file]]
            [environ.core :refer [env]]))

(defn webproperty-properties-folder []
  "Compute the configuration folder that exposes the properties files."
  (let [;; the default filepath to the bootstrap webproperty configuration file
        default-webproperty-filepath "/tmp/bootstrap-webproperty.properties"
        ;; Determine the path to the bootstrap webproperty configuration file
        ;; If a jvm property exists referencing it, we use it
        ;; otherwise, we default to the value of `default-webproperty-filepath`
        bootstrap-webproperty-filepath (if-let [property-filepath (env "bootstrap.webproperty")]
                                         property-filepath
                                         default-webproperty-filepath)
        ;; Load the bootstrap configuration file from the system
        bootstrap-webproperty-config (load-properties-file bootstrap-webproperty-filepath)]
    (bootstrap-webproperty-config "path.to.properties.folder")))
