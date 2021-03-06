(ns doll-smuggler.core
  (:require [clj-yaml.core :as yaml]
            [doll-smuggler.doll_reader :as reader]
            [clojure.string :as string]
            [clojure.pprint :as pprint :refer [pprint print-table]]
            [clojure.inspector :refer [inspect-table]]
            [clojure.java.io :refer [as-file]]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

;; Forward declare memoized version
(declare m-find-dolls)

;; Program CLI options
(def cli-options
  [
   ["-w" "--weight WEIGHT" "Maximum total weight (in lbs) for the dolls"
    :id :max-weight
    :default 400
    :default-desc "Default: 400 lbs"
    :parse-fn #(Integer/parseInt %1)
    :validate [#(> %1 0) "Must be a positive number"]]
   ["-f" "--file FILEPATH" "Input file path (YAML). E.g. drug-stuff.yaml"
    :id :file
    :default "resources/doll-input.yml"
    :default-desc "Default: doll-input.yml"
    :validate [#(.exists (as-file %1)) "File does not exist!"]]
   [nil "--chart" "Create a graphical chart of the calculated data"
    :id :chart
    :default-desc "Displays the results in a nice GUI"]
   ["-h" "--help"]])

(defn usage
  "Return usage string"
  [options-summary]
  (->> ["Welcome, Drug Smuggler."
        "I will help you find the optimum doll set for your mule!"
        ""
        "Usage: lein run -m doll-smuggler.core [OPTS]"
        ""
        "Options:"
        options-summary
        ""
        "Please refer to the README for more information."]
       (string/join \newline)))

(defn error-msg 
  "Print error message"
  [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn exit
  "Exit and print a status"
  [status msg]
  (println msg)
  (System/exit status))

(defn find-dolls 
  "Finds the optimimum dolls, given a maximum weight and a source doll set
  Algorithm based off [this] (http://rosettacode.org/wiki/Knapsack_problem/0-1#Clojure)"
  [idx weight dolls]
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
  "Calculates the total weight of the doll collection"
  [dolls]
  (get (apply merge-with + (for [x dolls] (select-keys x [:weight]))) :weight))

(defn filter-by-index
  "Using an input vector of list indexes, return a subset comprised of the
  desired indexes"
  [coll idx]
  (map (partial nth coll) idx))

(defn print-results
  "Print retults using pretty-print's 'print-table', sorting by doll name"
  [results]
  (print-table [:name :weight :value] (sort-by :name results)))

(defn -main
  "Parse command line options and calculate the doll set"
  [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    ;; Handle help and error conditions
    (cond
      (:help options) (exit 0 (usage summary))
      (not= (count arguments) 0) (exit 1 (usage summary))
      errors (exit 1 (error-msg errors)))
    ;; Run the program
    (let [doll-data (reader/read-yaml (get options :file))]
      (let [[value indexes] (find-dolls (-> doll-data count dec) (get options :max-weight) doll-data)
            names (filter-by-index doll-data indexes)]
        (println "Dolls to pack:")
        (print-results names)
        (println "-------------------------------")
        (println "Total Value:" value)
        (println "Total Weight:" (total-weight names))
        (if (get options :chart) 
          (inspect-table names) 
          (exit 0 "Good Luck! Don't get caught..."))))))

