/*
 * The MIT License
 *
 * Copyright 2015 Manuel Schmid.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package client.gui;

import client.gui.tabs.ChatTab;
import client.gui.tabs.ChatType;
import client.gui.tabs.TabController;
import client.gui.userlist.UserListController;
import networking.packets.*;
import networking.general.Packet;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import javax.swing.DefaultListModel;
import static javax.swing.JList.*;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

/**
 * This is the gui class for the client Run this file to initialize gui
 *
 * @author Manuel Schmid
 */
public final class ClientGui extends javax.swing.JFrame {

    // Socket
    protected Socket clientSocket = null;

    // Streams
    protected ObjectOutputStream outStream = null;
    protected ObjectInputStream inStream = null;
    protected BufferedReader inputLine = null;

    protected DialogHelper dialogHelper = null;

    protected boolean isConnected = false;
    protected boolean hasWrittenMessage = false;

    protected String clientName, host;
    protected int port;

    protected TabController tabController;
    protected UserListController userListController;

    private enum connectButtonText {

        Connect,
        Disconnect
    }

    /**
     * Initializes the gui elements
     */
    public ClientGui() {
        initComponents();

        this.tabController = new TabController(this.tabbedPane);
        this.userListController = new UserListController(this.lbUsers, tabController);

        // Set fields to default value
        tbPort.setText("8000");
        tbServer.setText("localhost");

        // Initialize a new DialogHelper
        dialogHelper = new DialogHelper(this);

        // Center JFrame
        setLocationRelativeTo(null);

        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new ShutdownHandle());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        connectionPanel = new javax.swing.JPanel();
        bConnect = new javax.swing.JButton();
        tbPort = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tbServer = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tbNickname = new javax.swing.JTextField();
        sendPanel = new javax.swing.JPanel();
        bSendMessage = new javax.swing.JButton();
        tbMessage = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbUsers = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat-Client");
        setMinimumSize(new java.awt.Dimension(700, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bConnect.setText("Connect");
        bConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConnectActionPerformed(evt);
            }
        });

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, connectionPanel, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), tbPort, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        tbPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPortKeyPressed(evt);
            }
        });

        jLabel1.setText("Port");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, connectionPanel, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jLabel1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jLabel2.setText("Server");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, connectionPanel, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jLabel2, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        tbServer.setMinimumSize(new java.awt.Dimension(50, 20));
        tbServer.setName(""); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, connectionPanel, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), tbServer, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        tbServer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbServerKeyPressed(evt);
            }
        });

        jLabel3.setText("Nickname");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, connectionPanel, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jLabel3, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, connectionPanel, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), tbNickname, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        tbNickname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNicknameKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout connectionPanelLayout = new javax.swing.GroupLayout(connectionPanel);
        connectionPanel.setLayout(connectionPanelLayout);
        connectionPanelLayout.setHorizontalGroup(
            connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, connectionPanelLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbServer, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbPort, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        connectionPanelLayout.setVerticalGroup(
            connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectionPanelLayout.createSequentialGroup()
                .addGroup(connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tbNickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(tbPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bConnect))
                .addGap(1, 1, 1))
        );

        sendPanel.setEnabled(false);

        bSendMessage.setText("Send");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sendPanel, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), bSendMessage, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        bSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSendMessageActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sendPanel, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), tbMessage, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        tbMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMessageKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout sendPanelLayout = new javax.swing.GroupLayout(sendPanel);
        sendPanel.setLayout(sendPanelLayout);
        sendPanelLayout.setHorizontalGroup(
            sendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sendPanelLayout.createSequentialGroup()
                .addComponent(tbMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        sendPanelLayout.setVerticalGroup(
            sendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tbMessage))
        );

        lbUsers.setModel(new DefaultListModel());
        lbUsers.setLayoutOrientation(VERTICAL);
        lbUsers.setSelectionMode(SINGLE_SELECTION);
        lbUsers.setVisibleRowCount(-1);
        lbUsers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lbUsersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lbUsers);

        jLabel4.setText("Userlist");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
        );

        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(connectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tabbedPane))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(connectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tabbedPane))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.closeHandle();
    }//GEN-LAST:event_formWindowClosing

    private void tbMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMessageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.sendMessageBox();
        }
    }//GEN-LAST:event_tbMessageKeyPressed

    private void tbNicknameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNicknameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.connect();
        }
    }//GEN-LAST:event_tbNicknameKeyPressed

    private void tbServerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbServerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.connect();
        }
    }//GEN-LAST:event_tbServerKeyPressed

    private void tbPortKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPortKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.connect();
        }
    }//GEN-LAST:event_tbPortKeyPressed

    private void bConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConnectActionPerformed
        if (this.bConnect.getText().equals(connectButtonText.Connect.toString())) {
            this.connect();
        } else {
            this.disconnect();
        }
    }//GEN-LAST:event_bConnectActionPerformed

    private void bSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSendMessageActionPerformed
        this.sendMessageBox();
    }//GEN-LAST:event_bSendMessageActionPerformed

    // TODO Add userlist programmatically
    private void lbUsersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lbUsersValueChanged
        List<String> selectedElements = this.lbUsers.getSelectedValuesList();
        if (!evt.getValueIsAdjusting() && selectedElements.size() > 0) {
            boolean addTab = true;
            int index = -1;

            for (String selectedElement : selectedElements) {

                index = this.tabController.getTabIndexByTitle(selectedElement);
                // Check if client has selected himself or chat already exists
                if (selectedElement.equals(this.clientName) || index != -1) {
                    addTab = false;
                    break;
                }
            }

            if (!addTab) {
                if (index != -1) {
                    tabController.setFocusAt(index);
                } else {
                    dialogHelper.showInfoDialog("Info", "This is you. You can't send messages to yourself!");
                }
            } else {

                tabController.addTab(selectedElements, ChatType.Private);
                tabController.setFocusAt(selectedElements.get(0));
            }

            this.tbMessage.requestFocus();
        }
    }//GEN-LAST:event_lbUsersValueChanged

    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        this.tabController.setActiveChatTab(tabbedPane.getSelectedIndex());
    }//GEN-LAST:event_tabbedPaneStateChanged

    /**
     * Connects the client to the server
     *
     * @return
     */
    protected boolean connect() {

        if (checkConnData()) {

            // Initiating variables
            this.host = this.tbServer.getText();
            this.port = Integer.parseInt(this.tbPort.getText());
            this.clientName = this.tbNickname.getText().trim();

            // Open a socket on a given host and port. Open input and output streams.
            try {
                // Set up socket and streams
                clientSocket = new Socket(host, port);
                inputLine = new BufferedReader(new InputStreamReader(System.in));
                outStream = new ObjectOutputStream(clientSocket.getOutputStream());
                inStream = new ObjectInputStream(clientSocket.getInputStream());

                // Create a thread to read from the server
                new Thread(new ClientGuiThread(this)).start();

                // Send clientName
                if (this.send(new ConnectPacket(this.clientName))) {

                    this.switchGui(true);
                    this.isConnected = true;

                    return true;
                }

                this.dialogHelper.showWarningDialog("Warning", "Could not send data to host \"" + host + "\" on Port " + port);
                return false;
            } catch (UnknownHostException ex) {
                this.dialogHelper.showWarningDialog("Warning", "Don't know about host " + host);
            } catch (IOException ex) {
                this.dialogHelper.showWarningDialog("Connection failed", "Could not connect to host \"" + host + "\" on Port " + port);
            }

            logging.general.Counters.exception();
            return false;
        }
        return false;
    }

    /**
     * Disconnects the client to the server
     */
    protected void disconnect() {
        this.send(new DisconnectPacket());
    }

    /**
     * Checks if given data is valid
     *
     * @return
     */
    protected boolean checkConnData() {
        String nickname = this.tbNickname.getText();
        String server = this.tbServer.getText();
        String portText = this.tbPort.getText();
        int connectionPort = Integer.parseInt(portText);

        try {
            if (nickname.trim().equals("")) {
                this.dialogHelper.showInfoDialog("Info", "Please set a nickname");
            } else if (server.trim().equals("")) {
                this.dialogHelper.showInfoDialog("Info", "Please set a server to connect to");
            } else if (portText.trim().equals("")) {
                this.dialogHelper.showInfoDialog("Info", "Please set a port to which to connect to on the server");
            } else if (connectionPort < 1 || connectionPort > 65555) {
                this.dialogHelper.showInfoDialog("Info", "The given port has to be an integer in the range from 1 to 65535");
            } else {
                return true;
            }
            return false;
        } catch (Exception ex) {
            this.dialogHelper.showInfoDialog("ERROR", "An error occured, message: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Sends a packet through the outStream to the server
     *
     * @param packet packet to send
     * @return
     */
    protected boolean send(Packet packet) {

        try {
            this.outStream.writeObject(packet);
            this.tbMessage.setText("");
            this.hasWrittenMessage = true;
            return true;
        } catch (IOException ex) {
            // TODO exception handling
        } finally {
            logging.general.Counters.exception();
        }
        return false;
    }

    /**
     * Tries to send content of message box
     */
    protected void sendMessageBox() {
        String message = this.tbMessage.getText().trim();
        //Check if line is empty
        if (!message.equals("")) {
            ChatTab currentChatTab = this.tabController.getCurrentChatTab();
            if (currentChatTab.getChatType() == ChatType.Private) {
                // TODO Implement group messages
                for (String person : currentChatTab.getPersons()) {
                    this.send(new PrivateMessagePacket(message, this.clientName, person));
                }
            } else if (currentChatTab.getChatType() == ChatType.Group) {
                this.send(new GroupMessagePacket(message, this.clientName));
            }
        }
        this.tbMessage.requestFocus();
    }

    /**
     * Closes the connection to the server
     */
    protected synchronized void closeConnection() {
        try {
            // Close streams and socket
            this.outStream.close();
            this.inStream.close();
            this.clientSocket.close();

            this.switchGui(false);
        } catch (IOException ex) {
            System.err.println("IOException:  " + ex);
        } finally {
            logging.general.Counters.exception();
        }
    }

    /**
     * Switches the gui between two states: 1. Only connect bar active 2. chat
     * area and message sending active
     *
     * @param isEnabled
     */
    protected void switchGui(boolean isEnabled) {

        if (isEnabled) {
            tabController.init();
            bConnect.setText(connectButtonText.Disconnect.toString());
            tbMessage.requestFocus();
        } else {
            tabController.terminate();
            userListController.clearList();
            bConnect.setText(connectButtonText.Connect.toString());
        }

        sendPanel.setEnabled(isEnabled);
        lbUsers.setEnabled(isEnabled);
        connectionPanel.setEnabled(!isEnabled);

        //this.pack();
    }

    /**
     * Disconnects the user on close
     */
    private void closeHandle() {
        if (this.isConnected) {
            if (this.hasWrittenMessage) {
                this.disconnect();
            }
        }
    }

    /**
     * Handle for disconnect on close
     */
    class ShutdownHandle extends Thread {

        @Override
        public void run() {
            closeHandle();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            logging.general.Counters.exception();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConnect;
    private javax.swing.JButton bSendMessage;
    private javax.swing.JPanel connectionPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lbUsers;
    private javax.swing.JPanel sendPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField tbMessage;
    private javax.swing.JTextField tbNickname;
    private javax.swing.JTextField tbPort;
    private javax.swing.JTextField tbServer;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
