(ns grossevoix.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [grossevoix.process :refer [say fetch]]
            [grossevoix.ip :refer [read-ip]]
            ))


(def ip (atom (read-ip)))
(def port (atom 49160))


(defroutes app
  (POST "/say" {{msg :msg} :params}
       (say @ip @port msg))
  (GET "/say" []
       (fetch @ip @port))
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
; serve a web page with a simple web form that posts to the heroku app

