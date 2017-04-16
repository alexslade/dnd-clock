(ns clock.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:color "black"
          :font-family "-apple-system,BlinkMacSystemFont,avenir next,avenir,helvetica,helvetica neue,ubuntu,roboto,noto,segoe ui,arial,sans-serif";
          :line-height 1.6}]
  [:.time {:margin "1em"
           :font-size "4em"}]
  [:button {:font-size "2em"
            :margin "0.25em"
            :padding "0.25em 1em"
            :display "inline-block"}]
  [:.minutes {:margin-bottom "1em"}]
  [:.clock {:text-align "center"}])


