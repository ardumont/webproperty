(ns webproperty.main
  "Main namespace to trigger the application."
  (:require [webproperty.handler :refer [app]]
            [compojure.handler :as handler]
            [clojure.java.io :as io]
            [ring.middleware.stacktrace :as trace]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]))

(defn wrap-error-page [handler]
  (fn [req]
    (try (handler req)
         (catch Exception e
           {:status 500
            :headers {"Content-Type" "text/html"}
            :body (slurp (io/resource "500.html"))}))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (-> #'app
                         ((if (env :production)
                            wrap-error-page
                            trace/wrap-stacktrace))
                         handler/site)
                     {:port port :join? false})))

;; For interactive development:
(comment
  (System/setProperty "bootstrap.webproperty" "/home/tony/repo/perso/webproperty/resources/public/bootstrap-webproperty.properties")
  (.stop server)
  (def server (-main))
  (do
   (.stop server)
   (def server (-main)))
  )
