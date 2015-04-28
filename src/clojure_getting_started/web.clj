(ns clojure-getting-started.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [clojure-getting-started.process :refer [say]]
            [clojure-getting-started.ip :refer [read-ip]]

            ))

(defn splash []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (pr-str ["Hello" :from 'Heroku])})


(defn- retrieve-home-url
  "retrieve the url at which home is located"
  [])

(defn- forward-command[])


(def ip (atom (read-ip)))
(def port (atom 49160))


(defroutes app
  (GET "/" []
       (splash))
  (POST "/say" {{msg :msg} :params}
       (say @ip @port msg))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))



;(.stop server)

;; For interactive development:
;; (.stop server)
;; (def server (-main))


; TODO -
; calls to parse-api in clj
; serve a web page with a simple web form that posts to the heroku app
; back-end route that forwards the call to the home server
; map ports (using router) to go to the right spot.

