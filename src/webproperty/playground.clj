(ns webproperty.playground
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(defn json-get [uri]
  "Get URI as json."
  (client/get uri {:as :json}))

(defn json-post [uri m]
  "Post map m to URI as json."
  (client/post uri {;;:basic-auth ["user" "pass"]
                    :form-params m
                    ;;:headers {"X-Api-Version" "2"}
                    ;; :content-type :json
                    ;; :socket-timeout 1000  ;; in milliseconds
                    ;; :conn-timeout 1000    ;; in milliseconds
                    :accept :json ;; header to accept json
                    :as :json     ;; coerce into json
                    }))

(comment
  (json/write-str {:key "value"})

  (json/read-str (json/write-str {:key "value"}))

  (json-get "http://localhost:5000/properties/sample0")

  (-> (json-post "http://localhost:5000/properties/sample0" {:some.key.3 "anything"})
      :body))
