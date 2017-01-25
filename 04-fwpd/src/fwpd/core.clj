(ns fwpd.core)

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "Convert a seq of rows (vectors) of string pairs into a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

;; Exercise 4.1
(defn glitter-filter
  "Filter a seq of record maps, returning those with glitter-index higher than minimum-glitter"
  [minimum-glitter records]
  (map :name (filter #(>= (:glitter-index %) minimum-glitter) records)))

;; Exercise 4.2
(defn append
  [suspects-list {:keys [name glitter-index] :as new-suspect}]
  (conj suspects-list new-suspect))

;; Exercise 4.3
(defn validate?
  "Verify whether a record contains all of its keywords"
  [keywords-list record]
  (every? #(true? %) (vec (map #(false? (nil? (% record))) keywords-list))))


;; Exercise 4.4

(def deconversions {:name identity
                    :glitter-index int->str})
(defn deconvert
  [vamp-key value]
  ((get deconversions vamp-key) value))

(defn int->str
  [integer]
  (str integer))

(defn unmapify
  "Convert a seq of record maps into a seq of rows of columns"
  [maps]
  ;; Guess the column headers
  (def keys-vector (keys (first maps)))
  (def my-record (first maps))
  (reduce (fn []
            (vec (map deconvert keys-vector (map #(%1 my-record) keys-vector))))
          ()))
