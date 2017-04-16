(ns clock.events
    (:require [re-frame.core :as re-frame]
              [clock.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
 :advance-time
 (fn [db [_ seconds]]
   (update-in db [:time] + seconds)))

