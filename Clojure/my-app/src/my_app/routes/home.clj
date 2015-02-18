(ns my-app.routes.home
  (:require [compojure.core :refer :all]
            [my-app.layout :as layout]
            [my-app.util :as util]))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))
