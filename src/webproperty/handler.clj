(ns webproperty.handler
  "Expose the routes of the application."
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [webproperty.properties :as properties]
            [webproperty.config :as config]))

(defn- response [content-type body]
  "Generic get response"
  (-> (resp/response body)
      (resp/content-type content-type)))

(defn- load-map-from-properties [filename]
  "Load properties from the filename."
  (->> filename
       (format "%s/%s.properties" config/webproperty-properties-folder)
       properties/load-properties-file))

(defroutes app-routes
  (GET "/" [] "An API to manipulate properties file.")
  (GET "/properties/:filename" [filename]
       (->> filename
            load-map-from-properties
            pr-str
            (response "text/plain")))
  (GET "/properties/:filename/:key" [filename key]
       (->> filename
            load-map-from-properties
            (get key)
            pr-str
            (response "text/plain")))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
