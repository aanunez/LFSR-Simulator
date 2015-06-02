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
 * LFSR
 * Simulates a XOR/XNOR, MANY-TO-ONE/ONE-TO-MANY LFSR.
 * The taps for the LFSR are one indexed. Inspiration
 * for this project comes from...
 * http://fab.cba.mit.edu/classes/MIT/864.05/people/gdennis/
 *
 * @author Adam Nunez, adam.a.nunez@gmail.com
 * @version 1.5 30 May 2015
 */
public class LFSR {

    private int NumberOfBits;
    private int[] Taps;
    private boolean[] Bits;
    
    private GateType Gate = GateType.XOR;
    private FeedbackType Feedback = FeedbackType.ONE2MANY;
    private int SeqLength = -1;
    private boolean Extended = false;

    private static final int ACCEPTABLE_RUN_TIME = 5000;
    private long TimeOut;
    private boolean TimeOutFlag;

    private boolean[] StrobeBitClone;
    private boolean StrobeExtendedRestoreFlag;
    private boolean StrobeExtendedOnceFlag;

    //**************************************************************************
    //Constructors
    //**************************************************************************

    public LFSR(int NumberOfBits, int[] Taps, GateType Gate, FeedbackType Feedback, boolean isExtended) {
        SeqLength = -1;
        setGateType(Gate);
        setFeedbackType(Feedback);
        setExtended(isExtended);
        if ( !setNumberOfBits(NumberOfBits) || !setTaps(Taps) )
            throw new IllegalArgumentException("Failed to initialize an LFSR class.");
        resetTimeOutFlag();
        resetLFSR();
    }

    public LFSR() {
        this(4, new int[0], GateType.XOR, FeedbackType.MANY2ONE, false);
    }

    public LFSR(int NumberOfBits, int[] Taps) {
         this(NumberOfBits, Taps, GateType.XOR, FeedbackType.MANY2ONE, false);
    }

    public LFSR(int NumberOfBits, int[] Taps, GateType Gate) {
        this(NumberOfBits, Taps, Gate, FeedbackType.MANY2ONE, false);
    }

    public LFSR(int NumberOfBits, int[] Taps, GateType Gate, FeedbackType Feedback) {
        this(NumberOfBits, Taps, Gate, Feedback, false);
    }

    public LFSR(int NumberOfBits, GateType Gate, FeedbackType Feedback, boolean isExtended) {
        this(NumberOfBits, new int[0], Gate, Feedback, isExtended);
    }

    public LFSR(int NumberOfBits, GateType Gate, FeedbackType Feedback) {
        this(NumberOfBits, new int[0], Gate, Feedback, false);
    }

    public LFSR(int NumberOfBits, GateType Gate) {
        this(NumberOfBits, new int[0], Gate, FeedbackType.MANY2ONE, false);
    }

    //**************************************************************************
    // Set Functions ( & reset )
    //**************************************************************************

   /**
    * setGateType
    * Sets the gate type of the LFSR.
    *
    * @param val  Gate type for the LFSR
    * @see GateType
    */
    public final void setGateType(GateType val) {
        Gate = val;
    }

   /**
    * setFeedbackType
    * Sets the feedback type of the LFSR.
    *
    * @param val  feedback type for the LFSR
    * @see FeedbackType
    */
    public final void setFeedbackType(FeedbackType val) {
        Feedback = val;
    }

   /**
    * setNumberOfBits
    * Sets the number of bits the LFSR should have. Checking is done to
    * insure a value greater than zero. Initializes the current value of
    * the LFSR to all zero if XNOR gate type and all one if XOR gate
    * type. The Bit array stored by the LFSR is one larger than requested
    * to facilitate the clock strobing operation.
    *
    * @param val  number of bits for the LFSR
    * @return true if successful, else false
    */
    public final boolean setNumberOfBits(int val) {
        SeqLength = -1;
        if (val <= 1)
            return false;
        boolean initialValue = true;
        if (Gate == GateType.XNOR)
            initialValue = false;
        NumberOfBits = val;
        Bits = new boolean[NumberOfBits + 1];
        for(int i = 0; i < NumberOfBits; i++)
            Bits[i] = initialValue;
        return true;
    }

