(ns webproperty.test.handler-test
  (:use clojure.test
        ring.mock.request
        webproperty.handler)
  (:require [midje.sweet :refer :all]
            [webproperty.properties :as properties]
            [webproperty.config :as config]))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "An API to manipulate properties files."))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
(fact (app (request :get "/properties/:name"))       => {:status 200
                                                         :headers {"Content-Type" "application/json"}
                                                         :body "{\"1\":\"2\"}"}
  (provided
    (config/webproperty-properties-folder)           => :some-path-to
    (load-map-from-properties :some-path-to ":name") => {"1" "2"}))

(fact (app (request :get "/properties/filename/key"))   => {:status 200
                                                            :headers {"Content-Type" "application/json"}
                                                            :body "\"some-value\""}
  (provided
    (config/webproperty-properties-folder)              => :some-path-to
    (load-map-from-properties :some-path-to "filename") => {"key" "some-value"}))

(fact (app (request :post "/properties/some-filename" {"key" "value"}))       => {:status 302
                                                                                  :headers {"Location" "/properties/some-filename"}
                                                                                  :body ""}
  (provided
    (config/webproperty-properties-folder)                                    => :some-path
    (filepath-from-filename :some-path "some-filename")                       => :some-path-to-filename
    (properties/merge-properties-file :some-path-to-filename {"key" "value"}) => :do-not-care))

(fact "Compute the filepath given a filename"
  (filepath-from-filename "/some/file/path/to" "some-name") => "/some/file/path/to/some-name.properties")

(fact "Load map from properties"
  (load-map-from-properties "/some/file/path/to" "some-name")  => :some-map
  (provided
    (properties/load-properties-file "/some/file/path/to/some-name.properties") => :some-map))

(fact "response"
  (response "some-content-type" "some-body") => {:status 200
                                                 :headers {"Content-Type" "some-content-type"}
                                                 :body "some-body"})
