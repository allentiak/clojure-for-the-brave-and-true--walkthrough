(ns clojure-noob.core
  (:gen-class))


;;; Chapter 3

;; Exercise 3.2
(defn add100
  "Adds 100 to the number passed as arg"
  [number]
  (+ number 100))

;; Exercise 3.3
(defn dec-maker
  "Creates a custom decrementer"
  [decrement-by]
  #(- % decrement-by))

;; Exercise 3.4
(defn mapset
  "Creates a set from applying a function to a coll."
  [fun my-coll]
  (set (map fun my-coll)))


;; "Hobbit violence"

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


;; Exercise 3.5

(defn match-to
  [part part-that-matches]
  {:name (clojure.string/replace (:name part) #"^left-" part-that-matches)
   :size (:size part)})

(defn multiply-by-five-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts
                  (into #{part}
                        (map (partial match-to part) '("right-" "forward-" "backward-" "fifth-")))))
          []
          asym-body-parts))


;; Exercise 3.6

(defn nth-matching-part
  "Expects the original part and the absolute nth to match. 0 matches to self."
  [part n]
  (if (zero? n) part
      (match-to part (str (Math/abs n) "th-"))))

(defn multiply-body-parts
  "Expects a seq of maps that have a :name and a :size and a number to multiply by. 0 or negative returns an empty list. 1 returns the part."
  [asym-body-parts times]
  (cond
    (< times 0) nil
    (= times 0) nil
    (= times 1) asym-body-parts
    (> times 1) (reduce (fn [final-body-parts part]
                          (into final-body-parts
                                (into #{part}
                                      (map (partial nth-matching-part part) (range 2 (inc times))))))
                  []
                  asym-body-parts)))



;;; Chapter 5


;; "Character"

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))
