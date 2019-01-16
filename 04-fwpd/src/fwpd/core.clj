(ns fwpd.core)

(defn str->int
  [str]
  (Integer. str))

(defn convert
  [vamp-key value]
  (let [conversions {:name identity
                     :glitter-index str->int}]
    ((get conversions vamp-key) value)))

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
                 (map vector [:name :glitter-index] unmapped-row)))
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

(defn int->str
  [integer]
  (str integer))

(defn deconvert
  [vamp-key value]
  (let [deconversions {:name identity
                           :glitter-index int->str}]
    ((get deconversions vamp-key) value)))

(defn unmapify
  "Convert a seq of record maps into a seq of rows of columns"
  [map-seq]
  (def keys-vector (keys (first map-seq)))
  ;; FIXME: consider using reduce?
  (map (fn [map-element] (vec (map deconvert keys-vector (map (fn [key] (key map-element)) keys-vector)))) map-seq))

(defn unparse
  "Convert a rows of columns into a CSV"
  [vector-seq]
  (clojure.string/join "\n" (map #(clojure.string/join "," %) vector-seq)))
