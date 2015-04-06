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


(comment "Tests the find-dolls algorithm against a large data set")
(deftest find-dolls-big-set
  (testing 
    "Test the find-dolls algorithm against a large data set"
    (let [[value indexes] (find-dolls (-> big-data count dec) 5000 big-data)]
      ;; Make the vecor into a set because we don't care about the order
      (is (= (set indexes) #{6 8 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                             27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 
                             44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 
                             61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 
                             78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 
                             95 96 97 98 99}))
      (is (= value 84119)))))

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
    {:name  "Name1"    :weight 1    :value  0    }
    {:name  "Name2"    :weight 2    :value  1    }
    {:name  "Name3"    :weight 3    :value  2    }
    {:name  "Name4"    :weight 4    :value  3    }
    {:name  "Name5"    :weight 5    :value  4    }
    {:name  "Name6"    :weight 6    :value  5    }
    {:name  "Name7"    :weight 7    :value  6    }
    {:name  "Name8"    :weight 8    :value  7    }
    {:name  "Name9"    :weight 9    :value  8    }
    {:name  "Name10"   :weight 10   :value  9    }
    {:name  "Name11"   :weight 11   :value  10   }
    {:name  "Name12"   :weight 12   :value  989  }
    {:name  "Name13"   :weight 13   :value  988  }
    {:name  "Name14"   :weight 14   :value  987  }
    {:name  "Name15"   :weight 15   :value  986  }
    {:name  "Name16"   :weight 16   :value  985  }
    {:name  "Name17"   :weight 17   :value  984  }
    {:name  "Name18"   :weight 18   :value  983  }
    {:name  "Name19"   :weight 19   :value  982  }
    {:name  "Name20"   :weight 20   :value  981  }
    {:name  "Name21"   :weight 21   :value  980  }
    {:name  "Name22"   :weight 22   :value  979  }
    {:name  "Name23"   :weight 23   :value  978  }
    {:name  "Name24"   :weight 24   :value  977  }
    {:name  "Name25"   :weight 25   :value  976  }
    {:name  "Name26"   :weight 26   :value  975  }
    {:name  "Name27"   :weight 27   :value  974  }
    {:name  "Name28"   :weight 28   :value  973  }
    {:name  "Name29"   :weight 29   :value  972  }
    {:name  "Name30"   :weight 30   :value  971  }
    {:name  "Name31"   :weight 31   :value  970  }
    {:name  "Name32"   :weight 32   :value  969  }
    {:name  "Name33"   :weight 33   :value  968  }
    {:name  "Name34"   :weight 34   :value  967  }
    {:name  "Name35"   :weight 35   :value  966  }
    {:name  "Name36"   :weight 36   :value  965  }
    {:name  "Name37"   :weight 37   :value  964  }
    {:name  "Name38"   :weight 38   :value  963  }
    {:name  "Name39"   :weight 39   :value  962  }
    {:name  "Name40"   :weight 40   :value  961  }
    {:name  "Name41"   :weight 41   :value  960  }
    {:name  "Name42"   :weight 42   :value  959  }
    {:name  "Name43"   :weight 43   :value  958  }
    {:name  "Name44"   :weight 44   :value  957  }
    {:name  "Name45"   :weight 45   :value  956  }
    {:name  "Name46"   :weight 46   :value  955  }
    {:name  "Name47"   :weight 47   :value  954  }
    {:name  "Name48"   :weight 48   :value  953  }
    {:name  "Name49"   :weight 49   :value  952  }
    {:name  "Name50"   :weight 50   :value  951  }
    {:name  "Name51"   :weight 51   :value  950  }
    {:name  "Name52"   :weight 52   :value  949  }
    {:name  "Name53"   :weight 53   :value  948  }
    {:name  "Name54"   :weight 54   :value  947  }
    {:name  "Name55"   :weight 55   :value  946  }
    {:name  "Name56"   :weight 56   :value  945  }
    {:name  "Name57"   :weight 57   :value  944  }
    {:name  "Name58"   :weight 58   :value  943  }
    {:name  "Name59"   :weight 59   :value  942  }
    {:name  "Name60"   :weight 60   :value  941  }
    {:name  "Name61"   :weight 61   :value  940  }
    {:name  "Name62"   :weight 62   :value  939  }
    {:name  "Name63"   :weight 63   :value  938  }
    {:name  "Name64"   :weight 64   :value  937  }
    {:name  "Name65"   :weight 65   :value  936  }
    {:name  "Name66"   :weight 66   :value  935  }
    {:name  "Name67"   :weight 67   :value  934  }
    {:name  "Name68"   :weight 68   :value  933  }
    {:name  "Name69"   :weight 69   :value  932  }
    {:name  "Name70"   :weight 70   :value  931  }
    {:name  "Name71"   :weight 71   :value  930  }
    {:name  "Name72"   :weight 72   :value  929  }
    {:name  "Name73"   :weight 73   :value  928  }
    {:name  "Name74"   :weight 74   :value  927  }
    {:name  "Name75"   :weight 75   :value  926  }
    {:name  "Name76"   :weight 76   :value  925  }
    {:name  "Name77"   :weight 77   :value  924  }
    {:name  "Name78"   :weight 78   :value  923  }
    {:name  "Name79"   :weight 79   :value  922  }
    {:name  "Name80"   :weight 80   :value  921  }
    {:name  "Name81"   :weight 81   :value  920  }
    {:name  "Name82"   :weight 82   :value  919  }
    {:name  "Name83"   :weight 83   :value  918  }
    {:name  "Name84"   :weight 84   :value  917  }
    {:name  "Name85"   :weight 85   :value  916  }
    {:name  "Name86"   :weight 86   :value  915  }
    {:name  "Name87"   :weight 87   :value  914  }
    {:name  "Name88"   :weight 88   :value  913  }
    {:name  "Name89"   :weight 89   :value  912  }
    {:name  "Name90"   :weight 90   :value  911  }
    {:name  "Name91"   :weight 91   :value  910  }
    {:name  "Name92"   :weight 92   :value  909  }
    {:name  "Name93"   :weight 93   :value  908  }
    {:name  "Name94"   :weight 94   :value  907  }
    {:name  "Name95"   :weight 95   :value  906  }
    {:name  "Name96"   :weight 96   :value  905  }
    {:name  "Name97"   :weight 97   :value  904  }
    {:name  "Name98"   :weight 98   :value  903  }
    {:name  "Name99"   :weight 99   :value  902  }
    {:name  "Name100"  :weight 100  :value  901  }
    {:name  "Name101"  :weight 101  :value  900  }
    {:name  "Name102"  :weight 102  :value  899  }
    {:name  "Name103"  :weight 103  :value  898  }
    {:name  "Name104"  :weight 104  :value  897  }
    {:name  "Name105"  :weight 105  :value  896  }
    {:name  "Name106"  :weight 106  :value  895  }
    {:name  "Name107"  :weight 107  :value  894  }
    {:name  "Name108"  :weight 108  :value  893  }
    {:name  "Name109"  :weight 109  :value  892  }
    {:name  "Name110"  :weight 110  :value  891  }
    {:name  "Name111"  :weight 111  :value  890  }
    {:name  "Name112"  :weight 112  :value  889  }
    {:name  "Name113"  :weight 113  :value  888  }
    {:name  "Name114"  :weight 114  :value  887  }
    {:name  "Name115"  :weight 115  :value  886  }
    {:name  "Name116"  :weight 116  :value  885  }
    {:name  "Name117"  :weight 117  :value  884  }
    {:name  "Name118"  :weight 118  :value  883  }
    {:name  "Name119"  :weight 119  :value  882  }
    {:name  "Name120"  :weight 120  :value  881  }
    {:name  "Name121"  :weight 121  :value  880  }
    {:name  "Name122"  :weight 122  :value  879  }
    {:name  "Name123"  :weight 123  :value  878  }
    {:name  "Name124"  :weight 124  :value  877  }
    {:name  "Name125"  :weight 125  :value  876  }
    {:name  "Name126"  :weight 126  :value  875  }
    {:name  "Name127"  :weight 127  :value  874  }
    {:name  "Name128"  :weight 128  :value  873  }
    {:name  "Name129"  :weight 129  :value  872  }
    {:name  "Name130"  :weight 130  :value  871  }
    {:name  "Name131"  :weight 131  :value  870  }
    {:name  "Name132"  :weight 132  :value  869  }
    {:name  "Name133"  :weight 133  :value  868  }
    {:name  "Name134"  :weight 134  :value  867  }
    {:name  "Name135"  :weight 135  :value  866  }
    {:name  "Name136"  :weight 136  :value  865  }
    {:name  "Name137"  :weight 137  :value  864  }
    {:name  "Name138"  :weight 138  :value  863  }
    {:name  "Name139"  :weight 139  :value  862  }
    {:name  "Name140"  :weight 140  :value  861  }
    {:name  "Name141"  :weight 141  :value  860  }
    {:name  "Name142"  :weight 142  :value  859  }
    {:name  "Name143"  :weight 143  :value  858  }
    {:name  "Name144"  :weight 144  :value  857  }
    {:name  "Name145"  :weight 145  :value  856  }
    {:name  "Name146"  :weight 146  :value  855  }
    {:name  "Name147"  :weight 147  :value  854  }
    {:name  "Name148"  :weight 148  :value  853  }
    {:name  "Name149"  :weight 149  :value  852  }
    {:name  "Name150"  :weight 150  :value  851  }
    {:name  "Name151"  :weight 151  :value  850  }
    {:name  "Name152"  :weight 152  :value  849  }
    {:name  "Name153"  :weight 153  :value  848  }
    {:name  "Name154"  :weight 154  :value  847  }
    {:name  "Name155"  :weight 155  :value  846  }
    {:name  "Name156"  :weight 156  :value  845  }
    {:name  "Name157"  :weight 157  :value  844  }
    {:name  "Name158"  :weight 158  :value  843  }
    {:name  "Name159"  :weight 159  :value  842  }
    {:name  "Name160"  :weight 160  :value  841  }
    {:name  "Name161"  :weight 161  :value  840  }
    {:name  "Name162"  :weight 162  :value  839  }
    {:name  "Name163"  :weight 163  :value  838  }
    {:name  "Name164"  :weight 164  :value  837  }
    {:name  "Name165"  :weight 165  :value  836  }
    {:name  "Name166"  :weight 166  :value  835  }
    {:name  "Name167"  :weight 167  :value  834  }
    {:name  "Name168"  :weight 168  :value  833  }
    {:name  "Name169"  :weight 169  :value  832  }
    {:name  "Name170"  :weight 170  :value  831  }
    {:name  "Name171"  :weight 171  :value  830  }
    {:name  "Name172"  :weight 172  :value  829  }
    {:name  "Name173"  :weight 173  :value  828  }
    {:name  "Name174"  :weight 174  :value  827  }
    {:name  "Name175"  :weight 175  :value  826  }
    {:name  "Name176"  :weight 176  :value  825  }
    {:name  "Name177"  :weight 177  :value  824  }
    {:name  "Name178"  :weight 178  :value  823  }
    {:name  "Name179"  :weight 179  :value  822  }
    {:name  "Name180"  :weight 180  :value  821  }
    {:name  "Name181"  :weight 181  :value  820  }
    {:name  "Name182"  :weight 182  :value  819  }
    {:name  "Name183"  :weight 183  :value  818  }
    {:name  "Name184"  :weight 184  :value  817  }
    {:name  "Name185"  :weight 185  :value  816  }
    {:name  "Name186"  :weight 186  :value  815  }
    {:name  "Name187"  :weight 187  :value  814  }
    {:name  "Name188"  :weight 188  :value  813  }
    {:name  "Name189"  :weight 189  :value  812  }
    {:name  "Name190"  :weight 190  :value  811  }
    {:name  "Name191"  :weight 191  :value  810  }
    {:name  "Name192"  :weight 192  :value  809  }
    {:name  "Name193"  :weight 193  :value  808  }
    {:name  "Name194"  :weight 194  :value  807  }
    {:name  "Name195"  :weight 195  :value  806  }
    {:name  "Name196"  :weight 196  :value  805  }
    {:name  "Name197"  :weight 197  :value  804  }
    {:name  "Name198"  :weight 198  :value  803  }
    {:name  "Name199"  :weight 199  :value  802  }
    {:name  "Name200"  :weight 200  :value  801  }))