   /**
    * setTaps
    * Sets the given taps to active in the LFSR. Taps can be in any order and
    * should not contain zero. The empty array sets the taps to optimal.
    *
    * @param NewTaps  array of taps to activate
    * @return true if successful, else false
    * @see OptimalTaps
    */
    public final boolean setTaps(int[] NewTaps) {
        SeqLength = -1;
        if ( NewTaps.length == 0 )
            NewTaps = getOptimizedTaps();
        java.util.Arrays.sort( NewTaps );
        if ( NewTaps[0] == 0 )
            return false;
        Taps = NewTaps;
        return true;
    }
    
   /**
    * setCurrentBits
    * Sets the current value of the LFSR to that given. This function is 
    * dangerous as the it does not reset the LFSR. The array should be the same
    * or one shorted that the value of NumberOfBits.
    *
    * @param NewBits  array of bits to set, size of NumberOfBits or one less
    * @return true if successful, else false
    * @see setNumberOfBits
    * @see resetLFSR
    * @see CalculateSequenceLength
    */
    public boolean setCurrentBits( boolean[] NewBits ) {
        if ( (NewBits.length == NumberOfBits-1) || (NewBits.length == NumberOfBits) ) {
            System.arraycopy(NewBits, 0, Bits, 0, NewBits.length);
            return true;
        }
        return false;
    }

   /**
    * setExtended
    * Sets the extended flag high or low for the LFSR.
    *
    * @param val  true if the LFSR is extended, else false
    */
    public final void setExtended(boolean val) {
        Extended = val;
    }

   /**
    * resetTimeOutFlag
    * resets the time out flag to zero. The flag may be set by certain
    * operations that could take a long time.
    * 
    * @see getBitSequence
    * @see getTimeOutFlag
    * @see CalculateSequenceLength
    */
    public final void resetTimeOutFlag() {
        TimeOutFlag = false;
    }

   /**
    * resetLFSR
    * resets the the value of the LFSR and all flags that are set by the LFSR
    * except time out. Sequence length is saved. The flag is used by 
    * getBitSequence and CalculateSequenceLength.
    * 
    * @see setNumberOfBits
    */
    public final void resetLFSR() {
        int SeqLenClone = SeqLength;
        setNumberOfBits(NumberOfBits);
        SeqLength = SeqLenClone;
        StrobeExtendedRestoreFlag = false;
        StrobeExtendedOnceFlag = false;
    }

    //**************************************************************************
    // Get Functions
    //**************************************************************************

   /**
    * isExtended
    * gets the value of the Extended setting of the LFSR
    * 
    * @return true if the LFSR is extended, else false
    */
    public boolean isExtended() {
        return Extended;
    }

   /**
    * getNumberOfBits
    * gets the size of the LFSR. This is not the length of the Bits array,
    * the array is NumberOfBits+1 in size as one additional bit is needed
    * for operations.
    * 
    * @return number of bits in the LFSR
    * @see strobeClock
    */
    public int getNumberOfBits() {
        return NumberOfBits;
    }

   /**
    * getTaps
    * gets the array of active taps of the LFSR
    * 
    * @return array of the active taps in the LFSR sorted ascending
    * @see strobeClock
    */
    public int[] getTaps() {
        return Taps;
    }

   /**
    * getBitsForward
    * gets a string of "1" and "0" that is th value of the LFSR with the most
    * significant bit (MSB) on the left and least significant bit (LSB) on the
    * right.
    * 
    * @return string of the value stored in the LFSR in binary
    */
    public String getBitsForward() {
        String currentBits = BitsToString();
        if (Feedback == FeedbackType.MANY2ONE)
            return currentBits;
        else
            return new StringBuilder(currentBits).reverse().toString();
    }

