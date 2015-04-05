(ns doll-smuggler.doll_reader-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.doll_reader :refer :all :as reader]))


(comment "Sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest yaml-read_sample-data
  (testing 
    "YAML Parsing of sample data from problem statement (test.yml)"
  (let [doll-data (reader/read-yaml "resources/test.yml")]
  (is (= {:name "luke"    :weight 9   :value 150} (nth doll-data 0)))
  (is (= {:name "anthony" :weight 13  :value 35 } (nth doll-data 1)))
  (is (= {:name "candice" :weight 153 :value 200} (nth doll-data 2)))
  (is (= {:name "dorothy" :weight 50  :value 160} (nth doll-data 3)))
  (is (= {:name "puppy"   :weight 15  :value 60 } (nth doll-data 4)))
  (is (= {:name "thomas"  :weight 68  :value 45 } (nth doll-data 5)))
  (is (= {:name "randal"  :weight 27  :value 60 } (nth doll-data 6)))
  (is (= {:name "april"   :weight 39  :value 40 } (nth doll-data 7)))
  (is (= {:name "nancy"   :weight 23  :value 30 } (nth doll-data 8)))
  (is (= {:name "bonnie"  :weight 52  :value 10 } (nth doll-data 9)))
  (is (= {:name "marc"    :weight 11  :value 70 } (nth doll-data 10)))
  (is (= {:name "kate"    :weight 32  :value 30 } (nth doll-data 11))))))