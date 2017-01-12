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

(defn mapset
  "Creates a set from applying a function to a coll."
  [fun my-coll]
  (set (map fun my-coll)))

; "Hobbit violence"
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))


;Exercise 3.5

(defn match-to
  [part part-that-matches]
  {:name (clojure.string/replace (:name part) #"^left-" part-that-matches)
   :size (:size part)})

(defn multiply-by-five-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (match-to part "right-")(match-to part "forward-")(match-to part "backward-")(match-to part "fifth-")])))
          []
          asym-body-parts))


;Exercise 3.6

(defn multiply-body-parts
  "Expects a seq of maps that have a :name and a :size and a number to multiply by"
  [asym-body-parts times]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matsh-to part)])))
          []
          asym-body-parts))
