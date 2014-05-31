(ns webproperty.test.properties-test
  (:require [webproperty.properties :refer :all]
            [midje.sweet :refer :all]))

(fact (keywordize "hello-you_how-are.you") => "hello.you.how.are.you")
