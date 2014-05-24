(ns webproperty.handler
  "Expose the routes of the application."
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [webproperty.properties :as properties]
            [webproperty.config :as config]))

(defn response [content-type body]
  "Generic get response"
  (-> (resp/response body)
      (resp/content-type content-type)))

(defroutes app-routes
  (GET "/" [] "An API to manipulate properties file.")
  (GET "/properties/:filename" [filename :as req]
       (->> (format "%s/%s.properties" config/webproperty-properties-folder filename)
            properties/load-properties-file
            (response "text/plain")))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
