(ns doll-smuggler.doll_reader-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.doll_reader :refer :all :as reader]))


(comment "Sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest yaml-read_sample-data
  (testing 
    "YAML Parsing of sample data from problem statement (test.yml)"
    
  (let [doll-data (reader/read-yaml "resources/test.yml")]
  (is (= "luke"    ((nth doll-data 0) :name)))
  (is (= 9         ((nth doll-data 0) :weight)))
  (is (= 150       ((nth doll-data 0) :value)))
  (is (= "anthony" ((nth doll-data 1) :name)))
  (is (= 13        ((nth doll-data 1) :weight)))
  (is (= 35        ((nth doll-data 1) :value)))
  (is (= "candice" ((nth doll-data 2) :name)))
  (is (= 153       ((nth doll-data 2) :weight)))
  (is (= 200       ((nth doll-data 2) :value)))
  (is (= "dorothy" ((nth doll-data 3) :name)))
  (is (= 50        ((nth doll-data 3) :weight)))
  (is (= 160       ((nth doll-data 3) :value)))
  (is (= "puppy"   ((nth doll-data 4) :name)))
  (is (= 15        ((nth doll-data 4) :weight)))
  (is (= 60        ((nth doll-data 4) :value)))
  (is (= "thomas"  ((nth doll-data 5) :name)))
  (is (= 68        ((nth doll-data 5) :weight)))
  (is (= 45        ((nth doll-data 5) :value)))
  (is (= "randal"  ((nth doll-data 6) :name)))
  (is (= 27        ((nth doll-data 6) :weight)))
  (is (= 60        ((nth doll-data 6) :value)))
  (is (= "april"   ((nth doll-data 7) :name)))
  (is (= 39        ((nth doll-data 7) :weight)))
  (is (= 40        ((nth doll-data 7) :value)))
  (is (= "nancy"   ((nth doll-data 8) :name)))
  (is (= 23        ((nth doll-data 8) :weight)))
  (is (= 30        ((nth doll-data 8) :value)))
  (is (= "bonnie"  ((nth doll-data 9) :name)))
  (is (= 52        ((nth doll-data 9) :weight)))
  (is (= 10        ((nth doll-data 9) :value)))
  (is (= "marc"    ((nth doll-data 10) :name)))
  (is (= 11        ((nth doll-data 10) :weight)))
  (is (= 70        ((nth doll-data 10) :value)))
  (is (= "kate"    ((nth doll-data 11) :name)))
  (is (= 32        ((nth doll-data 11) :weight)))
  (is (= 30        ((nth doll-data 11) :value))))))