/**
 * Author: Adam Nunez, adam.a.nunez@gmail.com
 * Copyright (C) 2014  name of author
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package lfsr.simulator;

/**
 * GateType
 * Enumeration of possible gate types for an LFSR. Used by below public methods.
 *
 * @author Adam Nunez, adam.a.nunez@gmail.com
 * @version 1.0 22 April 2015
 * @see lfsr.simulator.LFSR#setNumberOfBits
 * @see lfsr.simulator.LFSR#strobeClock
 * @see lfsr.simulator.LFSR#CalculateSequenceLength
 * @see lfsr.simulator.LFSR#toVerilog
 */
public enum GateType { 
        
    /**
     * The XOR gate type
     */
     XOR, 

    /**
     * The XNOR gate type
     */
     XNOR
}