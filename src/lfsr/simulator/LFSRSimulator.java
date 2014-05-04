package lfsr.simulator;

import javax.swing.UnsupportedLookAndFeelException;

public class LFSRSimulator {
    public static void main(String[] args) {
        try{
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){}

        View view = new View();
        view.setVisible(true);
    }

    //To do:
    // Image of circuit
    // AHDL
    // VHDL
    // About section
    // Flip Bit Direction
}