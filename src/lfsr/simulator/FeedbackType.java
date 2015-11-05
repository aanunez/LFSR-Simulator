/**
 * Author: Adam Nunez, adam.a.nunez@gmail.com
 * Copyright (C) 2014 Adam Nunez
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
 * FeedbackType
 * Enumeration of the possible feedback types for the LFSR. Used by below public
 * methods.
 *
 * @author Adam Nunez, adam.a.nunez@gmail.com
 * @version 1.0 22 April 2015
 * @see lfsr.simulator.LFSR#getBitsForward
 * @see lfsr.simulator.LFSR#getBitsBackward
 * @see lfsr.simulator.LFSR#strobeClock
 * @see lfsr.simulator.LFSR#toVerilog
 */
public enum FeedbackType { 
    
   /**
    * The One to Many feedback type
    */
    ONE2MANY, 
    
   /**
    * The Many to One feedback type
    */
    MANY2ONE
}