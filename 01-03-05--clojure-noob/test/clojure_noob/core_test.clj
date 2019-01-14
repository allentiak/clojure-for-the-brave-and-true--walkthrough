(ns clojure-noob.core-test
  (:require [clojure.test :refer :all]
            [clojure-noob.core :refer :all]))


;;; Chapter 3

;; Exercise 3.2
(deftest add100-test
  (is (= (add100 1) 101))
  (is (= (add100 -100) 0)))

;; Exercise 3.3
(deftest dec-maker-test
  (is (= ((dec-maker 100) 101) 1))
  (is (= ((dec-maker 100) 0) -100)))

;; Exercise 3.4
(deftest mapset-test
  (is (= (mapset inc [1 1 2 2]) #{2 3})))
