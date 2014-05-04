package lfsr.simulator;

import java.awt.event.ActionEvent;

/**
 *
 * View.java
 * Purpose: Its called View, what do you think it does?
 *
 * @author Adam Nunez, aanunez@uh.edu
 * @version 1.0 27 March 2014
 *
 */

public class View extends javax.swing.JFrame {

    LFSR lfsr = new LFSR();

    public View() {
        initComponents();
        initActionListeners();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GateRadioGroup = new javax.swing.ButtonGroup();
        FeedbackRadioGroup = new javax.swing.ButtonGroup();
        TapSelectionGroup = new javax.swing.ButtonGroup();
        SeqLengthButtonGroup = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        TapsTextArea = new javax.swing.JScrollPane();
        TapsList = new javax.swing.JList();
        GenerateButton = new javax.swing.JButton();
        LFSRSettingsPanel = new javax.swing.JPanel();
        XorRadio = new javax.swing.JRadioButton();
        XnorRadio = new javax.swing.JRadioButton();
        ManyToOneRadio = new javax.swing.JRadioButton();
        OneToManyRadio = new javax.swing.JRadioButton();
        BitsTextArea = new javax.swing.JTextField();
        NumberOfBits = new java.awt.Label();
        GateType = new java.awt.Label();
        FeedbackType = new java.awt.Label();
        TapSelection = new java.awt.Label();
        SeqLength = new java.awt.Label();
        n1RadioButton = new javax.swing.JRadioButton();
        nRadioButton = new javax.swing.JRadioButton();
        SequenceLength = new java.awt.Label();
        SeqLengthTextArea = new java.awt.Label();
        ManualRadio = new javax.swing.JRadioButton();
        AutoRadio = new javax.swing.JRadioButton();
        LFSRSettings = new java.awt.Label();
        Taps = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        label8 = new java.awt.Label();
        label9 = new java.awt.Label();
        UpperTextArea = new javax.swing.JTextField();
        LowerTextArea = new javax.swing.JTextField();
        FullSeqCheckBox = new javax.swing.JCheckBox();
        DisplaySettings = new java.awt.Label();
        TabbedPane = new javax.swing.JTabbedPane();
        SequencePanel = new javax.swing.JPanel();
        SequenceScroll = new javax.swing.JScrollPane();
        SeqTextArea = new javax.swing.JTextArea();
        VerilogPanel = new javax.swing.JPanel();
        VerilogScroll = new javax.swing.JScrollPane();
        VerilogTextArea = new javax.swing.JTextArea();
        VHDLPanel = new javax.swing.JPanel();
        VHDLScroll = new javax.swing.JScrollPane();
        VHDLTextArea = new javax.swing.JTextArea();
        AHDLPanel = new javax.swing.JPanel();
        AHDLScroll = new javax.swing.JScrollPane();
        AHDLTextArea = new javax.swing.JTextArea();
        MenuBar = new javax.swing.JMenuBar();
        About = new javax.swing.JMenu();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LFSR Simulator");
        setResizable(false);

        TapsTextArea.setViewportView(TapsList);

        GenerateButton.setText("Generate");

        LFSRSettingsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        GateRadioGroup.add(XorRadio);
        XorRadio.setSelected(true);
        XorRadio.setText("Xor");
        XorRadio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        GateRadioGroup.add(XnorRadio);
        XnorRadio.setText("Xnor");

        FeedbackRadioGroup.add(ManyToOneRadio);
        ManyToOneRadio.setSelected(true);
        ManyToOneRadio.setText("Many to One");

        FeedbackRadioGroup.add(OneToManyRadio);
        OneToManyRadio.setText("One to Many");

        BitsTextArea.setText("15");

        NumberOfBits.setText("Number of Bits:");

        GateType.setText("Gate Type");

        FeedbackType.setName(""); // NOI18N
        FeedbackType.setText("Feedback Type");

        TapSelection.setText("Tap Selection");

        SeqLength.setText("Seq Length");

        SeqLengthButtonGroup.add(n1RadioButton);
        n1RadioButton.setSelected(true);
        n1RadioButton.setText("n^2-1");

        SeqLengthButtonGroup.add(nRadioButton);
        nRadioButton.setText("n^2");

        SequenceLength.setText("Sequence Length:");

        SeqLengthTextArea.setText("0");

        TapSelectionGroup.add(ManualRadio);
        ManualRadio.setText("Manual");

        TapSelectionGroup.add(AutoRadio);
        AutoRadio.setSelected(true);
        AutoRadio.setText("Auto");

        javax.swing.GroupLayout LFSRSettingsPanelLayout = new javax.swing.GroupLayout(LFSRSettingsPanel);
        LFSRSettingsPanel.setLayout(LFSRSettingsPanelLayout);
        LFSRSettingsPanelLayout.setHorizontalGroup(
            LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addComponent(NumberOfBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BitsTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TapSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GateType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(XnorRadio)
                            .addComponent(XorRadio)
                            .addComponent(AutoRadio)
                            .addComponent(ManualRadio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ManyToOneRadio)
                            .addComponent(OneToManyRadio)
                            .addComponent(n1RadioButton)
                            .addComponent(nRadioButton)
                            .addComponent(SeqLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FeedbackType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addComponent(SequenceLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SeqLengthTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)))
                .addContainerGap())
        );
        LFSRSettingsPanelLayout.setVerticalGroup(
            LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GateType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FeedbackType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addComponent(XorRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(XnorRadio))
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addComponent(ManyToOneRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OneToManyRadio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TapSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SeqLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addComponent(n1RadioButton)
                        .addGap(26, 26, 26))
                    .addComponent(nRadioButton)
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addComponent(AutoRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ManualRadio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NumberOfBits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BitsTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SeqLengthTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(SequenceLength, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        LFSRSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LFSRSettings.setFont(new java.awt.Font("Dialog", 1, 12));
        LFSRSettings.setName(""); // NOI18N
        LFSRSettings.setText("LFSR Settings");

        Taps.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Taps.setFont(new java.awt.Font("Dialog", 1, 12));
        Taps.setText("Taps");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        label8.setText("Upper Bound:");

        label9.setText("Lower Bound:");

        UpperTextArea.setText("255");

        LowerTextArea.setText("0");

        FullSeqCheckBox.setText("Display Full Sequence");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UpperTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LowerTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                    .addComponent(FullSeqCheckBox))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(UpperTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LowerTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FullSeqCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FullSeqCheckBox.getAccessibleContext().setAccessibleName("");

        DisplaySettings.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DisplaySettings.setFont(new java.awt.Font("Dialog", 1, 12));
        DisplaySettings.setName(""); // NOI18N
        DisplaySettings.setText("Display Settings");

        SequenceScroll.setBorder(null);

        SeqTextArea.setColumns(20);
        SeqTextArea.setEditable(false);
        SeqTextArea.setRows(5);
        SeqTextArea.setAlignmentX(0.0F);
        SeqTextArea.setAlignmentY(0.0F);
        SeqTextArea.setBorder(null);
        SequenceScroll.setViewportView(SeqTextArea);

        javax.swing.GroupLayout SequencePanelLayout = new javax.swing.GroupLayout(SequencePanel);
        SequencePanel.setLayout(SequencePanelLayout);
        SequencePanelLayout.setHorizontalGroup(
            SequencePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SequenceScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );
        SequencePanelLayout.setVerticalGroup(
            SequencePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SequenceScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        TabbedPane.addTab("Sequence", SequencePanel);

        VerilogScroll.setBorder(null);

        VerilogTextArea.setColumns(20);
        VerilogTextArea.setRows(5);
        VerilogScroll.setViewportView(VerilogTextArea);

        javax.swing.GroupLayout VerilogPanelLayout = new javax.swing.GroupLayout(VerilogPanel);
        VerilogPanel.setLayout(VerilogPanelLayout);
        VerilogPanelLayout.setHorizontalGroup(
            VerilogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VerilogScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );
        VerilogPanelLayout.setVerticalGroup(
            VerilogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VerilogScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        TabbedPane.addTab("Verilog", VerilogPanel);

        VHDLScroll.setBorder(null);

        VHDLTextArea.setColumns(20);
        VHDLTextArea.setRows(5);
        VHDLScroll.setViewportView(VHDLTextArea);

        javax.swing.GroupLayout VHDLPanelLayout = new javax.swing.GroupLayout(VHDLPanel);
        VHDLPanel.setLayout(VHDLPanelLayout);
        VHDLPanelLayout.setHorizontalGroup(
            VHDLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );
        VHDLPanelLayout.setVerticalGroup(
            VHDLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        TabbedPane.addTab("VHDL", VHDLPanel);

        AHDLScroll.setBorder(null);

        AHDLTextArea.setColumns(20);
        AHDLTextArea.setRows(5);
        AHDLScroll.setViewportView(AHDLTextArea);

        javax.swing.GroupLayout AHDLPanelLayout = new javax.swing.GroupLayout(AHDLPanel);
        AHDLPanel.setLayout(AHDLPanelLayout);
        AHDLPanelLayout.setHorizontalGroup(
            AHDLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );
        AHDLPanelLayout.setVerticalGroup(
            AHDLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        TabbedPane.addTab("AHDL", AHDLPanel);

        About.setText("About");
        MenuBar.add(About);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(DisplaySettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(111, 111, 111))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LFSRSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LFSRSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(GenerateButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Taps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TapsTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Taps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TapsTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DisplaySettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LFSRSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LFSRSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GenerateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        LFSRSettings.getAccessibleContext().setAccessibleDescription("");
        TabbedPane.getAccessibleContext().setAccessibleName("SequenceTextArea");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void UpdateTapsList(){
        lfsr.resetTimeOutFlag();
        javax.swing.DefaultListModel model = new javax.swing.DefaultListModel();
        if(AutoRadio.isSelected())
             lfsr.setTaps(lfsr.getOptimizedTaps());
        else{
            int[] adjArray = TapsList.getSelectedIndices();
            for(int i = 0; i < adjArray.length; i++)
                adjArray[i] = adjArray[i] + 1;
            lfsr.setTaps(adjArray);
        }
        for(int i = 1; i <= lfsr.getNumberOfBits(); i++)
            model.addElement(i);
        TapsList.clearSelection();
        TapsList.setModel(model);
        int[] adjArray = lfsr.getTaps().clone();
        for(int i = 0; i < adjArray.length; i++)
            adjArray[i] = adjArray[i] - 1;
        TapsList.setSelectedIndices(adjArray);
    }

    private void UpdateSeqList(){
        SeqTextArea.setText("");
        lfsr.resetTimeOutFlag();
        int start = 0;
        int stop = 0;
        if(FullSeqCheckBox.isSelected()){
            start = 0;
            stop = lfsr.getSeqLength();
        } else{
            start = getStart();
            stop = getStop();
        }
        SeqTextArea.append(lfsr.getSeq(start, stop, false));
        if(lfsr.getTimeOutFlag())
            SeqTextArea.append("***Timeout Waring Occured***");
        SeqTextArea.setCaretPosition(0);
        lfsr.resetLFSR();
    }

    private void UpdateLFSRSettings(){
        if(XorRadio.isSelected())
            lfsr.setGateType("XOR");
        else
            lfsr.setGateType("XNOR");
        if(ManyToOneRadio.isSelected())
            lfsr.setFeedbackType("MANY2ONE");
        else
            lfsr.setFeedbackType("ONE2MANY");
        if(nRadioButton.isSelected())
            lfsr.setExtended(true);
        else
            lfsr.setExtended(false);
        try{lfsr.setNumberOfBits(Integer.parseInt(BitsTextArea.getText()));}
        catch(NumberFormatException e){ lfsr.setNumberOfBits(1);}
        lfsr.resetLFSR();
    }

    private void UpdateCode(){
        VerilogTextArea.setText(lfsr.toVerilog());
        VHDLTextArea.setText(lfsr.toVHDL());
        AHDLTextArea.setText(lfsr.toAHDL());
    }

    private int getStop(){
        int stop = 0;
        try{
            if(Integer.parseInt(UpperTextArea.getText())>lfsr.getSeqLength())
                stop = lfsr.getSeqLength();
            else
                stop = Integer.parseInt(UpperTextArea.getText());
        }
        catch(NumberFormatException e){
            stop = lfsr.getSeqLength();
        }
        return stop;
    }

    private int getStart(){
        int start = 0;
        try{
            if(Integer.parseInt(LowerTextArea.getText())<1)
                start = 0;
            else
                start = Integer.parseInt(LowerTextArea.getText());
        }
        catch(NumberFormatException e){start = 0;}
        return start;
    }

    private void generateButton(){
        UpdateLFSRSettings();
        UpdateTapsList();
        UpdateSeqLength();
        UpdateSeqList();
        UpdateCode();
    }

    private void UpdateSeqLength(){
        lfsr.getSeqLength();
        if(lfsr.getTimeOutFlag())
            SeqLengthTextArea.setText("Time Out");
        else
            SeqLengthTextArea.setText(Integer.toString(lfsr.getSeqLength()));
    }

    private void initActionListeners(){
        addUpdateButtonListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateButton();
            }
        });

        addFullSeqCheckBoxListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LowerTextArea.setEditable(FullSeqCheckBox.isSelected() == false);
                UpperTextArea.setEditable(FullSeqCheckBox.isSelected() == false);
            }
        });

        addBitsTextAreaListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{lfsr.setNumberOfBits(Integer.parseInt(BitsTextArea.getText()));}
                catch(NumberFormatException f){lfsr.setNumberOfBits(1);}
                UpdateTapsList();
                UpdateSeqLength();
            }
        });
    }

    private void addUpdateButtonListener(java.awt.event.ActionListener UpdateButtonListener){
        GenerateButton.addActionListener(UpdateButtonListener);
    }

    private void addFullSeqCheckBoxListener(java.awt.event.ActionListener FullSeqCheckBoxListener){
        FullSeqCheckBox.addActionListener(FullSeqCheckBoxListener);
    }

    private void addBitsTextAreaListener(java.awt.event.ActionListener BitsTextAreaListener){
        BitsTextArea.addActionListener(BitsTextAreaListener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AHDLPanel;
    private javax.swing.JScrollPane AHDLScroll;
    private javax.swing.JTextArea AHDLTextArea;
    private javax.swing.JMenu About;
    private javax.swing.JRadioButton AutoRadio;
    private javax.swing.JTextField BitsTextArea;
    private java.awt.Label DisplaySettings;
    private javax.swing.ButtonGroup FeedbackRadioGroup;
    private java.awt.Label FeedbackType;
    private javax.swing.JCheckBox FullSeqCheckBox;
    private javax.swing.ButtonGroup GateRadioGroup;
    private java.awt.Label GateType;
    private javax.swing.JButton GenerateButton;
    private java.awt.Label LFSRSettings;
    private javax.swing.JPanel LFSRSettingsPanel;
    private javax.swing.JTextField LowerTextArea;
    private javax.swing.JRadioButton ManualRadio;
    private javax.swing.JRadioButton ManyToOneRadio;
    private javax.swing.JMenuBar MenuBar;
    private java.awt.Label NumberOfBits;
    private javax.swing.JRadioButton OneToManyRadio;
    private java.awt.Label SeqLength;
    private javax.swing.ButtonGroup SeqLengthButtonGroup;
    private java.awt.Label SeqLengthTextArea;
    private javax.swing.JTextArea SeqTextArea;
    private java.awt.Label SequenceLength;
    private javax.swing.JPanel SequencePanel;
    private javax.swing.JScrollPane SequenceScroll;
    private javax.swing.JTabbedPane TabbedPane;
    private java.awt.Label TapSelection;
    private javax.swing.ButtonGroup TapSelectionGroup;
    private java.awt.Label Taps;
    private javax.swing.JList TapsList;
    private javax.swing.JScrollPane TapsTextArea;
    private javax.swing.JTextField UpperTextArea;
    private javax.swing.JPanel VHDLPanel;
    private javax.swing.JScrollPane VHDLScroll;
    private javax.swing.JTextArea VHDLTextArea;
    private javax.swing.JPanel VerilogPanel;
    private javax.swing.JScrollPane VerilogScroll;
    private javax.swing.JTextArea VerilogTextArea;
    private javax.swing.JRadioButton XnorRadio;
    private javax.swing.JRadioButton XorRadio;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private javax.swing.JRadioButton n1RadioButton;
    private javax.swing.JRadioButton nRadioButton;
    // End of variables declaration//GEN-END:variables

}
