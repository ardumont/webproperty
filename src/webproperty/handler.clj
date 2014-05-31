(ns webproperty.handler
  "Expose the routes of the application."
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [webproperty.properties :as properties]
            [webproperty.config :as config]
            [clojure.data.json :as json]))

(defn response [content-type body]
  "Generic get response"
  (-> (resp/response body)
      (resp/content-type content-type)))

(defn filepath-from-filename [rootpath filename]
  "Compute the full filepath from the given filename."
  (format "%s/%s.properties" rootpath filename))

(defn load-map-from-properties [rootpath filename]
  "Load properties from the filename."
  (->> filename
       (filepath-from-filename rootpath)
       properties/load-properties-file))

(defroutes app-routes
  (GET "/" [] "An API to manipulate properties files.")

  (GET "/properties/:filename" [filename]
       (->> filename
            (load-map-from-properties (config/webproperty-properties-folder))
            json/write-str
            (response "application/json")))

  (GET "/properties/:filename/:key" [filename key]
       (->> filename
            (load-map-from-properties (config/webproperty-properties-folder))
            ((fn [m] (get m key)))
            json/write-str
            (response "application/json")))

  (POST "/properties/:filename" [filename :as req]
        (do
          (->> req
               :form-params
               (properties/merge-properties-file (filepath-from-filename (config/webproperty-properties-folder) filename)))
          (resp/redirect (format "/properties/%s" filename))))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
