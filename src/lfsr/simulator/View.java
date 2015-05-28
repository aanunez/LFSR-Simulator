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
 * View.java
 * Purpose: Its called View, what do you think it does? Ok, well its also
 *          a controller.
 *
 * @author Adam Nunez, adam.a.nunez@gmail.com
 * @version 1.2 17 May 2014
 *
 */

public class View extends javax.swing.JFrame {

    LFSR lfsr = new LFSR();

    public View() {
        initComponents();
        setLocationRelativeTo(null);
        generateButton();
        AboutTextArea.setText("This is a simulator for designing and     \n"
                            + "understanding LFSRs.                      \n"
                            + "                                          \n"
                            + "This program was designed by Adam Nunez   \n"
                            + "and is licensed under GPLv2.              \n"
                            + "                                          \n"
                            + "For question or comments please feel free \n"
                            + "to contact me at...                       \n"
                            + "adam.a.nunez@gmail.com                    \n"
                            + "                                          \n"
                            + "Source code is available here:            \n"
                            + "github.com/aanunez/LFSR-Simulator         \n"
                            + "                                          \n"
                            + "This program is free software; you can    \n"
                            + "redistribute it and/or modify it under the\n"
                            + "terms of the GNU General Public License as\n"
                            + "published by the Free Software Foundation;\n"
                            + "either version 2 of the License, or (at   \n"
                            + "your option) any later version.           \n"
                            + "                                          \n"
                            + "This program is distributed in the hope   \n"
                            + "that it will be useful, but WITHOUT ANY   \n"
                            + "WARRANTY; without even the implied        \n"
                            + "warranty of MERCHANTABILITY or FITNESS FOR\n"
                            + "A PARTICULAR PURPOSE. See the GNU General \n"
                            + "Public License for more details           \n");
    }
    
