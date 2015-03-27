(defproject helpful-loader "0.1.1"
  :description "Load resources with helpful error messages."
  :url "http://github.com/magnars/helpful-loader"
  :license {:name "GNU General Public License v3"
            :url "http://www.gnu.org/licenses/"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:dev {:dependencies [[expectations "2.1.0"]
                                  [test-with-files "0.1.0"]]
                   :plugins [[lein-expectations "0.0.7"]
                             [lein-autoexpect "1.4.0"]]
                   :resource-paths ["test/resources"]}})
