(ns helpful-loader.edn
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn- validate-resource-existence [file]
  (if (nil? (io/resource file))
    (throw (Exception. (str "Unable to load " file ", no such file on classpath.")))))

(defn- load-one-str [content file-name]
  (let [forms (try
                (clojure.edn/read-string (str "[" content "]"))
                (catch Exception e
                  (throw (Exception. (str "Error in " file-name ": " (.getMessage e))))))]
    (when (> (count forms) 1)
      (throw (Exception. (str file-name " should contain only a single form, but had " (count forms) " forms."))))
    (first forms)))

(defn load-one
  "Read a single edn data structure from the classpath. The path
  should be relative to the resources directory, e.g. (load-edn
  'config.edn') to read resources/config.edn"
  [file]
  (validate-resource-existence file)
  (load-one-str (slurp (io/resource file)) file))

(defn load-one-or-nil
  "Read a single edn data structure from the classpath if it exists."
  [file]
  (when-let [resource (io/resource file)]
    (load-one-str (slurp resource) file)))
