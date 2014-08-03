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
 * @version 1.3 17 May 2014
 * 
 */
public final class LFSR {

    private static final char XOR = 0;
    private static final char XNOR = 1;
    private static final char ONE2MANY = 0;
    private static final char MANY2ONE = 1;

    private int M;
    private int[] Taps;
    private boolean[] Bits;
    private char Gate;
    private char Feedback;
    private int SeqLength;
    private int Position;
    private boolean Extended;

    private static final int ACCEPTABLE_RUN_TIME = 5000;
    private long TimeOut;
    private boolean TimeOutFlag = false;

    private boolean[] StrobeBitClone;
    private boolean StrobeExtendedRestoreFlag;
    private boolean StrobeExtendedOnceFlag;

    //**********************************************
    //Constructors
    //**********************************************

    public LFSR(int NumberOfBits, int[] Taps, String GateType, String FeedbackType, boolean isExtended){
        if( !setNumberOfBits(NumberOfBits) || !setTaps(Taps) || !setGateType(GateType) || !setFeedbackType(FeedbackType) )
            throw new IllegalArgumentException("Failed to initilize an LFSR class.");
        Extended = isExtended;
        SeqLength = -1;
        resetLFSR();
    }

    public LFSR(){
        this(2, new int[] {1}, "XOR", "MANY2ONE", false);
    }

    public LFSR(int NumberOfBits, int[] Taps){
         this(NumberOfBits, Taps, "XOR", "MANY2ONE", false);
    }

    public LFSR(int NumberOfBits, int[] Taps, String GateType){
        this(NumberOfBits, Taps, GateType, "MANY2ONE", false);
    }

    public LFSR(int NumberOfBits, int[] Taps, String GateType, String FeedbackType){
        this(NumberOfBits, Taps, GateType, FeedbackType, false);
    }

    public LFSR(int NumberOfBits, String GateType, String FeedbackType, boolean isExtended){
        this(NumberOfBits, new int[] {1}, GateType, FeedbackType, isExtended);
    }

    public LFSR(int NumberOfBits, String GateType, String FeedbackType){
        this(NumberOfBits, new int[] {1}, GateType, FeedbackType, false);
    }

    public LFSR(int NumberOfBits, String GateType){
        this(NumberOfBits, new int[] {1}, GateType, "MANY2ONE", false);
    }

    //**********************************************
    // Set Functions ( & reset )
    //**********************************************

