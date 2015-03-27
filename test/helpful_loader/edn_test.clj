(ns helpful-loader.edn-test
  (:require [helpful-loader.edn :refer :all]
            [expectations :refer :all]
            [test-with-files.core :refer :all]))

(expect {:a 4} (with-files [["/map.edn" "{:a 4}"]]
                 (load-one (str public-dir "/map.edn"))))

(expect (str "Unable to load " public-dir "/map.edn, no such file on classpath.")
        (try (load-one (str public-dir "/map.edn"))
             (catch Exception e (.getMessage e))))

(expect (str "Error in " public-dir "/broken.edn: Unmatched delimiter: ]")
        (with-files [["/broken.edn" "{:a 4"]]
          (try (load-one (str public-dir "/broken.edn"))
               (catch Exception e (.getMessage e)))))

(expect (str public-dir "/several.edn should contain only a single form, but had 2 forms.")
        (with-files [["/several.edn" "{:a 4} :wq"]]
          (try (load-one (str public-dir "/several.edn"))
               (catch Exception e (.getMessage e)))))

(expect [1 2 3] (with-files [["/numbers.edn" "[1 2 3]"]]
                  (load-one-or-nil (str public-dir "/numbers.edn"))))

(expect nil? (load-one-or-nil "unknown.edn"))
