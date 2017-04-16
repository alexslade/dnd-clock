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

;; home-panel

(defn clock-panel []
  (let [time (re/subscribe [:time])]
    (fn []
      [:div
        [:div.version
          "Version 0.1.0-alpha — "
          [:a {:href "#/"} "Read more"]]
        [:div.clock
          [:div.time (display-time @time)]
          [:div.minutes
            [:button
              {:on-click #(re/dispatch [:advance-time (minutes 5)])}
              "+ 5 minutes"]

            [:button
              {:on-click #(re/dispatch [:advance-time (minutes 15)])}
              "+ 15 minutes"]]
          [:div.hours
            [:button
              {:on-click #(re/dispatch [:advance-time (hours 1)])}
              "+ 1 hour"]

            [:button
              {:on-click #(re/dispatch [:advance-time (hours 8)])}
              "+ 8 hours"]]]])))



;; home

(defn home-panel []
  (fn []
    [:div
      [:div [:a {:href "#/clock"} "Start using the Clock"]]
      [:h1 "RPG clock - prototype"]
      [:p "This is the first draft of a tool to help track time during campaigns. Rather than ticking along, the DM (or a designated player) is in control of progressing time. Please give feedback, and consider " [:strong "everything"] " experimental."]
      [:h2 "TODO (in current priority order - please request changes)"]
      [:ul
        [:li "[x] Basic 12-h clock with increment buttons"]
        [:li "[ ] Sync across multiple screens. I.e. DM controls via a phone, players see time on an iPad or TV."]
        [:li "[ ] Nicer styling, with mobile support"]
        [:li "[ ] Date tracking (Does this need custom date formats to suit different worlds?)"]]


      [:h2 "Release notes"]
      [:h3 "Version 0.1.0-alpha"]
      [:ul
        [:li "A first prototype. No styles, probably broken in some way, needs plenty of further work."]]]))



;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :clock-panel [clock-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
