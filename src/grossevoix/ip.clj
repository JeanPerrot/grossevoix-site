(ns grossevoix.ip
  (:require [clj-http.client :as http]))


(def appId "DvAjuWljBB0RLhUjitZAodpTit1ePUhoWWhV97yq")
(def api-key "zFwmgWjoDBvzk3JlAI7pOHromzhmMFV4qPomymc0")
(def url "https://api.parse.com/1/classes/config")


(defn read-ip []
  (-> (http/get url
              {:headers {"X-Parse-Application-Id" appId
                         "X-Parse-REST-API-Key"   api-key
                         "Content-Type"           "application/json"}
               :as      :json})
    :body :results first :ip .trim
    ))

