(ns doll-smuggler.core
  (:require [clj-yaml.core :as yaml]
            [doll-smuggler.doll_reader :as reader]
            [clojure.pprint :as pprint :refer [pprint print-table]])
  (:gen-class))

(use '[clojure.string :only [join]])
(use 'clojure.inspector)

(declare m-find-dolls)

(defn find-dolls [idx weight dolls]
   (cond
    (< idx 0) [0 []]
    (= weight 0) [0 []]
    :else
    (let [{doll-weight :weight doll-value :value} (nth dolls idx)]
      (if (> doll-weight weight)
        (m-find-dolls (dec idx) weight dolls)
        (let [[vn sn :as no]  (m-find-dolls (dec idx) weight dolls)
              [vy sy :as yes] (m-find-dolls (dec idx) (- weight doll-weight) dolls)]
          (if (> (+ vy doll-value) vn)
            [(+ vy doll-value) (conj sy idx)]
            no))))))
 
(def m-find-dolls (memoize find-dolls))

(defn total-weight
  [dolls]
  (get (apply merge-with + (for [x dolls] (select-keys x [:weight]))) :weight))

(defn filter-by-index 
  [coll idx]
  (map (partial nth coll) idx))

(defn print-results
  [results]
  (print-table [:name :weight :value] (sort-by :name results)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [doll-data (reader/read-yaml "resources/test.yml")]
    (let [[value indexes] (find-dolls (-> doll-data count dec) 400 doll-data)
      names (filter-by-index doll-data indexes)]
      (println "Dolls to pack:")
      (print-results names)
      (println "-------------------------------")
      (println "Total Value:" value)
      (println "Total Weight:" (total-weight names))
      (inspect-table names))))

