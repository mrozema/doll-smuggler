(ns doll-smuggler.doll_reader
  (:require [clj-yaml.core :as yaml]))

(defn read-yaml [path]
  "Loads YAML file containing the doll data"
  (get (yaml/parse-string (slurp path)) :DOLLS))