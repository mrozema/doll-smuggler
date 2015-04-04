(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

(def sample_dolls 
  [
   ["luke" 9 150]  ["anthony" 13 35] ["candice" 153 200] ["dorothy" 50 160] 
   ["puppy" 15 60] ["thomas" 68 45]  ["randal" 27 60]    ["april" 39 40] 
   ["nancy" 23 30] ["bonnie" 52 10]  ["marc" 11 70]      ["kate" 32 30] 
   ["tbone" 24 15] ["tranny" 48 10]  ["uma" 73 40]       ["grumpkin" 42 70] 
   ["dusty" 43 75] ["grumpy" 22 80]  ["eddie" 7 20]      ["tory" 18 12] 
   ["sally" 4 50]  ["babe" 30 10]
  ])

(comment "Sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest sample-data
  (testing 
    "Sample data"
    (is
      (= 
        ((sample_dolls 0) 0) "luke"))))

