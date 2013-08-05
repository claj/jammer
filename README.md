# jammer

<img src="https://github.com/claj/jammer/blob/master/wicki-hayden-qwerty.png" alt="QWERTY keyboard layout with notes marked according to the Wicky-Hayden layout" title="The Keyboard">

> "S D S C   S D S C
> E E F   W W S   D D W F D S D S C"
> "Silent Night" as stated by Qwertonic.com

This is a Jammer, a clever MIDI-keyboard layout for you computer keyboard. The layout is much more fun than a piano-layout, since it's isotonic - to transpose the melody, just move your whole hand in one of six directions.

# ALPHA. This was made in an afternoon.

## Usage

If you want to play a program in your computer, configure a MIDI loop-back device. On Mac OS X this is built in, search your Mac for \"Audio MIDI Setup\", chose Window->Show MIDI window. When in doubt, there's a [youtube movie](http://www.youtube.com/watch?v=hgFA_fdup7g).

After this it should be to just

`lein run`

and you can select your MIDI out-port.

## References

 * [Wicki-Hayden note layout](http://en.wikipedia.org/wiki/Wicki-Hayden_note_layout), the layout used here, except that we use the standard midi tuning (ie C# = Db, that is):
 * A [list of keycodes (and a tester)](http://www.cambiaresearch.com/articles/15/javascript-char-codes-key-codes)
 * A [table of midi notes and their numbers](http://www.midimountain.com/midi/midi_note_numbers.html)
 * [Seesaw event handling](https://github.com/daveray/seesaw/wiki/Handling-events)
 * [midi-clj](https://github.com/overtone/midi-clj), Overtone MIDI functionality 
 * [Qwertonic](http://www.qwertonic.com/), another implementation of the Wicki-Hayden layout.

## License

Copyright © 2013 Linus Ericsson

Distributed under the Eclipse Public License, the same as Clojure.
