(ns webproperty.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :refer [site]]
            [compojure.route :refer [resources not-found]]))

(defroutes app-routes
  (GET "/" [] "An API to manipulate properties file.")
  (resources "/")
  (not-found "Not Found"))

(def app
  (site app-routes))
