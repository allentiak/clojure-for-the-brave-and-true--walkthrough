(ns clojure-noob.core-test
  (:require [clojure.test :refer :all]
            [clojure-noob.core :refer :all]))


;;; Chapter 3

;;; Numerical functions

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


;;; Chapter 3

;;; "Hobbit violence" functions


;; Exercise 3.5

(deftest match-to-test
  (let [sym-body-part {:name "head" :size 3}
        asym-body-part {:name "left-eye" :size 1}
        symmed-body-part {:name "other-eye" :size 1}]
      (is (= (match-to sym-body-part "other-") (identity sym-body-part)))
      (is (= (match-to asym-body-part "other-") symmed-body-part))))

(deftest multiply-by-five-body-parts-test
  (let [asym-body-parts   [{:name "head" :size 3}
                           {:name "left-eye" :size 1}]
        symmed-body-parts [{:name "head" :size 3}
                           {:name "left-eye" :size 1}
                           {:name "right-eye" :size 1}
                           {:name "forward-eye" :size 1}
                           {:name "backward-eye" :size 1}
                           {:name "fifth-eye" :size 1}]]
    (is (= (multiply-by-five-body-parts asym-body-parts) symmed-body-parts))))


;; Exercise 3.6

(deftest nth-matching-part-test
  (let [sym-body-part   {:name "head" :size 3}
        asym-body-part  {:name "left-eye" :size 1}
        nth1-body-part  {:name "1th-eye" :size 1}
        nth2-body-part  {:name "2th-eye" :size 1}]
    (is (= (nth-matching-part sym-body-part -1) sym-body-part))
    (is (= (nth-matching-part sym-body-part 0) sym-body-part))
    (is (= (nth-matching-part sym-body-part 1) sym-body-part))
    (is (= (nth-matching-part sym-body-part 2) sym-body-part))
    (is (= (nth-matching-part asym-body-part -1) asym-body-part))
    (is (= (nth-matching-part asym-body-part 0) asym-body-part))
    (is (= (nth-matching-part asym-body-part 1) nth1-body-part))
    (is (= (nth-matching-part asym-body-part 2) nth2-body-part))))