    private void generateButton(){
        long generationTime = System.currentTimeMillis();
        UpdateLFSRSettings();
        UpdateTapsList();
        UpdateSeqLength();
        UpdateSeqList();
        UpdateCode();
        GenerationTimeTextArea.setText(Long.toString(System.currentTimeMillis()-generationTime)+" ms");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GateRadioGroup = new javax.swing.ButtonGroup();
        FeedbackRadioGroup = new javax.swing.ButtonGroup();
        TapSelectionGroup = new javax.swing.ButtonGroup();
        SeqLengthButtonGroup = new javax.swing.ButtonGroup();
        BitDirection = new javax.swing.ButtonGroup();
        TapsTextArea = new javax.swing.JScrollPane();
        TapsList = new javax.swing.JList();
        GenerateButton = new javax.swing.JButton();
        LFSRSettingsPanel = new javax.swing.JPanel();
        XorRadio = new javax.swing.JRadioButton();
        XnorRadio = new javax.swing.JRadioButton();
        ManyToOneRadio = new javax.swing.JRadioButton();
        OneToManyRadio = new javax.swing.JRadioButton();
        BitsTextArea = new javax.swing.JTextField();
        NumberOfBitsLabel = new java.awt.Label();
        GateTypeLabel = new java.awt.Label();
        FeedbackTypeLabel = new java.awt.Label();
        TapSelectionLabel = new java.awt.Label();
        SeqLengthLabel = new java.awt.Label();
        n1RadioButton = new javax.swing.JRadioButton();
        nRadioButton = new javax.swing.JRadioButton();
        SequenceLength = new java.awt.Label();
        SeqLengthTextArea = new java.awt.Label();
        ManualRadio = new javax.swing.JRadioButton();
        AutoRadio = new javax.swing.JRadioButton();
        SequenceLength1 = new java.awt.Label();
        GenerationTimeTextArea = new java.awt.Label();
        LFSRSettings = new java.awt.Label();
        Taps = new java.awt.Label();
        DisplaySettingsPanel = new javax.swing.JPanel();
        UpperBoundLabel = new java.awt.Label();
        LowerBoundLabel = new java.awt.Label();
        UpperTextArea = new javax.swing.JTextField();
        LowerTextArea = new javax.swing.JTextField();
        FullSeqCheckBox = new javax.swing.JCheckBox();
        BitDirectionLabel = new java.awt.Label();
        BitForward = new javax.swing.JRadioButton();
        BitBackward = new javax.swing.JRadioButton();
        DisplaySettingsLabel = new java.awt.Label();
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
        MyHDLPanel = new javax.swing.JPanel();
        MyHDLScroll = new javax.swing.JScrollPane();
        MyHDLTextArea = new javax.swing.JTextArea();
        AboutPanel = new javax.swing.JPanel();
        AboutScroll = new javax.swing.JScrollPane();
        AboutTextArea = new javax.swing.JTextArea();
        CodeGenerationSettingsPanel = new javax.swing.JPanel();
        IncludeResetCheckBox = new javax.swing.JCheckBox();
        IncludeFlagCheckBox = new javax.swing.JCheckBox();
        FlagOnCycleTextArea = new javax.swing.JTextField();
        FlagOnCycleLabel = new java.awt.Label();
        CodeGenerationSettingsLabel = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LFSR Simulator");
        setResizable(false);

        TapsTextArea.setViewportView(TapsList);

        GenerateButton.setText("Generate");
        GenerateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateButtonActionPerformed(evt);
            }
        });

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
        BitsTextArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BitsTextAreaActionPerformed(evt);
            }
        });

        NumberOfBitsLabel.setText("Number of Bits:");

        GateTypeLabel.setText("Gate Type");

        FeedbackTypeLabel.setName(""); // NOI18N
        FeedbackTypeLabel.setText("Feedback Type");

        TapSelectionLabel.setText("Tap Selection");

        SeqLengthLabel.setText("Seq Length");

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

        SequenceLength1.setText("Generation Time:");

        GenerationTimeTextArea.setText("0");

        javax.swing.GroupLayout LFSRSettingsPanelLayout = new javax.swing.GroupLayout(LFSRSettingsPanel);
        LFSRSettingsPanel.setLayout(LFSRSettingsPanelLayout);
        LFSRSettingsPanelLayout.setHorizontalGroup(
            LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                                .addComponent(NumberOfBitsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BitsTextArea))
                            .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TapSelectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(GateTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(SeqLengthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FeedbackTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SequenceLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SequenceLength1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GenerationTimeTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SeqLengthTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        LFSRSettingsPanelLayout.setVerticalGroup(
            LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GateTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FeedbackTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addComponent(XorRadio)
                        .addGap(0, 0, 0)
                        .addComponent(XnorRadio))
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addComponent(ManyToOneRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OneToManyRadio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TapSelectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SeqLengthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nRadioButton)
                    .addGroup(LFSRSettingsPanelLayout.createSequentialGroup()
                        .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AutoRadio)
                            .addComponent(n1RadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ManualRadio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NumberOfBitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BitsTextArea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SequenceLength, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SeqLengthTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LFSRSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SequenceLength1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GenerationTimeTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        LFSRSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LFSRSettings.setFont(new java.awt.Font("Dialog", 1, 12));
        LFSRSettings.setName(""); // NOI18N
        LFSRSettings.setText("LFSR Settings");

        Taps.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Taps.setFont(new java.awt.Font("Dialog", 1, 12));
        Taps.setText("Taps");

        DisplaySettingsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        UpperBoundLabel.setText("Upper Bound:");

        LowerBoundLabel.setText("Lower Bound:");

        UpperTextArea.setText("255");

        LowerTextArea.setText("0");

        FullSeqCheckBox.setText("Display Full Sequence");
        FullSeqCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullSeqCheckBoxActionPerformed(evt);
            }
        });

        BitDirectionLabel.setText("Bit Direction:");

        BitDirection.add(BitForward);
        BitForward.setSelected(true);
        BitForward.setText("Forward");

        BitDirection.add(BitBackward);
        BitBackward.setText("Backward");

        javax.swing.GroupLayout DisplaySettingsPanelLayout = new javax.swing.GroupLayout(DisplaySettingsPanel);
        DisplaySettingsPanel.setLayout(DisplaySettingsPanelLayout);
        DisplaySettingsPanelLayout.setHorizontalGroup(
            DisplaySettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DisplaySettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DisplaySettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DisplaySettingsPanelLayout.createSequentialGroup()
                        .addGroup(DisplaySettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UpperBoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LowerBoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DisplaySettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UpperTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(LowerTextArea))
                        .addContainerGap())
                    .addGroup(DisplaySettingsPanelLayout.createSequentialGroup()
                        .addGroup(DisplaySettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FullSeqCheckBox)
                            .addGroup(DisplaySettingsPanelLayout.createSequentialGroup()
                                .addComponent(BitDirectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(DisplaySettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BitForward)
                                    .addComponent(BitBackward))))
                        .addGap(10, 10, 10))))
        );
        DisplaySettingsPanelLayout.setVerticalGroup(
            DisplaySettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DisplaySettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DisplaySettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DisplaySettingsPanelLayout.createSequentialGroup()
                        .addComponent(UpperTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LowerTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DisplaySettingsPanelLayout.createSequentialGroup()
                        .addComponent(UpperBoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LowerBoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FullSeqCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DisplaySettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DisplaySettingsPanelLayout.createSequentialGroup()
                        .addComponent(BitForward)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BitBackward))
                    .addComponent(BitDirectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FullSeqCheckBox.getAccessibleContext().setAccessibleName("");

        DisplaySettingsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DisplaySettingsLabel.setFont(new java.awt.Font("Dialog", 1, 12));
        DisplaySettingsLabel.setName(""); // NOI18N
        DisplaySettingsLabel.setText("Display Settings");

        SequenceScroll.setBorder(null);

        SeqTextArea.setColumns(20);
        SeqTextArea.setRows(5);
        SequenceScroll.setViewportView(SeqTextArea);

        javax.swing.GroupLayout SequencePanelLayout = new javax.swing.GroupLayout(SequencePanel);
        SequencePanel.setLayout(SequencePanelLayout);
        SequencePanelLayout.setHorizontalGroup(
            SequencePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SequenceScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );
        SequencePanelLayout.setVerticalGroup(
            SequencePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SequenceScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
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
            .addComponent(VerilogScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );
        VerilogPanelLayout.setVerticalGroup(
            VerilogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VerilogScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
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
            .addComponent(VHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );
        VHDLPanelLayout.setVerticalGroup(
            VHDLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
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
            .addComponent(AHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );
        AHDLPanelLayout.setVerticalGroup(
            AHDLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );

        TabbedPane.addTab("AHDL", AHDLPanel);

        MyHDLScroll.setBorder(null);

        MyHDLTextArea.setColumns(20);
        MyHDLTextArea.setRows(5);
        MyHDLScroll.setViewportView(MyHDLTextArea);

        javax.swing.GroupLayout MyHDLPanelLayout = new javax.swing.GroupLayout(MyHDLPanel);
        MyHDLPanel.setLayout(MyHDLPanelLayout);
        MyHDLPanelLayout.setHorizontalGroup(
            MyHDLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MyHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );
        MyHDLPanelLayout.setVerticalGroup(
            MyHDLPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MyHDLScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );

        TabbedPane.addTab("MyHDL", MyHDLPanel);

        AboutScroll.setBorder(null);

        AboutTextArea.setColumns(20);
        AboutTextArea.setRows(5);
        AboutScroll.setViewportView(AboutTextArea);

        javax.swing.GroupLayout AboutPanelLayout = new javax.swing.GroupLayout(AboutPanel);
        AboutPanel.setLayout(AboutPanelLayout);
        AboutPanelLayout.setHorizontalGroup(
            AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AboutScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );
        AboutPanelLayout.setVerticalGroup(
            AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AboutScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );

        TabbedPane.addTab("About", AboutPanel);

        CodeGenerationSettingsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        IncludeResetCheckBox.setText("Include Reset Code");
        IncludeResetCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IncludeResetCheckBoxActionPerformed(evt);
            }
        });

        IncludeFlagCheckBox.setText("Include Flag Code");
        IncludeFlagCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IncludeFlagCheckBoxActionPerformed(evt);
            }
        });

        FlagOnCycleTextArea.setText("25000");

        FlagOnCycleLabel.setText("Flag on Cycle #:");

        javax.swing.GroupLayout CodeGenerationSettingsPanelLayout = new javax.swing.GroupLayout(CodeGenerationSettingsPanel);
        CodeGenerationSettingsPanel.setLayout(CodeGenerationSettingsPanelLayout);
        CodeGenerationSettingsPanelLayout.setHorizontalGroup(
            CodeGenerationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CodeGenerationSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CodeGenerationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IncludeResetCheckBox)
                    .addComponent(IncludeFlagCheckBox)
                    .addGroup(CodeGenerationSettingsPanelLayout.createSequentialGroup()
                        .addComponent(FlagOnCycleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FlagOnCycleTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CodeGenerationSettingsPanelLayout.setVerticalGroup(
            CodeGenerationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CodeGenerationSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IncludeResetCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IncludeFlagCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CodeGenerationSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FlagOnCycleTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FlagOnCycleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CodeGenerationSettingsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CodeGenerationSettingsLabel.setFont(new java.awt.Font("Dialog", 1, 12));
        CodeGenerationSettingsLabel.setName(""); // NOI18N
        CodeGenerationSettingsLabel.setText("Code Generation Settings");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GenerateButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(LFSRSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LFSRSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CodeGenerationSettingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisplaySettingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CodeGenerationSettingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DisplaySettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Taps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TapsTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LFSRSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(LFSRSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GenerateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Taps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(TapsTextArea))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CodeGenerationSettingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(CodeGenerationSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DisplaySettingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(DisplaySettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TabbedPane))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LFSRSettings.getAccessibleContext().setAccessibleDescription("");
        TabbedPane.getAccessibleContext().setAccessibleName("SequenceTextArea");

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    //**********************************************
    //Action Listeners
    //**********************************************
    
    private void IncludeResetCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IncludeResetCheckBoxActionPerformed
        UpdateCode();
    }//GEN-LAST:event_IncludeResetCheckBoxActionPerformed

    private void IncludeFlagCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IncludeFlagCheckBoxActionPerformed
        UpdateCode();
    }//GEN-LAST:event_IncludeFlagCheckBoxActionPerformed

    private void GenerateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateButtonActionPerformed
        generateButton();
    }//GEN-LAST:event_GenerateButtonActionPerformed

    private void FullSeqCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullSeqCheckBoxActionPerformed
        LowerTextArea.setEditable(FullSeqCheckBox.isSelected() == false);
        UpperTextArea.setEditable(FullSeqCheckBox.isSelected() == false);
    }//GEN-LAST:event_FullSeqCheckBoxActionPerformed

    private void BitsTextAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BitsTextAreaActionPerformed
        try{
            lfsr.setNumberOfBits(Integer.parseInt(BitsTextArea.getText()));
        }
        catch(NumberFormatException f){
            lfsr.setNumberOfBits(1);
        }
        UpdateTapsList();
        UpdateSeqLength();
    }//GEN-LAST:event_BitsTextAreaActionPerformed

    //**********************************************
    //Update Functions
    //**********************************************
    
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
        int start, stop;
        if(FullSeqCheckBox.isSelected()){
            start = 0;
            stop = lfsr.getSequenceLength();
        } else{
            start = getStart();
            stop = getStop();
        }
        SeqTextArea.append(lfsr.getBitSequence(start, stop, BitForward.isSelected()));
        if(lfsr.getTimeOutFlag())
            SeqTextArea.append("***Timeout Warning Occured***");
        SeqTextArea.setCaretPosition(0);
        lfsr.resetLFSR();
    }

    private void UpdateLFSRSettings(){
        if(XorRadio.isSelected())
            lfsr.setGateType(GateType.XOR);
        else
            lfsr.setGateType(GateType.XNOR);
        if(ManyToOneRadio.isSelected())
            lfsr.setFeedbackType(FeedbackType.MANY2ONE);
        else
            lfsr.setFeedbackType(FeedbackType.ONE2MANY);
        if(nRadioButton.isSelected())
            lfsr.setExtended(true);
        else
            lfsr.setExtended(false);
        try{lfsr.setNumberOfBits(Integer.parseInt(BitsTextArea.getText()));}
        catch(NumberFormatException e){ lfsr.setNumberOfBits(1);}
        lfsr.resetLFSR();
    }

    private void UpdateCode(){
        boolean IncludeReset = false;
        boolean IncludeFlag = false;
        if(IncludeResetCheckBox.isSelected())
            IncludeReset = true;
        if(IncludeFlagCheckBox.isSelected())
            IncludeFlag = true;
        VerilogTextArea.setText(lfsr.toVerilog(IncludeReset,IncludeFlag,Integer.parseInt(FlagOnCycleTextArea.getText())));
        VHDLTextArea.setText(lfsr.toVHDL());
        AHDLTextArea.setText(lfsr.toAHDL());
        MyHDLTextArea.setText(lfsr.toMyHDL());
        VerilogTextArea.setCaretPosition(0);
        VHDLTextArea.setCaretPosition(0);
        AHDLTextArea.setCaretPosition(0);
        MyHDLTextArea.setCaretPosition(0);
    }

    private void UpdateSeqLength(){
        lfsr.getSequenceLength();
        if(lfsr.getTimeOutFlag())
            SeqLengthTextArea.setText("Time Out");
        else
            SeqLengthTextArea.setText(Integer.toString(lfsr.getSequenceLength()));
    }
    
    //**********************************************
    //Get Functions
    //**********************************************
    
    private int getStop(){
        int stop;
        try{
            if(Integer.parseInt(UpperTextArea.getText())>lfsr.getSequenceLength())
                stop = lfsr.getSequenceLength();
            else
                stop = Integer.parseInt(UpperTextArea.getText());
        }
        catch(NumberFormatException e){
            stop = lfsr.getSequenceLength();
        }
        return stop;
    }

    private int getStart(){
        int start;
        try{
            if(Integer.parseInt(LowerTextArea.getText())<1)
                start = 0;
            else
                start = Integer.parseInt(LowerTextArea.getText());
        }
        catch(NumberFormatException e){start = 0;}
        return start;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AHDLPanel;
    private javax.swing.JScrollPane AHDLScroll;
    private javax.swing.JTextArea AHDLTextArea;
    private javax.swing.JPanel AboutPanel;
    private javax.swing.JScrollPane AboutScroll;
    private javax.swing.JTextArea AboutTextArea;
    private javax.swing.JRadioButton AutoRadio;
    private javax.swing.JRadioButton BitBackward;
    private javax.swing.ButtonGroup BitDirection;
    private java.awt.Label BitDirectionLabel;
    private javax.swing.JRadioButton BitForward;
    private javax.swing.JTextField BitsTextArea;
    private java.awt.Label CodeGenerationSettingsLabel;
    private javax.swing.JPanel CodeGenerationSettingsPanel;
    private java.awt.Label DisplaySettingsLabel;
    private javax.swing.JPanel DisplaySettingsPanel;
    private javax.swing.ButtonGroup FeedbackRadioGroup;
    private java.awt.Label FeedbackTypeLabel;
    private java.awt.Label FlagOnCycleLabel;
    private javax.swing.JTextField FlagOnCycleTextArea;
    private javax.swing.JCheckBox FullSeqCheckBox;
    private javax.swing.ButtonGroup GateRadioGroup;
    private java.awt.Label GateTypeLabel;
    private javax.swing.JButton GenerateButton;
    private java.awt.Label GenerationTimeTextArea;
    private javax.swing.JCheckBox IncludeFlagCheckBox;
    private javax.swing.JCheckBox IncludeResetCheckBox;
    private java.awt.Label LFSRSettings;
    private javax.swing.JPanel LFSRSettingsPanel;
    private java.awt.Label LowerBoundLabel;
    private javax.swing.JTextField LowerTextArea;
    private javax.swing.JRadioButton ManualRadio;
    private javax.swing.JRadioButton ManyToOneRadio;
    private javax.swing.JPanel MyHDLPanel;
    private javax.swing.JScrollPane MyHDLScroll;
    private javax.swing.JTextArea MyHDLTextArea;
    private java.awt.Label NumberOfBitsLabel;
    private javax.swing.JRadioButton OneToManyRadio;
    private javax.swing.ButtonGroup SeqLengthButtonGroup;
    private java.awt.Label SeqLengthLabel;
    private java.awt.Label SeqLengthTextArea;
    private javax.swing.JTextArea SeqTextArea;
    private java.awt.Label SequenceLength;
    private java.awt.Label SequenceLength1;
    private javax.swing.JPanel SequencePanel;
    private javax.swing.JScrollPane SequenceScroll;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.ButtonGroup TapSelectionGroup;
    private java.awt.Label TapSelectionLabel;
    private java.awt.Label Taps;
    private javax.swing.JList TapsList;
    private javax.swing.JScrollPane TapsTextArea;
    private java.awt.Label UpperBoundLabel;
    private javax.swing.JTextField UpperTextArea;
    private javax.swing.JPanel VHDLPanel;
    private javax.swing.JScrollPane VHDLScroll;
    private javax.swing.JTextArea VHDLTextArea;
    private javax.swing.JPanel VerilogPanel;
    private javax.swing.JScrollPane VerilogScroll;
    private javax.swing.JTextArea VerilogTextArea;
    private javax.swing.JRadioButton XnorRadio;
    private javax.swing.JRadioButton XorRadio;
    private javax.swing.JRadioButton n1RadioButton;
    private javax.swing.JRadioButton nRadioButton;
    // End of variables declaration//GEN-END:variables

}