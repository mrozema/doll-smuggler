(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

(def sample-dolls 
  [{ :name "luke",    :weight 9,   :value 150} { :name "anthony",  :weight 13, :value 35 }  
   { :name "candice" :weight 153 :value 200} { :name "dorothy"  :weight 50 :value 160} 
   { :name "puppy"   :weight 15  :value 60 } { :name "thomas"   :weight 68 :value 45 }  
   { :name "randal"  :weight 27  :value 60 } { :name "april"    :weight 39 :value 40 } 
   { :name "nancy"   :weight 23  :value 30 } { :name "bonnie"   :weight 52 :value 10 }  
   { :name "marc"    :weight 11  :value 70 } { :name "kate"     :weight 32 :value 30 } 
   { :name "tbone"   :weight 24  :value 15 } { :name "tranny"   :weight 48 :value 10 }  
   { :name "uma"     :weight 73  :value 40 } { :name "grumpkin" :weight 42 :value 70 } 
   { :name "dusty"   :weight 43  :value 75 } { :name "grumpy"   :weight 22 :value 80 }  
   { :name "eddie"   :weight 7   :value 20 } { :name "tory"     :weight 18 :value 12 } 
   { :name "sally"   :weight 4   :value 50 } { :name "babe"     :weight 30 :value 10 }])

(comment "Sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest yaml-read_sample-data
  (testing 
    "Test the algorithm against the sample data"
    
  (is (= 
        (find-dolls sample-dolls 400)
        [["sally" 4 50] ["eddie" 7 20] ["grumpy" 22 80] ["dusty" 43 75] 
         ["grumpkin" 42 70] ["marc" 11 70] ["randal" 27 60] ["puppy" 15 60] 
         ["dorothy" 50 160] ["candice" 153 200] ["anthony" 13 35] ["luke" 9 150]]))))




