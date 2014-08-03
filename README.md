LFSR-Simulator
==============

A small utility for simulating an LFSR, viewing the code to make that LFSR in an HDL, and making sure your LFSR stops when you want it to stop.

Files
-----

* LFSR.java - The LFSR class itself, feel free to use it all you want.
* LFSRSimulator.java - The main file.
* OptimalTaps.java - Contains static data for the selection of optimal taps in the LFSR.
* View.java - GUI and controller code.

Features
--------

* Generate/display all, or a range, of the bit sequences produced by an LFSR
* LFSRs can be XOR or XNOR, extended or not, many to one or one to many.
* Automatic tap selection to insure longest possible sequence or manual.
* Number of bits can be altered to find the resulting sequence length before displaying the sequence.
* Code generation for Verilog, VHDL, AHDL, MyHDL. (NOT COMPLETE)

Wait, Whats an LFSR?
--------------------

A Linear Feedback Shift Register is used when you need to track a large counting event. Adding Counter = Counter + 1 takes a surprisingly large amount of work, but an LFSR does not. Typically they are used in embedded devices. If you don't know why you would need this, its cool, but you probably don't need to be here reading this.

Usage
-----

If you simply wish to use the program as is you need only to run dist/LFSR-Simulator.jar and fill in needed information. If you wish to use the LFSR class then you will need both LFSR.java and OptimalTaps.java. The basic operation of the class is as follows...

    LFSR lfsr = new LFSR (); // Create a simple LFSR with default settings.(see below)
    lfsr.setNumberOfBits ( 10 );
    lfsr.setGateType ("XNOR");
    lfsr.strobeClock (); // Move the LFSR forward one. 
    print ( lfsr.getBitsForward() ); // Print the new sequence.

Default settings for the LFSR are as follows:

* Number of Bits: 2
* Taps: {1}
* Gate: XOR
* Feedback: Many to One
* Extended: No, 2^n-1 length (at most)

All XOR feedback LFSRs start as an all 1 sequences while all XNOR start as all 0 sequences.

Contact
-------

If you have questions, concerns, or comments please feel free to contact me via 

         adam.a.nunez@gmail.com


License
-------

Copyright (C) 2014  Adam Nunez

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.