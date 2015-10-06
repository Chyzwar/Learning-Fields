(ns t01spark.core
  (:gen-class))

(require '[flambo.api :as f]
         '[flambo.conf :as cf]
         '[flambo.tuple :as ft]
         '[clojure.string :as s])

(import '[org.apache.spark.mllib.linalg Vectors]
               '[org.apache.spark.mllib.regression LabeledPoint]
               '[org.apache.spark.mllib.classification LogisticRegressionWithLBFGS]
               '[org.apache.spark.mllib.evaluation BinaryClassificationMetrics])


(def spark
         (let [cfg (-> (cf/spark-conf)
                       (cf/master "local[2]")
                       (cf/app-name "t01spark")
                       (cf/set "spark.akka.timeout" "300"))]
           (f/spark-context cfg)))


(def data
         ;; Read lines from file
         (-> (f/text-file spark "winequality-red.csv")
             ;; Enumerate lines.
             ;; This function is missing from Flambo,
             ;; so we call the method directly
             (.zipWithIndex)
             ;; This is here purely for convenience:
             ;; it transforms Spark tuples into Clojure vectors
             (f/map f/untuple)
             ;; Get rid of the header
             (f/filter (f/fn [[line idx]] (< 0 idx)))
             ;; Split lines and transform values
             (f/map (f/fn [[line _]]
                      (->> (s/split line #";")
                           (map #(Float/parseFloat %)))))))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

