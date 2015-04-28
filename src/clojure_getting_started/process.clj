(ns clojure-getting-started.process
  (:require [clj-http.client :as http]))

(defn- endpoint [ip port]
  (str "http://" ip ":" port "/say"))

(defn say [ip port what]
  (let [url (str (endpoint ip port) "?msg=" what)]
    (println url)
    (http/post url)))

(defn fetch [ip port]
  (let [url (endpoint ip port)]
    (println url)
    (:body (http/get url)))
  )
