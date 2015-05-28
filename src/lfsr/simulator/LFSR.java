// Author: Adam Nunez, adam.a.nunez@gmail.com
// Copyright (C) 2014  name of author
// 
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

package lfsr.simulator;


/*
 * 
 * LFSR.java
 * Purpose: Simulates a XOR/XNOR, MANY-TO-ONE/ONE-TO-MANY LFSR.
 *          The taps for the LFSR are one indexed. Inspiration
 *          for this project comes from...
 *          http://fab.cba.mit.edu/classes/MIT/864.05/people/gdennis/
 *
 * @author Adam Nunez, adam.a.nunez@gmail.com
 * @version 1.4 22 April 2015
 * 
 */
public class LFSR {

    private int NumberOfBits;
    private int[] Taps;
    private boolean[] Bits;
    
    private GateType Gate = GateType.XOR;
    private FeedbackType Feedback = FeedbackType.ONE2MANY;
    private int SeqLength = -1;
    private int Position = 0;
    private boolean Extended = false;

    private static final int ACCEPTABLE_RUN_TIME = 5000;
    private long TimeOut;
    private boolean TimeOutFlag;

    private boolean[] StrobeBitClone;
    private boolean StrobeExtendedRestoreFlag;
    private boolean StrobeExtendedOnceFlag;

    //**********************************************
    //Constructors
    //**********************************************

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

    //**********************************************
    // Set Functions ( & reset )
    //**********************************************

    public final void setGateType(GateType val) {
        Gate = val;
    }

    public final void setFeedbackType(FeedbackType val) {
        Feedback = val;
    }

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

    public final boolean setTaps(int[] NewTaps) {
        SeqLength = -1;
        if ( NewTaps.length == 0 )
            Taps = getOptimizedTaps();
        else {
            java.util.Arrays.sort( NewTaps );
            if ( NewTaps[0] == 0 )
                return false;
            int tmp;
            for(int i = 0; i < NewTaps.length / 2; i++) {
                tmp = NewTaps[i];
                NewTaps[i] = NewTaps[NewTaps.length - i - 1];
                NewTaps[NewTaps.length - i - 1] = tmp;
            }
            Taps = NewTaps;
        }
        return true;
    }

    public final void setExtended(boolean val) {
        Extended = val;
    }

    public final void resetTimeOutFlag() {
        TimeOutFlag = false;
    }

    public final void resetLFSR() {
        int SeqLenClone = SeqLength;
        setNumberOfBits(NumberOfBits);
        SeqLength = SeqLenClone;
        Position = 0;
        StrobeExtendedRestoreFlag = false;
        StrobeExtendedOnceFlag = false;
    }

    //**********************************************
    // Get Functions
    //**********************************************

    public boolean isExtended() {
        return Extended;
    }

    public int getNumberOfBits() {
        return NumberOfBits;
    }

    public int[] getTaps() {
        return Taps;
    }

    public int getPosition() {
        return Position;
    }

    public String getBitsForward() {
        String currentBits = BitsToString();
        if (Feedback == FeedbackType.MANY2ONE)
            return currentBits;
        else
            return new StringBuilder(currentBits).reverse().toString();
    }

    public String getBitsBackward() {
        String currentBits = BitsToString();
        if (Feedback == FeedbackType.ONE2MANY)
            return currentBits;
        else
            return new StringBuilder(currentBits).reverse().toString();
    }

    public int getSequenceLength() {
        if (SeqLength == -1)
            return CalculateSeqLength();
        return SeqLength;
    }

    public int[] getOptimizedTaps(int NumberOfBits) {
        int[][] x = OptimalTaps.getOptimalTaps(NumberOfBits);
        return x[0];
    }

    public int[] getOptimizedTaps() {
        return getOptimizedTaps(NumberOfBits);
    }

    public boolean getTimeOutFlag() {
        return TimeOutFlag;
    }

    public String getBitSequence(int start, int stop, boolean bitDirection) {
        initTimeOut(ACCEPTABLE_RUN_TIME);
        for(int i = 0; i < start; i++) {
            strobeClock();
            if (isTimeOut())
                return "timeout";
        }
        StringBuilder text = new StringBuilder();
        for(int i = start; i < stop; i++) {
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

    //**********************************************
    // Core Operations
    //**********************************************

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

    public void strobeClock() {
        System.out.println(this.getBitsForward());
        if (Gate == GateType.XOR && Feedback == FeedbackType.ONE2MANY) 
            System.out.println("ok, we are kosher");
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

    public int CalculateSeqLength() {
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

    //**********************************************
    // To Language Functions
    //**********************************************

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

    public String toVHDL() {
        return "Work in progress...";
    }

    public String toAHDL() {
        return "Work in progress...";
    }
    
    public String toMyHDL() {
        return "Work in progress...";
    }

    //**********************************************
    // Miscellaneous Private Functions
    //**********************************************

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