(ns clock.views
    (:require [re-frame.core :as re]))

(defn minutes [n]
  (* n 60))

(defn hours [n]
  (* (minutes n) 60))

(defn pad [n]
  (let [l (count (str n))]
    (if (= 1 l)
      (str 0 n)
      (str n))))

(defn display-time [n]
  (let [time (mod n (hours 24))
        secs (mod time 60)
        mins (/ (- (mod time (minutes 60))
                   secs)
                60)
        hrs (/ (- time
                  (minutes mins)
                  secs)
               60
               60)
        mm (if (> 12 hrs) "am" "pm")
        mm-hrs (if (> 13 hrs) hrs (mod hrs 12))]
    [:span mm-hrs ":" (pad mins) [:span.small mm]]))

;; home

(defn home-panel []
  (let [time (re/subscribe [:time])]
    (fn []
      [:div
        [:div.time (display-time @time)]
        [:button
          {:on-click #(re/dispatch [:advance-time (minutes 5)])}
          "5 minutes"]

        [:button
          {:on-click #(re/dispatch [:advance-time (minutes 15)])}
          "15 minutes"]

        [:button
          {:on-click #(re/dispatch [:advance-time (hours 1)])}
          "1 hour"]

        [:button
          {:on-click #(re/dispatch [:advance-time (hours 8)])}
          "8 hours"]])))



;; about

(defn about-panel []
  (fn []
    [:div "This is the About Page."
     [:div [:a {:href "#/"} "go to Home Page"]]]))


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
