# Documentation of setlX Sound Plugin

## Abstract - What this document is about

We provide several user defined functions, that you be used for sound processing in setlx. This document will provide some information about the possibilities of these functions.
SetlX implements an API to the Java Framework JFugue.

Arguments listed with a '?' are optional.

## addChordProgression(patternName:String,  chordProgression: String, key?: String)

* **patternName**: The name of the pattern you want to add. This is also the id, which can be used to access this pattern

* **chordProgression**: A progression of tje (Hauptstufen). indicates the main cadences in roman letteres from I to VII. For example "I IV V I" is a basic finishing cadence

* **key** the tonality. Logically, this is the first I step

## addPattern(patternName: String, pattern:String, tempo:Int?, instrument: Int?, voice:Int?)

* **patternName** The name of the pattern

* **pattern**: A musical pattern. This can contain, all things, a jfugue pattern can contain

    e.g. note letters and durations. 

    addPattern("A","Cq Fq Gh Cq"

    [link for jmusic possibilities]

    or Midinumbers

        addPattern("A", "40 41 42 42");
    
    or even different Voices in one line
    
     addPattern("A","V1 C G V2 GC");

* **tempo** A tempo in Beats Per Minut4e (BPM)

* **Voice** A voice is a "musical line in a partiture. So if you like to play two notes at the same time, you have to divide them into different voices.

## addRhythm(patternName: String, pattern:String, length?: Int)

## addToPattern (patternName:String, patternString: String)

* **patternName** The name of the pattern you want to add something

* **patternString** The String of musical information you want to add to a pattern

## allChordsAs(patternName: String, chordProgression: String)

Allows to modify a Chord Progression. Maybe you want to play a appegio or something like that, then you can just convert the musical progression into it.
