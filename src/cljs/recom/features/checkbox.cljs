(ns recom.features.checkbox
  (:require [re-com.core   :refer [h-box v-box box gap line checkbox label p]]
            [re-com.misc   :refer [checkbox-args-desc]]
            [recom.features.utils :refer [panel-title title2 args-table github-hyperlink status-text]]
            [reagent.core  :as    reagent]))

(defn right-arrow
  []
  [:svg
   {:height 20  :width 25}
   [:line {:x1 "0" :y1 "10" :x2 "20" :y2 "10"
           :style {:stroke "#888"}}]
   [:polygon {:points "20,6 20,14 25,10" :style {:stroke "#888" :fill "#888"}}]])


(defn left-arrow
  []
  [:svg
   {:height 20  :width 25}
   [:line {:x1 "5" :y1 "10" :x2 "20" :y2 "10"
           :style {:stroke "#888"}}]
   [:polygon {:points "5,6 5,14 0,10" :style {:stroke "#888" :fill "#888"}}]])


(defn checkboxes-demo
  []
  (let [; always-false (reagent/atom false)
        disabled?    (reagent/atom false)
        ticked?      (reagent/atom false)
        something1?  (reagent/atom false)
        something2?  (reagent/atom true)
        all-for-one? (reagent/atom true)]
    (fn
      []
      [v-box
       :size     "auto"
       :gap      "10px"
       :children [[h-box
                   :gap      "100px"
                   :children [[v-box
                               :gap      "10px"
                               :children [[title2 "Demo"]
                                          [v-box
                                           :gap "15px"
                                           :children [[h-box
                                                       :gap      "10px"
                                                       :height   "20px"
                                                       :children [[checkbox
                                                                   :label     "tick me  "
                                                                   :model     ticked?
                                                                   :on-change #(reset! ticked? %)]
                                                                  (when @ticked? [left-arrow])
                                                                  (when @ticked? [label :label " is ticked"])]]

                                                      [h-box
                                                       :gap      "10px"
                                                       :children [[checkbox  :model all-for-one? :on-change #(reset! all-for-one? %)]
                                                                  [checkbox  :model all-for-one? :on-change #(reset! all-for-one? %)]
                                                                  [checkbox  :model all-for-one? :on-change #(reset! all-for-one? %)  :label  "all for one, and one for all.  "]]]

                                                      [h-box
                                                       :gap      "15px"
                                                       :children [[checkbox
                                                                   :label     "tick this one, to \"disable\""
                                                                   :model     disabled?
                                                                   :on-change #(reset! disabled? %)]
                                                                  [right-arrow]
                                                                  [checkbox
                                                                   :label       (if @disabled? "now disabled" "enabled")
                                                                   :model       something1?
                                                                   :disabled?   disabled?
                                                                   :label-style (if @disabled?  {:color "#888"})
                                                                   :on-change   #(reset! something1? %)]]]

                                                      [h-box
                                                       :gap      "1px"
                                                       :children [[checkbox
                                                                   :model     something2?
                                                                   :on-change #(reset! something2? %)]
                                                                  [gap :size "50px"]
                                                                  [left-arrow]
                                                                  [gap :size "5px"]
                                                                  [label
                                                                   :label "no label on this one"]]]]]]]]]]])))


;; core holds onto references, so need one level of indirection to get figwheel updates
(defn panel
  []
  [checkboxes-demo])
