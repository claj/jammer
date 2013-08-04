(defproject jammer "0.1.0-SNAPSHOT"
  :description "A keyboard->midi converter with a layout suitable for jamming"
  :url "https://github.com/claj/jammer"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [seesaw "1.4.3"]
                 [overtone/midi-clj "0.5.0"]]
  :main jammer.core)
