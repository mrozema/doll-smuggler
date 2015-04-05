(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

(def sample-dolls 
  ( list
  { :name "luke"    :weight 9   :value 150} { :name "anthony"  :weight 13 :value 35 }  
  { :name "candice" :weight 153 :value 200} { :name "dorothy"  :weight 50 :value 160} 
  { :name "puppy"   :weight 15  :value 60 } { :name "thomas"   :weight 68 :value 45 }  
  { :name "randal"  :weight 27  :value 60 } { :name "april"    :weight 39 :value 40 } 
  { :name "nancy"   :weight 23  :value 30 } { :name "bonnie"   :weight 52 :value 10 }  
  { :name "marc"    :weight 11  :value 70 } { :name "kate"     :weight 32 :value 30 } 
  { :name "tbone"   :weight 24  :value 15 } { :name "tranny"   :weight 48 :value 10 }  
  { :name "uma"     :weight 73  :value 40 } { :name "grumpkin" :weight 42 :value 70 } 
  { :name "dusty"   :weight 43  :value 75 } { :name "grumpy"   :weight 22 :value 80 }  
  { :name "eddie"   :weight 7   :value 20 } { :name "tory"     :weight 18 :value 12 } 
  { :name "sally"   :weight 4   :value 50 } { :name "babe"     :weight 30 :value 10 }))

(def sample-dolls-answer
  (list
  {:name "sally"    :weight 4   :value 50 } 
  {:name "eddie"    :weight 7   :value 20 }
  {:name "grumpy"   :weight 22  :value 80 } 
  {:name "dusty"    :weight 43  :value 75 }
  {:name "grumpkin" :weight 42  :value 70 } 
  {:name "marc"     :weight 11  :value 70 } 
  {:name "randal"   :weight 27  :value 60 } 
  {:name "puppy"    :weight 15  :value 60 } 
  {:name "dorothy"  :weight 50  :value 160} 
  {:name "candice"  :weight 153 :value 200} 
  {:name "anthony"  :weight 13  :value 35 } 
  {:name "luke"     :weight 9   :value 150}))


(comment "This test uses sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest find-dolls-sample
  (testing 
    "Test the find-dolls algorithm against the sample data"
    (let [[value indexes] (find-dolls (-> sample-dolls count dec) 400 sample-dolls)]
      ;; Make the vecor into a set because we don't care about the order
      (is (= (set indexes) #{0 1 2 3 4 6 10 15 16 17 18 20}))
      (is (= value 1030)))))


(comment "This test uses sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest filter-by-index-sample
  (testing
    "Test the filter-by-index algorithm against the sample data"
      (is (= (set (filter-by-index sample-dolls [0 1 2 3 4 6 10 15 16 17 18 20]))
             (set sample-dolls-answer)))))


(comment "This test uses sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest total-weight-sample
  (testing
    "Test the total-weight function agains the sample data"
    (is (= 396 (total-weight sample-dolls-answer)))))