   /**
    * getBitsBackward
    * gets a string of "1" and "0" that is th value of the LFSR with the most
    * significant bit (MSB) on the right and least significant bit (LSB) on the
    * left.
    * 
    * @return string of the value stored in the LFSR in binary
    */
    public String getBitsBackward() {
        String currentBits = BitsToString();
        if (Feedback == FeedbackType.ONE2MANY)
            return currentBits;
        else
            return new StringBuilder(currentBits).reverse().toString();
    }

   /**
    * getSequenceLength
    * gets the length of the sequence generated by the current LFSR settings. If
    * that value has not yet been calculated is will be by calling 
    * CalculateSequenceLength
    * 
    * @return the length of the sequence generated by the current LFSR settings
    * @see CalculateSequenceLength
    */
    public int getSequenceLength() {
        if (SeqLength == -1)
            return CalculateSequenceLength();
        return SeqLength;
    }

   /**
    * getOptimizedTaps
    * gets the first (shortest) solution for optimal taps for a LFSR of size 
    * val. These values are stored in the class OptimalTaps and are precomputed.
    * 
    * @param val  the number of bits in an LFSR
    * @return array of size 2 or 4 with optimal taps or null if taps are not known
    * @see OptimalTaps
    */
    public int[] getOptimizedTaps(int val) {
        int[][] x = OptimalTaps.getOptimalTaps(val);
        return x[0];
    }

   /**
    * getOptimizedTaps
    * gets the first (shortest) solution for optimal taps for a LFSR of size 
    * val. These values are stored in the class OptimalTaps and are precomputed.
    * 
    * @return array of size 2 or 4 with optimal taps or null if taps are not known
    * @see OptimalTaps
    */
    public int[] getOptimizedTaps() {
        return getOptimizedTaps(NumberOfBits);
    }

   /**
    * getTimeOutFlag
    * gets the value of the timeout flag. The timeout flag is hardcoded to be
    * set after 5 seconds. The flag is used by getBitSequence and 
    * CalculateSequenceLength
    * 
    * @return true if timeout, else false
    * @see OptimalTaps
    * @see resetTimeOutFlag
    * @see CalculateSequenceLength
    * @see getBitSequence
    */
    public boolean getTimeOutFlag() {
        return TimeOutFlag;
    }

   /**
    * getBitSequence
    * gets the string of many bit sequences from depth start to stop with each
    * sequence delimited by the system line separator. The sequences can be 
    * displayed forward or backward. The method can cause a timeout flag.
    * 
    * @param start  starting depth of bit sequence
    * @param stop  ending depth of bit sequence
    * @param bitDirection  true if direction should be forward, false if backward
    * @return true if timeout, else false
    * @see getBitsForward
    * @see getBitsBackward
    * @see getTimeOutFlag
    * @see resetTimeOutFlag
    */
    public String getBitSequence(int start, int stop, boolean bitDirection) {
        initTimeOut(ACCEPTABLE_RUN_TIME);
        for(int i = 0; i < start; i++) {
            strobeClock();
            if (isTimeOut())
                return "timeout";
        }
        StringBuilder text = new StringBuilder();
        for(int i = start; i <= stop; i++) {
            text.append(i)
                .append("\t");
            if (bitDirection)
                text.append(getBitsForward());
            else
                text.append(getBitsBackward());
            text.append(System.getProperty("line.separator"));
            strobeClock();
            if (isTimeOut())
                break;
        }
        return text.toString();
    }

    //**************************************************************************
    // Core Operations
    //**************************************************************************

    private boolean preStrobeExtended() {
        boolean isXNOR = (Gate==GateType.XNOR);
        if (StrobeExtendedRestoreFlag) {
            Bits = StrobeBitClone.clone();
            StrobeExtendedRestoreFlag = false;
            StrobeExtendedOnceFlag = true;
        }
        if ( !StrobeExtendedOnceFlag && ((isXNOR && XNMSB())||(!isXNOR && XMSB()))) {
            StrobeExtendedRestoreFlag = true;
            StrobeBitClone = Bits.clone();
            for(int i = 0; i < NumberOfBits; i++)
                Bits[i] = isXNOR;
            return true;
        }
        return false;
    }

