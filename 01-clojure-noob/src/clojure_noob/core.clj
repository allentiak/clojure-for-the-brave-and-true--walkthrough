(ns clojure-noob.core
  (:gen-class))

(defn add100
  "Adds 100 to the number passed as arg"
  [number]
  (+ number 100))

(defn dec-maker
  "Creates a custom decrementer"
  [decrement-by]
  #(- % decrement-by))

