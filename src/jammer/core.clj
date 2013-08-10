(ns jammer.core
  "This is a Jammer, a clever MIDI-keyboard layout for you computer keyboard
See alsp README.md"
  (:use [seesaw.core]
        [overtone.midi :only [midi-out midi-note-on midi-note-off]]))

(def alphabet-keycodes (zipmap 
                        (range 65 91) 
                        "abcdefghijklmnopqrstuvwxyz"))

(def numeric-keycodes {49 \1
                       50 \2 
                       51 \3
                       52 \4
                       53 \5 
                       54 \6
                       55 \7
                       56 \8
                       57 \9
                       48 \0})

(defn keycode->char  
  "'leaky' keycode->char converter
gives 65 -> 'a'
ie when given a code not a char, it just send the keycode through"
  [keycode]
  (get 
   (merge alphabet-keycodes numeric-keycodes)     
   keycode keycode))

(def char->note "
used to convert char a to midi note 54 (F#4) etc...

the characters below should resemble of a keyboard, with the key left of z and the two right of m specified with keycode instead of a clojure char

please note that the midi note numbers always increases +2, and that there's an odd (5,7) offset per row, according to the Wicki-Hayden note layout:

  +fourth  +oct  +fifth
      ^     ^     _
      |     |     /|
      |_ ****** /
        *      *
       *        *
       *   C    *  -> +2
       *        *
        *      *
         ******       
"
{\1 64 \2 66 \3 68 \4 70 \5 72 \6 74 \7 76 \8 78 \9 80 \0 82
   \q 59 \w 61 \e 63 \r 65 \t 67 \y 69 \u 71 \i 73 \o 75 \p 77
    \a 54 \s 56 \d 58 \f 60 \g 62 \h 64 \j 66 \k 68 \l 70 
60 47 \z 49 \x 51 \c 53 \v 55 \b 57 \n 59 \m 61 188 63 190 65} )

(defn keycode->midinote 
  [keycode] 
  {:test (fn [] ( = (keycode->midinote 65 ) 54))}
  "gets a keycode and output a suitable MIDI-note"
  (-> keycode 
      keycode->char
      char->note))

(def the-midi-port "selects a midi port through a popup window" (atom nil) )

(reset! the-midi-port (midi-out))

(defn setup-frame 
  "initializes a window that maps it's :key-pressed and :key-released events to midi-note on/offs as chosen in the start of the program. the keyboard keys are mapped to the Wichy-Hayden layout. You must have the window focused to be able to use it to send midi (because your OS only sends the keys to the window when it's focused)"
  []
  (let [the-label (label "I need focus to play MIDI out")
        midi-indicator (label "midi")
        the-frame (frame :title "Jammer" :content (left-right-split the-label midi-indicator :divider-location 5/6))]
    (-> the-frame
        pack!
        show!)
    (listen the-frame
            :window-activated (fn [e]
                                (println "window activated!")
                            (future 
                              (config! the-label :text "listening to keyboard") 
                              (config! the-label :background :lightgrey)))
            :window-deactivated (fn [e] 
                                  (println "window deactivated!")
                          (future 
                            (config! the-label :text "no focus, no midi") 
                            (config! the-label :background :grey)))
            :key-pressed (fn [e] 
                           (future (config! midi-indicator :background :lightgrey))
                           (if-let [note-number (keycode->midinote (. e getKeyCode))]
                              
                             (midi-note-on @the-midi-port note-number 100)
                             (println "no midi out")))
            :key-released (fn [e]
                            (future (config! midi-indicator :background "#DDD"))
                            (if-let [note-number (keycode->midinote (. e getKeyCode))]
                              
                              (midi-note-off @the-midi-port note-number) 
                              (println "no midi out"))))
    midi-indicator))

(defn test-midi []
  (do
    (midi-note-on @the-midi-port 40 100) 
    (Thread/sleep 50) 
    (midi-note-off @the-midi-port 40)))




(comment (test-midi))

(comment (setup-frame))

(defn -main 
  "start with lein run if you like"
  [& args]
  (invoke-later (setup-frame)))
