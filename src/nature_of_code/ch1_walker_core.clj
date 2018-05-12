(ns noc_ch1_walker.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

 (def width 1500) ; no magic numbers
 (def height 1500) 


(defn setup []; this function is called first when the sketch runs
  (q/color-mode :hsb) ; side effect 1: sketch mode
  (q/background 50) ; side effect 2: set the background color
  {:x (/ width 2) :y (/ height 2)} ; start at the center
  ; all state is encapsulated by a single walker
)

(defn display_walker [w]
  (q/fill 205)
  (q/no-stroke)
  (q/ellipse (get w :x) (get w :y) 3 3)
)

(defn draw [walker]

  (q/fill 205)
  (display_walker walker)
)

(defn walker_update [w]
 (let [c (rand-int 4)]
  (cond
    (= c 0) {:x (+ 1 (get w :x)) :y (get w :y)}
    (= c 1) {:x (- 1 (get w :x)) :y (get w :y)}
    (= c 2) {:x (get w :x) :y (+ 1 (get w :y))}
    (= c 3) {:x (get w :x) :y (- 1 (get w :y))}
   )
  )
)


(defn main [& args]
  (q/defsketch walker01
   :title "brownian motion"
   :size [width height]
   :setup setup
   :update walker_update
   :draw draw
   :middleware [m/fun-mode]
  )
)
