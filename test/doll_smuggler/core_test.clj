(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

;; big data set is near the EOF
(declare big-data)

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

;; "Data set from: http://www.nitjsr.ac.in/faculty/courses/CA03_2_0-1%20Knapsack%20Problem.pdf
(def small-set
  (list
    {:name "1" :weight 1 :value 15}
    {:name "2" :weight 5 :value 10}
    {:name "3" :weight 3 :value 9 }
    {:name "4" :weight 4 :value 5 }))

(def small-set-answer
  (list
    {:name "1" :weight 1 :value 15}
    {:name "3" :weight 3 :value 9 }
    {:name "4" :weight 4 :value 5 }))

(comment "This test uses sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest find-dolls-sample
  (testing 
    "Test the find-dolls algorithm against the sample data"
    (let [[value indexes] (find-dolls (-> sample-dolls count dec) 400 sample-dolls)]
      ;; Make the vecor into a set because we don't care about the order
      (is (= (set indexes) #{0 1 2 3 4 6 10 15 16 17 18 20}))
      (is (= value 1030)))))

(comment "Tests the find-dolls algorithm against some human-verifiable data")
(deftest find-dolls-small-set
  (testing
    "Test the find-dolls algorithm against a small, human-verifiable set"
    (let [[value indexes] (find-dolls (-> small-set count dec) 8 small-set)]
      (is (= (set indexes) #{0 2 3}))
      (is (= value 29)))))


(comment "Tests the find-dolls algorithm against a larger data set")
(deftest find-dolls-big-set
  (testing 
    "Test the find-dolls algorithm against a larger data set"
    (let [[value indexes] (find-dolls (-> big-data count dec) 2000 big-data)]
      ;; Make the vecor into a set because we don't care about the order
      (is (= (set indexes) #{0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 16 17 18 19 20 21
                             22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39
                             40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57
                             58 59 60 61 62}))
      ;; Since weight = value, the value should be 2000 also
      (is (= value 2000)))))

(comment "This test uses sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest filter-by-index-sample
  (testing
    "Test the filter-by-index algorithm against the sample data"
      (is (= (set (filter-by-index sample-dolls [0 1 2 3 4 6 10 15 16 17 18 20]))
             (set sample-dolls-answer)))))

(comment "Tests the filter-by-index algorithm against some human-verifiable data")
(deftest filter-by-index-small-set
  (testing
    "Test the filter-by-index algorithm against some human-verifiable data"
      (is (= (set (filter-by-index small-set [0 2 3]))
             (set small-set-answer)))))

(comment "Tests filter-by-index with an empty filter vector")
(deftest filter-by-index-no-vector
  (testing
    "Tests filter-by-index function with an empty filter vector"
      (is (= (set (filter-by-index sample-dolls []))
             #{}))))

(comment "This test uses sample data set from problem description:
          https://github.com/micahalles/doll-smuggler")
(deftest total-weight-sample
  (testing
    "Test the total-weight function against the sample data"
    (is (= 396 (total-weight sample-dolls-answer)))))

(comment "Test the return of total-weight with an empty list")
(deftest total-weight-empty
  (testing
    "Test the total-weight function with an empty list"
      (is (= nil (total-weight (list))))))

(comment "Test the return of total-weight when the list has no elements with a
          :weight attribute")
(deftest total-weight-no-weight
  (testing
    "Test the total-weight function with when the list has no elements with :weight"
      (is (= nil (total-weight (list {:foo 1}))))))

(def big-data
  (list
    {:name "Name1"   :weight 1   :value 1   } 
    {:name "Name2"   :weight 2   :value 2   } 
    {:name "Name3"   :weight 3   :value 3   } 
    {:name "Name4"   :weight 4   :value 4   } 
    {:name "Name5"   :weight 5   :value 5   } 
    {:name "Name6"   :weight 6   :value 6   } 
    {:name "Name7"   :weight 7   :value 7   } 
    {:name "Name8"   :weight 8   :value 8   } 
    {:name "Name9"   :weight 9   :value 9   } 
    {:name "Name10"  :weight 10  :value 10  }
    {:name "Name11"  :weight 11  :value 11  }
    {:name "Name12"  :weight 12  :value 12  }
    {:name "Name13"  :weight 13  :value 13  }
    {:name "Name14"  :weight 14  :value 14  }
    {:name "Name15"  :weight 15  :value 15  }
    {:name "Name16"  :weight 16  :value 16  }
    {:name "Name17"  :weight 17  :value 17  }
    {:name "Name18"  :weight 18  :value 18  }
    {:name "Name19"  :weight 19  :value 19  }
    {:name "Name20"  :weight 20  :value 20  }
    {:name "Name21"  :weight 21  :value 21  }
    {:name "Name22"  :weight 22  :value 22  }
    {:name "Name23"  :weight 23  :value 23  }
    {:name "Name24"  :weight 24  :value 24  }
    {:name "Name25"  :weight 25  :value 25  }
    {:name "Name26"  :weight 26  :value 26  }
    {:name "Name27"  :weight 27  :value 27  }
    {:name "Name28"  :weight 28  :value 28  }
    {:name "Name29"  :weight 29  :value 29  }
    {:name "Name30"  :weight 30  :value 30  }
    {:name "Name31"  :weight 31  :value 31  }
    {:name "Name32"  :weight 32  :value 32  }
    {:name "Name33"  :weight 33  :value 33  }
    {:name "Name34"  :weight 34  :value 34  }
    {:name "Name35"  :weight 35  :value 35  }
    {:name "Name36"  :weight 36  :value 36  }
    {:name "Name37"  :weight 37  :value 37  }
    {:name "Name38"  :weight 38  :value 38  }
    {:name "Name39"  :weight 39  :value 39  }
    {:name "Name40"  :weight 40  :value 40  }
    {:name "Name41"  :weight 41  :value 41  }
    {:name "Name42"  :weight 42  :value 42  }
    {:name "Name43"  :weight 43  :value 43  }
    {:name "Name44"  :weight 44  :value 44  }
    {:name "Name45"  :weight 45  :value 45  }
    {:name "Name46"  :weight 46  :value 46  }
    {:name "Name47"  :weight 47  :value 47  }
    {:name "Name48"  :weight 48  :value 48  }
    {:name "Name49"  :weight 49  :value 49  }
    {:name "Name50"  :weight 50  :value 50  }
    {:name "Name51"  :weight 51  :value 51  }
    {:name "Name52"  :weight 52  :value 52  }
    {:name "Name53"  :weight 53  :value 53  }
    {:name "Name54"  :weight 54  :value 54  }
    {:name "Name55"  :weight 55  :value 55  }
    {:name "Name56"  :weight 56  :value 56  }
    {:name "Name57"  :weight 57  :value 57  }
    {:name "Name58"  :weight 58  :value 58  }
    {:name "Name59"  :weight 59  :value 59  }
    {:name "Name60"  :weight 60  :value 60  }
    {:name "Name61"  :weight 61  :value 61  }
    {:name "Name62"  :weight 62  :value 62  }
    {:name "Name63"  :weight 63  :value 63  }
    {:name "Name64"  :weight 64  :value 64  }
    {:name "Name65"  :weight 65  :value 65  }
    {:name "Name66"  :weight 66  :value 66  }
    {:name "Name67"  :weight 67  :value 67  }
    {:name "Name68"  :weight 68  :value 68  }
    {:name "Name69"  :weight 69  :value 69  }
    {:name "Name70"  :weight 70  :value 70  }
    {:name "Name71"  :weight 71  :value 71  }
    {:name "Name72"  :weight 72  :value 72  }
    {:name "Name73"  :weight 73  :value 73  }
    {:name "Name74"  :weight 74  :value 74  }
    {:name "Name75"  :weight 75  :value 75  }
    {:name "Name76"  :weight 76  :value 76  }
    {:name "Name77"  :weight 77  :value 77  }
    {:name "Name78"  :weight 78  :value 78  }
    {:name "Name79"  :weight 79  :value 79  }
    {:name "Name80"  :weight 80  :value 80  }
    {:name "Name81"  :weight 81  :value 81  }
    {:name "Name82"  :weight 82  :value 82  }
    {:name "Name83"  :weight 83  :value 83  }
    {:name "Name84"  :weight 84  :value 84  }
    {:name "Name85"  :weight 85  :value 85  }
    {:name "Name86"  :weight 86  :value 86  }
    {:name "Name87"  :weight 87  :value 87  }
    {:name "Name88"  :weight 88  :value 88  }
    {:name "Name89"  :weight 89  :value 89  }
    {:name "Name90"  :weight 90  :value 90  }
    {:name "Name91"  :weight 91  :value 91  }
    {:name "Name92"  :weight 92  :value 92  }
    {:name "Name93"  :weight 93  :value 93  }
    {:name "Name94"  :weight 94  :value 94  }
    {:name "Name95"  :weight 95  :value 95  }
    {:name "Name96"  :weight 96  :value 96  }
    {:name "Name97"  :weight 97  :value 97  }
    {:name "Name98"  :weight 98  :value 98  }
    {:name "Name99"  :weight 99  :value 99  }
    {:name "Name100" :weight 100 :value 100 }))
  