    private void strobeClockXORO2M() {
        boolean[] BitsClone = Bits.clone();
        int j = 0;
        Bits[0] = BitsClone[NumberOfBits-1];
        for(int i = 1; i < NumberOfBits; i++) {
            if ((Taps[j] == i) && (Taps[j] != NumberOfBits)) {
                Bits[i] = BitsClone[i-1] ^ BitsClone[NumberOfBits-1];
                j++;
            } else
                Bits[i] = BitsClone[i-1];
        }
    }

    private void strobeClockXORM2O() {
        Bits[NumberOfBits] = false;
        for(int i = 0; i < Taps.length; i++)
            Bits[NumberOfBits] ^= Bits[NumberOfBits - Taps[i]];
        for(int i = 0; i < NumberOfBits; i++)
            Bits[i] = Bits[i + 1];
    }

    private void strobeClockXNORO2M() {
        boolean[] BitsClone = Bits.clone();
        int j = 0;
        Bits[0] = BitsClone[NumberOfBits-1];
        for(int i = 1; i < NumberOfBits; i++) {
            if ((Taps[j] == i) && (Taps[j] != NumberOfBits)) {
                Bits[i] = !(BitsClone[i-1] ^ BitsClone[NumberOfBits-1]);
                j++;
            } else
                Bits[i] = BitsClone[i-1];
        }
    }

    private void strobeClockXNORM2O() {
        Bits[NumberOfBits] = true;
        for(int i = 0; i < Taps.length; i++)
            Bits[NumberOfBits] = !(Bits[NumberOfBits] ^ Bits[NumberOfBits - Taps[i]]);
        for(int i = 0; i < NumberOfBits; i++)
            Bits[i] = Bits[i + 1];
    }

   /**
    * strobeClock
    * strobes the clock once. Advances the LFSR given its current settings.
    * The results can be observed with a display function.
    *
    * @see getBitsForward
    * @see getBitsBackward
    */
    public void strobeClock() {
        if (Extended && preStrobeExtended()) 
            return;
        if (Gate == GateType.XOR) {
            if (Feedback == FeedbackType.ONE2MANY)
                strobeClockXORO2M();
            else
                strobeClockXORM2O();
        }
        else {
            if (Feedback == FeedbackType.ONE2MANY)
                strobeClockXNORO2M();
            else
                strobeClockXNORM2O();
        }
     }
    
   /**
    * strobeClock
    * strobes the clock strobes many times. Advances the LFSR given its current 
    * settings. The results can be observed with a display function.
    * 
    * @param strobes  number of times to advance the LFSR
    * @see getBitsForward
    * @see getBitsBackward
    */
    public void strobeClock( int strobes ) {
        for ( int i =0; i < strobes; i++)
            strobeClock();
    }

   /**
    * CalculateSequenceLength
    * Calculates the sequence length of the LFSR with current settings. Unlike
    * getSequenceLength, this method does not check the previously calculated
    * and saved value.
    * 
    * @return sequence length of the LFSR with current settings
    * @see getBitsForward
    * @see getBitsBackward
    * @see getSequenceLength
    */
    public int CalculateSequenceLength() {
        int length = 0;
        boolean[] BitsClone = Bits.clone();
        setNumberOfBits(NumberOfBits);
        initTimeOut(ACCEPTABLE_RUN_TIME);
        strobeClock();
        for (;;) {
            length++;
            strobeClock();
            if (isTimeOut())
                break;
            if ( (Gate == GateType.XOR) && areAllTrue(Bits) )
                break;
            if ( (Gate == GateType.XNOR) && areAllFalse(Bits) )
                break;
        } 
        Bits = BitsClone;
        SeqLength = length + 1;
        resetLFSR();
        return SeqLength;
    }

    //**************************************************************************
    // To Language Functions
    //**************************************************************************

