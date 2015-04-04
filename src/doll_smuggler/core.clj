(ns doll-smuggler.core
  (:require [clj-yaml.core :as yaml]
            [doll-smuggler.doll_reader :as reader])
  (:gen-class))

;; Define the doll structure
(defstruct doll :name :weight :value)

;(def dolls (vec (map #(apply struct doll %)
;                     (partition 3 doll-data))))

(defn find-dolls [idx weight]
  (printf "Hello!"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [doll-data (reader/read-yaml "resources/test.yml")]
    (find-dolls (-> doll-data count dec) 400)))

