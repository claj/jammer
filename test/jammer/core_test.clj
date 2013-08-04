(ns jammer.core-test
  (:require [clojure.test :refer :all]
            [jammer.core :refer :all]))

(deftest keycode->char-test
  (testing "keycode->char-test"
    (is (= (keycode->char 2001) 2001)
        (= (keycode->char 48) \0)))
  (testing "keycode->midinot test"
    (is (= (keycode->midinote 65 ) 54)))


)

;;would love to test the loop back