   /**
    * toVerilog
    * Calculates the sequence length of the LFSR with current settings. Unlike
    * getSequenceLength, this method does not check the previously calculated
    * and saved value.
    * 
    * @param IncludeReset  true if generated code should include a reset ability
    * @param IncludeFlag  true if generated code should include flag
    * @param CycleNumb  sequence number to assert flag on 
    * @return string of Verilog code describing the current LFSR
    */
    public String toVerilog(boolean IncludeReset, boolean IncludeFlag, int CycleNumb) {
        StringBuilder Verilog = new StringBuilder();
        String depth = "        ";
        String flagseq = "INITILIZE";
        if (IncludeFlag) {
            if (CycleNumb > getSequenceLength())
                flagseq = "ERROR";
            else {
                resetLFSR();
                for (int i = 0; i < CycleNumb; i++)
                    strobeClock();
                flagseq = getBitsForward();
            }
        }
        if (IncludeReset)
            depth = "            ";
        Verilog .append("module LFSR(Clock, ");
        if (IncludeReset)
            Verilog .append("Reset, ");
        if (IncludeFlag)
            Verilog .append("Flag, ");
        Verilog .append("Q);")
                .append(System.getProperty("line.separator"))
                .append("    input Clock;")
                .append(System.getProperty("line.separator"));
        if (IncludeReset)
            Verilog .append("    input Reset;")
                    .append(System.getProperty("line.separator"));
        if (IncludeFlag)
            Verilog .append("    output Flag;")
                    .append(System.getProperty("line.separator"));
        Verilog .append("    output [").append(NumberOfBits-1).append(":0] Q;")
                .append(System.getProperty("line.separator"))
                .append(System.getProperty("line.separator"))
                .append("    reg [").append(NumberOfBits-1).append(":0] LFSR;")
                .append(System.getProperty("line.separator"));
        if (Feedback == FeedbackType.ONE2MANY) {
            Verilog .append("    wire feedback = LFSR[")
                    .append(NumberOfBits-1).append("]");
            if (Extended)
                Verilog .append("^(LFSR[")
                        .append(NumberOfBits-2)
                        .append(":0] == ")
                        .append(NumberOfBits)
                        .append("'b");
            if (Gate == GateType.XNOR)
                for(int i=0; i<NumberOfBits ; i++)
                    Verilog .append("0");
            else {
                for(int i=0; i<NumberOfBits ; i++)
                    Verilog .append("1");
            }
            Verilog .append(");")
                    .append(System.getProperty("line.separator"));
        }
        Verilog .append(System.getProperty("line.separator"))
                .append("    always @(posedge Clock) begin")
                .append(System.getProperty("line.separator"));
        if (IncludeReset) {
            Verilog .append("        if (Reset == 0) begin")
                    .append(System.getProperty("line.separator"))
                    .append("            LFSR <= ")
                    .append(NumberOfBits)
                    .append("'b");
            if (Gate == GateType.XNOR)
                for(int i=0; i<NumberOfBits ; i++)
                    Verilog .append("0");
            else {
                for(int i=0; i<NumberOfBits ; i++)
                    Verilog .append("1");
            }
            Verilog .append(";")
                    .append(System.getProperty("line.separator"));
            if (IncludeFlag)
                Verilog .append(depth)
                        .append("Flag <= 0;")
                        .append(System.getProperty("line.separator"));
            Verilog .append("        end")
                    .append(System.getProperty("line.separator"))
                    .append("        else begin")
                    .append(System.getProperty("line.separator"));
        }
        if (Feedback == FeedbackType.ONE2MANY) {
            Verilog .append(depth)
                            .append("LFSR[0] <= feedback;")
                            .append(System.getProperty("line.separator"));
            int j = 0;
            for(int i = 0; i < NumberOfBits-1; i++) {
                Verilog .append(depth)
                        .append("LFSR[")
                        .append(i+1)
                        .append("] <= LFSR[")
                        .append(i)
                        .append("];");
                if (Taps[j]==(i+1)) {
                    Verilog .delete(Verilog.length()-1,Verilog.length());
                    if (Gate == GateType.XNOR) Verilog.append(" ~");
                    else           Verilog.append(" ");
                    Verilog .append("^ feedback;");
                    j++;
                }
                Verilog .append(System.getProperty("line.separator"));
            }
        }
        else {
            Verilog .append(depth)
                    .append("LFSR[0] <=");
            if (Gate == GateType.XNOR) Verilog.append(" ~");
            else                       Verilog.append(" ");
            for(int i = 0; i < Taps.length; i++)
                Verilog.append("LFSR[").append(Taps[i]-1).append("] ^ "); 
            if (Extended) {
                Verilog .append("(LFSR[")
                        .append(NumberOfBits-2)
                        .append("] == ")
                        .append(NumberOfBits)
                        .append("'b");
                if (Gate == GateType.XNOR) {
                    for(int i=0; i<NumberOfBits ; i++)
                            Verilog .append("1");
                }
                else {
                    for(int i=0; i<NumberOfBits ; i++)
                            Verilog .append("0");
                }
                Verilog .append(")");
            }
            else
                Verilog .delete(Verilog.length()-3,Verilog.length());
            Verilog .append(";")
                    .append(System.getProperty("line.separator"))
                    .append(depth)
                    .append("LFSR[")
                    .append(NumberOfBits-1)
                    .append(":1] <= LFSR[")
                    .append(NumberOfBits-2)
                    .append(":0];")
                    .append(System.getProperty("line.separator"));
        }
        if (IncludeFlag) {
            Verilog .append(depth)
                    .append("if (LFSR == ")
                    .append(NumberOfBits)
                    .append("'b")
                    .append(flagseq)
                    .append(")")
                    .append(System.getProperty("line.separator"))
                    .append(depth).append("    ")
                    .append("Flag <= 1;")
                    .append(System.getProperty("line.separator"));
        }
        if (IncludeReset)
            Verilog .append("        end")
                    .append(System.getProperty("line.separator"));
        Verilog .append("    end")
                .append(System.getProperty("line.separator"))
                .append(System.getProperty("line.separator"))
                .append("    assign Q = LFSR;")
                .append(System.getProperty("line.separator"))
                .append("endmodule");
        return Verilog.toString();
    }

