(ns leiningen.lein-haml-sass.options
  (:require [leiningen.core.main :as lmain]))

(def ^:private default-options {:src "resources"
                                :output-extension ""
                                :delete-output-dir true
                                :auto-compile-delay 250
                                :gem-name "sass"
                                :gem-version "3.3.0"
                                :style :nested
                               })

(defn- normalize-hooks [options]
  (let [hooks            (into #{} (:ignore-hooks options))
        normalized-hooks (if (:compile hooks)
                           (disj (conj hooks :once) :compile)
                           hooks)]
    (assoc options :ignore-hooks normalized-hooks)))

(defn extract-options [project]
  (if :sass project
    (merge default-options (normalize-hooks options))
    (lmain/warn "No sass entry found in project definition.")))