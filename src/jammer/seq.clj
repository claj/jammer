(ns jammer.seq

  (:use [seesaw.core] 
        [jammer.core :only [test-midi the-midi-port]]
        [overtone.midi]
))


(def seqi [40 nil nil 40 
           40 nil nil 40 
           40 40 40 40
           40 nil nil 40])

(dotimes [_ 16]
  (doseq [s seqi]
    (if s 
      (do (test-midi) (midi-control ctrl-surf 100 (+ 40 (rand-int 10)))))
    (Thread/sleep 65)))

(def other-wise [0 7 5 7 
                 3 4 7 9 
                 0 7 0 2 
                 0 2 3 7])

(dotimes [i 64] 
  (midi-note @the-midi-port 
             (+ (get other-wise (mod i 12)) 40) 
             100 
             10
             0) 
  (Thread/sleep 120))

(dotimes [i 12] 
  (midi-note @the-midi-port 
             60
             100 
             10
             1) 
  (Thread/sleep 500))

(def bas60 [100 50 50 0 50 100 0 0 0 100 100 0 100 0 0 0])
(def bas62 [10 00 50 0 30 100 30 0 100 10 0 0 0 30 30 30])
(def bas64 [100 0 0 0 100 0 0 0 100 0 0 0 100 0 0 100])
(def basline-vel [0 0 100 100 0 100 100 0 100 100 0 0 0 0 100 0])

(def melodi (vec (take 16 (cycle [32 38 50]))))

;; muzak bitte.

(dotimes [_ 8]
  (dotimes [i 16]
    (midi-note @the-midi-port 60 (bas60 i) 10 1) 
    (midi-note @the-midi-port 62 (bas62 i) 10 1) 
    (midi-note @the-midi-port 64 (bas64 i) 10 1)
    (midi-note @the-midi-port (melodi i) (basline-vel i) 40 0)

(Thread/sleep 80)))