   /**
    * toVHDL
    * Not yet used.
    * 
    * @return string "Work in progress..."
    */
    public String toVHDL() {
        return "Work in progress...";
    }

   /**
    * toAHDL
    * Not yet used.
    * 
    * @return string "Work in progress..."
    */
    public String toAHDL() {
        return "Work in progress...";
    }
    
   /**
    * toMyHDL
    * Not yet used.
    * 
    * @return string "Work in progress..."
    */
    public String toMyHDL() {
        return "Work in progress...";
    }

    //**************************************************************************
    // Miscellaneous Private Functions
    //**************************************************************************

    private String BitsToString() {
        String x = java.util.Arrays.toString(Bits)
                .replace("true", "1").replace("false", "0")
                .replace(",","").replace(" ","");
        return x.substring(1,x.length()-2);
    }

    private boolean areAllTrue(boolean[] array) {
        if (Gate == GateType.XOR) Bits[NumberOfBits] = true;
        for(boolean b : array) if (!b) return false;
        return true;
    }

    private boolean areAllFalse(boolean[] array) {
        for(boolean b : array) if (b) return false;
        return true;
    }

    private void initTimeOut(long miliSecTimeOut) {
        TimeOut = System.currentTimeMillis() + miliSecTimeOut;
    }

    private boolean isTimeOut() {
        if (System.currentTimeMillis()>TimeOut) {
            TimeOutFlag = true;
            return true;
        }
        return false;
    }

    private boolean XMSB() {
        if (Bits[0]) {
            for(int i = 1; i < NumberOfBits; i++) {
                if (Bits[i])
                   return false;
            }
            return true;
        }
        return false;
    }

    private boolean XNMSB() {
        if (!Bits[0]) {
            for(int i = 1; i < NumberOfBits; i++) {
                if (!Bits[i])
                   return false;
            }
            return true;
        }
        return false;
    }
    
}