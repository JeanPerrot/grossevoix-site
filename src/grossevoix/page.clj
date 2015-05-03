(ns grossevoix.page
  (:use [hiccup.core]
        [hiccup.form]
        ))

(defn submit-form []
  (html
    [:html
     [:body
      [:h1 "just say something"]
      (form-to [:post "/say"]
               (text-area "msg" "")
               (submit-button "say it")
               )]]))


