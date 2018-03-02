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

* Allows to modify a Chord Progression. Maybe you want to play a appegio or something like that, then you can just convert the musical progression into it.
e.g.
    addChordProgess("A","I IV V I");
    allChordsAs
    //TODO

## duplicatePattern(sourceName: String, newName: String)

* **sourceName** the name of the pattern you want to clone

* **newName** The new name of the pattern.

## eachChordAs

...

## getPatternStats(patternName:String)

An interesting tool to get information about a specific pattern. Have fun!

* **patternName** The name of the pattern, you want to calculate stats

## listSuppoertedInstruments 
    @Not implemented yet

## modifyPatternProperty(patternName: String, property: String, value:Int)

* **patternName** The
 name of the pattern

* **property** A value of the set {INSTRUMENT, TEMPO, VOICE}
* **value** the new value for the pattern Property.

## play(patternNames:String...)

Takes a list of pattern names and playes it. If you want to play different notes at once, remember that you have to use differen tvoices.

## playTone(note: String, duration: Int, Instrument: Int, Voice: Int, Layer: Int)

A standalone function to play single tones.

## removeMusic(patternName: String)

removes a pattern from the storage.
* **patternName**: The name of the pattern

## saveAsPattern(elementName: String)

* **elementName** the name of the element you want to convert to a regular pattern

## setKeyForChordProgression)(elementName: String, baseKey: String)
Sets the base key (I) for a given chord Progression

## showMusic()

Shows all stored Patterns, Chordprogressions and rythmicPatterns

## stopNotes()

Stops the playback of notes processed by the real time processor. See playTone-Function.

## Transpose()

    @Not supported yet