    public boolean setGateType(String GateType){
        switch (GateType.toUpperCase()) {
            case "XOR":
                Gate = XOR;
                break;
            case "XNOR":
                Gate = XNOR;
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean setFeedbackType(String FeedbackType){
        switch (FeedbackType.toUpperCase()) {
            case "ONE2MANY":
                Feedback = ONE2MANY;
                break;
            case "MANY2ONE":
                Feedback = MANY2ONE;
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean setNumberOfBits(int NumberofBits){
        SeqLength = -1;
        if(NumberofBits <= 1)
            return false;
        boolean initialValue = true;
        if(Gate == XNOR)
            initialValue = false;
        M = NumberofBits;
        Bits = new boolean[M + 1];
        for(int i = 0; i < M; i++)
            Bits[i] = initialValue;
        return true;
    }

    public boolean setTaps(int[] NewTaps){
        SeqLength = -1;
        NewTaps = removeZeros(NewTaps);
        if(NewTaps.length==0)
            return false;
        java.util.Arrays.sort(NewTaps);
        Taps = NewTaps;
        return true;
    }

    public void setExtended(boolean NewVal){
        Extended = NewVal;
    }

    public void resetTimeOutFlag(){
        TimeOutFlag = false;
    }

    public boolean resetLFSR(){
        int SeqLenClone = SeqLength;
        setNumberOfBits(M);
        SeqLength = SeqLenClone;
        Position = 0;
        StrobeExtendedRestoreFlag = false;
        StrobeExtendedOnceFlag = false;
        return true;
    }

    //**********************************************
    // Get Functions
    //**********************************************

    public boolean isExtended(){
        return Extended;
    }

    public int getNumberOfBits(){
        return M;
    }

    public int[] getTaps(){
        return Taps;
    }

    public int getPosition(){
        return Position;
    }

    public String getBitsForward(){
        String currentBits = BitsToString();
        switch(2*Gate+Feedback){
            case 0: //XOR,  ONE2MANY
            case 2: //XNOR, One2MANY
                return new StringBuilder(currentBits).reverse().toString();
            case 1: //XOR,  MANY2ONE
            case 3: //XNOR, MANY2ONE
                return currentBits;
            default: return "Default";
        }
    }

    public String getBitsBackward(){
        String currentBits = BitsToString();
        switch(2*Gate+Feedback){
            case 0: //XOR,  ONE2MANY
            case 2: //XNOR, One2MANY
                return currentBits;
            case 1: //XOR,  MANY2ONE
            case 3: //XNOR, MANY2ONE
                return new StringBuilder(currentBits).reverse().toString();
            default: return "Default";
        }
    }

    public int getSeqLength(){
        if(SeqLength==-1)
            return CalculateSeqLength();
        return SeqLength;
    }

    public int[] getOptimizedTaps(int NumberOfBits){
        int[][] x = OptimalTaps.getOptimalTaps(NumberOfBits);
        return x[0];
    }

    public int[] getOptimizedTaps(){
        return getOptimizedTaps(M);
    }

    public boolean getTimeOutFlag(){
        return TimeOutFlag;
    }

    public String getSeq(int start, int stop, boolean bitDirection){
        initTimeOut(ACCEPTABLE_RUN_TIME);
        for(int i = 0; i < start; i++){
            strobeClock();
            if(isTimeOut())
                return "Time Out";
        }
        StringBuilder text = new StringBuilder();
        if(!bitDirection){
            for(int i = start; i < stop; i++){
                text.append(i)
                    .append("\t")
                    .append(getBitsBackward())
                    .append(System.getProperty("line.separator"));
                strobeClock();
                if(isTimeOut())
                    break;
            }
        } else{
            for(int i = start; i < stop; i++){
                text.append(i)
                    .append("\t")
                    .append(getBitsForward())
                    .append(System.getProperty("line.separator"));
                strobeClock();
                if(isTimeOut())
                    break;
            }
        }
        return text.toString();
    }

    //**********************************************
    // Core Operations
    //**********************************************
    
    public void strobeClockAll(){
        if(StrobeExtendedRestoreFlag){
            Bits = StrobeBitClone.clone();
            StrobeExtendedRestoreFlag = false;
            StrobeExtendedOnceFlag = true;
        }
        if(Extended && !StrobeExtendedOnceFlag && ((XMSB() && (Gate== XOR))|| (XNMSB() && (Gate == XNOR)) )){
            StrobeExtendedRestoreFlag = true;
            StrobeBitClone = Bits.clone();
            if(Gate == XOR){
                for(int i = 0; i < M; i++)
                        Bits[i] = false;
            } else{
                for(int i = 0; i < M; i++)
                        Bits[i] = true;
            }
        } else{
            if(Feedback == MANY2ONE){
                if(Gate == XOR){
                    Bits[M] = false;
                    for(int i = 0; i < Taps.length; i++)
                        Bits[M] ^= Bits[M - Taps[i]];
                } else {
                    Bits[M] = true;
                    for(int i = 0; i < Taps.length; i++)
                        Bits[M] = !(Bits[M] ^ Bits[M - Taps[i]]);
                }
                for(int i = 0; i < M; i++)
                    Bits[i] = Bits[i + 1];
            }
            else{
                boolean[] BitsClone = Bits.clone();
                int j = 0;
                Bits[0] = BitsClone[M-1];
                for(int i = 1; i < M; i++){
                    if((Taps[j] == i) && (Taps[j] != M)){
                        Bits[i] = BitsClone[i-1] ^ BitsClone[M-1];
                        if(Gate == XNOR)
                            Bits[i] = !Bits[i];
                        j++;
                    } else
                        Bits[i] = BitsClone[i-1];
                }
            }
        }
        Position++;
    }

    private boolean preStrobeExtendedXOR(){
        if(StrobeExtendedRestoreFlag){
            Bits = StrobeBitClone.clone();
            StrobeExtendedRestoreFlag = false;
            StrobeExtendedOnceFlag = true;
        }
        if(!StrobeExtendedOnceFlag && XMSB()){
            StrobeExtendedRestoreFlag = true;
            StrobeBitClone = Bits.clone();
            for(int i = 0; i < M; i++)
                    Bits[i] = false;
            return true;
        }
        return false;
    }

    private boolean preStrobeExtendedXNOR(){
        if(StrobeExtendedRestoreFlag){
            Bits = StrobeBitClone.clone();
            StrobeExtendedRestoreFlag = false;
            StrobeExtendedOnceFlag = true;
        }
        if(!StrobeExtendedOnceFlag && XNMSB()){
            StrobeExtendedRestoreFlag = true;
            StrobeBitClone = Bits.clone();
            for(int i = 0; i < M; i++)
                    Bits[i] = true;
            return true;
        }
        return false;
    }

    private void strobeClockA(){
        if(!preStrobeExtendedXOR())
            strobeClockE();
    }

    private void strobeClockB(){
        if(!preStrobeExtendedXOR())
            strobeClockF();
    }

    private void strobeClockC(){
        if(!preStrobeExtendedXNOR())
            strobeClockG();
    }

    private void strobeClockD(){
        if(!preStrobeExtendedXNOR())
            strobeClockH();
    }

    private void strobeClockE(){
        boolean[] BitsClone = Bits.clone();
        int j = 0;
        Bits[0] = BitsClone[M-1];
        for(int i = 1; i < M; i++){
            if((Taps[j] == i) && (Taps[j] != M)){
                Bits[i] = BitsClone[i-1] ^ BitsClone[M-1];
                j++;
            } else
                Bits[i] = BitsClone[i-1];
        }
    }

    private void strobeClockF(){
        Bits[M] = false;
        for(int i = 0; i < Taps.length; i++)
            Bits[M] ^= Bits[M - Taps[i]];
        for(int i = 0; i < M; i++)
            Bits[i] = Bits[i + 1];
    }

    private void strobeClockG(){
        boolean[] BitsClone = Bits.clone();
        int j = 0;
        Bits[0] = BitsClone[M-1];
        for(int i = 1; i < M; i++){
            if((Taps[j] == i) && (Taps[j] != M)){
                Bits[i] = !(BitsClone[i-1] ^ BitsClone[M-1]);
                j++;
            } else
                Bits[i] = BitsClone[i-1];
        }
    }

    private void strobeClockH(){
        Bits[M] = true;
        for(int i = 0; i < Taps.length; i++)
            Bits[M] = !(Bits[M] ^ Bits[M - Taps[i]]);
        for(int i = 0; i < M; i++)
            Bits[i] = Bits[i + 1];
    }

    public void strobeClock(){
        if(Extended){
            switch(2*Gate+Feedback){
                case 0: //XOR,  ONE2MANY
                    strobeClockA();
                    break;
                case 1: //XOR,  MANY2ONE
                    strobeClockB();
                    break;
                case 2: //XNOR, One2MANY
                    strobeClockC();
                    break;
                case 3: //XNOR, MANY2ONE
                    strobeClockD();
                    break;
            }
        } else{
            switch(2*Gate+Feedback){
                case 0: //XOR,  ONE2MANY
                    strobeClockE();
                    break;
                case 1: //XOR,  MANY2ONE
                    strobeClockF();
                    break;
                case 2: //XNOR, One2MANY
                    strobeClockG();
                    break;
                case 3: //XNOR, MANY2ONE
                    strobeClockH();
                    break;
            }
        }
    }

    public int CalculateSeqLength(){
        int length = 0;
        boolean[] BitsClone = Bits.clone();
        setNumberOfBits(M);
        initTimeOut(ACCEPTABLE_RUN_TIME);
        strobeClock();
        if(Gate == XOR){
            while(!areAllTrue(Bits)){
                length++;
                strobeClock();
                if(isTimeOut())
                    break;
            }
        } else{
            while(!areAllFalse(Bits)){
                length++;
                strobeClock();
                if(isTimeOut())
                    break;
            }
        }
        Bits = BitsClone;
        SeqLength = length + 1;
        resetLFSR();
        return SeqLength;
    }

    //**********************************************
    // Printing Functions
    //**********************************************

    public void printBitsForward(){
        switch(2*Gate+Feedback){
            case 0: //XOR,  ONE2MANY
            case 2: //XNOR, One2MANY
                printB();
                break;
            case 1: //XOR,  MANY2ONE
            case 3: //XNOR, MANY2ONE
                printA();
                break;
        }
    }

    public void printBitsBackward(){
        switch(2*Gate+Feedback){
            case 0: //XOR,  ONE2MANY
            case 2: //XNOR, One2MANY
                printA();
                break;
            case 1: //XOR,  MANY2ONE
            case 3: //XNOR, MANY2ONE
                printB();
                break;
        }
    }

    //**********************************************
    // To Language Functions
    //**********************************************

    public String toVerilog(boolean IncludeReset, boolean IncludeFlag, int CycleNumb){
        StringBuilder Verilog = new StringBuilder();
        String depth = "        ";
        String flagseq = "INITILIZE";
        if(IncludeFlag){
            if(CycleNumb > getSeqLength())
                flagseq = "ERROR";
            else{
                resetLFSR();
                for (int i = 0; i < CycleNumb; i++)
                    strobeClock();
                flagseq = getBitsForward();
            }
        }
        if(IncludeReset)
            depth = "            ";
        Verilog .append("module LFSR(Clock, ");
        if(IncludeReset)
            Verilog .append("Reset, ");
        if(IncludeFlag)
            Verilog .append("Flag, ");
        Verilog .append("Q);")
                .append(System.getProperty("line.separator"))
                .append("    input Clock;")
                .append(System.getProperty("line.separator"));
        if(IncludeReset)
            Verilog .append("    input Reset;")
                    .append(System.getProperty("line.separator"));
        if(IncludeFlag)
            Verilog .append("    output Flag;")
                    .append(System.getProperty("line.separator"));
        Verilog .append("    output [").append(M-1).append(":0] Q;")
                .append(System.getProperty("line.separator"))
                .append(System.getProperty("line.separator"))
                .append("    reg [").append(M-1).append(":0] LFSR;")
                .append(System.getProperty("line.separator"));
        if(Feedback==ONE2MANY){
                Verilog .append("    wire feedback = LFSR[")
                        .append(M-1).append("]");
                if(Extended)
                    Verilog .append("^(LFSR[")
                            .append(M-2)
                            .append(":0] == ")
                            .append(M)
                            .append("'b");
                if(Gate == XNOR)
                    for(int i=0; i<M ; i++)
                        Verilog .append("0");
                else{
                    for(int i=0; i<M ; i++)
                        Verilog .append("1");
                }
                Verilog .append(");")
                        .append(System.getProperty("line.separator"));
        }
        Verilog .append(System.getProperty("line.separator"))
                .append("    always @(posedge Clock) begin")
                .append(System.getProperty("line.separator"));
        if(IncludeReset){
            Verilog .append("        if(Reset == 0) begin")
                    .append(System.getProperty("line.separator"))
                    .append("            LFSR <= ")
                    .append(M)
                    .append("'b");
            if(Gate == XNOR)
                for(int i=0; i<M ; i++)
                    Verilog .append("0");
            else{
                for(int i=0; i<M ; i++)
                    Verilog .append("1");
            }
            Verilog .append(";")
                    .append(System.getProperty("line.separator"));
            if(IncludeFlag)
                Verilog .append(depth)
                        .append("Flag <= 0;")
                        .append(System.getProperty("line.separator"));
            Verilog .append("        end")
                    .append(System.getProperty("line.separator"))
                    .append("        else begin")
                    .append(System.getProperty("line.separator"));
        }
        switch(2*Gate+Feedback){
            case 0: //XOR,  ONE2MANY
            case 2: //XNOR, ONE2MANY
                Verilog .append(depth)
                        .append("LFSR[0] <= feedback;")
                        .append(System.getProperty("line.separator"));
                int j = 0;
                for(int i = 0; i < M-1; i++){
                     Verilog .append(depth)
                             .append("LFSR[")
                             .append(i+1)
                             .append("] <= LFSR[")
                             .append(i)
                             .append("];");
                     if(Taps[j]==(i+1)){
                         Verilog .delete(Verilog.length()-1,Verilog.length());
                         if(Gate==XNOR) Verilog.append(" ~");
                         else           Verilog.append(" ");
                         Verilog .append("^ feedback;");
                         j++;
                     }
                     Verilog .append(System.getProperty("line.separator"));
                }
                break;
            case 1: //XOR,  MANY2ONE
            case 3: //XNOR, MANY2ONE
                Verilog .append(depth)
                        .append("LFSR[0] <=");
                if(Gate==XNOR) Verilog.append(" ~");
                else           Verilog.append(" ");
                for(int i = 0; i < Taps.length; i++)
                    Verilog.append("LFSR[").append(Taps[i]-1).append("] ^ "); 
                if(Extended){
                    Verilog .append("(LFSR[")
                            .append(M-2)
                            .append("] == ")
                            .append(M)
                            .append("'b");
                    if(Gate==XNOR){
                        for(int i=0; i<M ; i++)
                            Verilog .append("1");
                    }
                    else{
                        for(int i=0; i<M ; i++)
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
                        .append(M-1)
                        .append(":1] <= LFSR[")
                        .append(M-2)
                        .append(":0];")
                        .append(System.getProperty("line.separator"));
                break;
        }
        if(IncludeFlag){
            Verilog .append(depth)
                    .append("if(LFSR == ")
                    .append(M)
                    .append("'b")
                    .append(flagseq)
                    .append(")")
                    .append(System.getProperty("line.separator"))
                    .append(depth).append("    ")
                    .append("Flag <= 1;")
                    .append(System.getProperty("line.separator"));
        }
        if(IncludeReset)
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

    public String toVHDL(){
        return "Work in progress...";
    }

    public String toAHDL(){
        return "Work in progress...";
    }
    
    public String toMyHDL(){
        return "Work in progress...";
    }

    //**********************************************
    // Miscallaneous Private Functions
    //**********************************************

    private String BitsToString(){
        String x = java.util.Arrays.toString(Bits)
                .replace("true", "1").replace("false", "0")
                .replace(",","").replace(" ","");
        return x.substring(1,x.length()-2);
    }

    private void printA(){
        for(int i = 0; i <= M - 1; i++)
            System.out.print(Bits[i] ? 1 : 0);
        System.out.println();
    }

    private void printB(){
        for(int i = M - 1; i >= 0; i--)
            System.out.print(Bits[i] ? 1 : 0);
        System.out.println();
    }

    private boolean areAllTrue(boolean[] array){
        if(Gate == XOR) Bits[M] = true;
        for(boolean b : array) if(!b) return false;
        return true;
    }

    private boolean areAllFalse(boolean[] array){
        for(boolean b : array) if(b) return false;
        return true;
    }

    private static int[] removeZeros(int[] array){
        int j = 0;
        for(int i=0;  i < array.length;  i++){
            if (array[i] != 0)
                array[j++] = array[i];
        }
        int [] newArray = new int[j];
        System.arraycopy( array, 0, newArray, 0, j );
        return newArray;
    }

    private void initTimeOut(long miliSecTimeOut){
        TimeOut = System.currentTimeMillis() + miliSecTimeOut;
    }

    private boolean isTimeOut(){
        if(System.currentTimeMillis()>TimeOut){
            TimeOutFlag = true;
            return true;
        }
        return false;
    }

    private boolean XMSB(){
        if(Bits[0]){
            for(int i = 1; i < M; i++){
                if(Bits[i])
                   return false;
            }
            return true;
        }
        return false;
    }

    private boolean XLSB(){
        if(Bits[M]){
            for(int i = 0; i < M-1; i++){
                if(Bits[i])
                   return false;
            }
            return true;
        }
        return false;
    }

    private boolean XNMSB(){
        if(!Bits[0]){
            for(int i = 1; i < M; i++){
                if(!Bits[i])
                   return false;
            }
            return true;
        }
        return false;
    }
    
    private boolean XNLSB(){
        if(!Bits[M]){
            for(int i = 0; i < M-1; i++){
                if(!Bits[i])
                   return false;
            }
            return true;
        }
        return false;
    } 
}