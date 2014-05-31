(ns webproperty.test.handler
  (:use clojure.test
        ring.mock.request
        webproperty.handler))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "an API to manipulate properties file."))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
