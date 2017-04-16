(ns clock.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:color "black"}]
  [:.time {:margin "1em"
           :font-size "4em"}]
  [:button {:font-size "2em"
            :margin "0.1em"
            :padding "0.25em 1em"
            :display "block"}])

