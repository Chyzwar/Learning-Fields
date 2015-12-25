(defn unique-set
  "Find if collection is unique"
  [coll]
  (let [set-coll (into #{} coll)]
  	(= (count set-coll) (count coll))))
