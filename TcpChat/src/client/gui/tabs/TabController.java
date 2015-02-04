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
package client.gui.tabs;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Manuel Schmid
 */
public final class TabController {

    private final JTabbedPane tabbedPane;
    private boolean isInitialized = false;
    // List which contains all ChatTabs
    private final List<ChatTab> chatTabs = new ArrayList<>();
    // Counter for persistent tabs which can't be closed by user during runtime (such as group chat)
    private final int persistentTabs = 1;

    /**
     * Constructor
     *
     * @param tabPane
     */
    public TabController(JTabbedPane tabPane) {
        this.tabbedPane = tabPane;
    }

    /**
     * Initialize components
     */
    public void init() {
        tabbedPane.setEnabled(true);
        // Remove all old entries
        tabbedPane.removeAll();
        chatTabs.clear();
        // Set up group chat (index 0)
        int tabIndex = addTab("Group Chat", ChatType.Group);
        tabbedPane.setSelectedIndex(tabIndex);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        // tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        isInitialized = true;
    }

    /**
     * Terminates components
     */
    public void terminate() {
        // Set isInitialized false
        isInitialized = false;
        // Disable TabbedPane
        tabbedPane.setEnabled(false);
        // Disable ChatArea at curreltny viewed tab
        getChat(tabbedPane.getSelectedIndex()).setEnabled(false);
    }

    /**
     * Getter for isInitialized
     *
     * @return isInitialized
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Adds a new ChatTab
     *
     * @param title title of the tab
     * @param chatType type of the chat tab
     * @return
     */
    public int addTab(String title, ChatType chatType) {
        // Create new ChatTab
        ChatTab chatTab = new ChatTab(chatType, tabbedPane, title);
        // Add ChatTab to tabbedPane
        tabbedPane.add(title, chatTab.getScrollPane());
        if (chatType.hasCloseElement()) {
            // Initialize new closing element for the tab if set in the type
            tabbedPane.setTabComponentAt(chatTab.getIndex(), new ButtonTabComponent(chatTab));
        }

        this.chatTabs.add(chatTab);
        return chatTab.getIndex();
    }

    /**
     * Removes the tab with the given title
     *
     * @param title title of the tab
     * @return boolean if tab was removed
     */
    public boolean removeTab(String title) {
        for (int i = persistentTabs; i < tabbedPane.getTabCount(); i++) {
            String tabTitle = tabbedPane.getTitleAt(i);
            if (title.equals(tabTitle)) {
                tabbedPane.remove(i);
                this.chatTabs.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the tab with the given index
     *
     * @param index index of the tab
     */
    public void removeTab(int index) {
        tabbedPane.remove(index);
        this.chatTabs.remove(index);

    }

    /**
     * Getter for the ChatTab at the given index
     * @param index index for tab to return
     * @return ChatTab at given index
     */
    public ChatTab getTabAt(int index) {
        ChatTab chatTab = chatTabs.get(index);
        if(chatTab != null){
            return chatTab;
        }
        return null;
    }

    /**
     * Getter for index of tab by title
     *
     * @param title title of the tab
     * @return index of tab or -1 if not found
     */
    public int getTabIndexByTitle(String title) {
        for (int i = persistentTabs; i < tabbedPane.getTabCount(); i++) {
            String tabTitle = tabbedPane.getTitleAt(i);
            if (title.equals(tabTitle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets focus on a specific tab by title
     *
     * @param title title of the tab
     * @return
     */
    public boolean setFocusAt(String title) {
        int index = getTabIndexByTitle(title);
        if (index == -1) {
            return false;
        } else {
            tabbedPane.setSelectedIndex(index);
            return true;
        }
    }

    /**
     * Sets focus on a specific tab by index
     *
     * @param index
     * @return
     */
    public boolean setFocusAt(int index) {
        // Check if tab index is valid
        if (index >= tabbedPane.getTabCount()) {
            // Tab index can not be bigger than countof tabs, because index is max count -1
            return false;
        } else {
            // Select tab at index
            tabbedPane.setSelectedIndex(index);
            return true;
        }
    }

    /**
     * Output a message on the currently selected tab
     *
     * @param message message to show on gui
     */
    public synchronized void outputLineOnGui(String message) {
        if (!message.trim().equals("")) {
            appendTextToChat(message, tabbedPane.getSelectedIndex());
        }
    }

    /**
     * Output a message on the chat text area
     *
     * @param message message to show on gui
     * @param person person of the message
     * @return tab was created
     */
    public synchronized boolean outputLineOnGui(String message, String person) {
        boolean tabCreated = false;
        if (!message.trim().equals("")) {
            int tabIndex = getTabIndexByTitle(person);
            if (tabIndex == -1) {
                tabIndex = addTab(person, ChatType.Private);
                tabCreated = true;
            }
            appendTextToChat(message, tabIndex);
        }
        return tabCreated;
    }

    /**
     * Output a message on a tab with given index
     *
     * @param message message to show on gui
     * @param tabIndex index of tab to show message in
     */
    public synchronized void outputLineOnGui(String message, int tabIndex) {
        if (!message.trim().equals("")) {
            appendTextToChat(message, tabIndex);
        }
    }

    /**
     * Appends a String to the ChatArea at the given index
     *
     * @param message message to append
     * @param index
     */
    public void appendTextToChat(String message, int index) {
        ChatArea chatArea = getChat(index);
        chatArea.append("\n" + message);
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    /**
     * Returns the ChatArea at tabIndex
     *
     * @param index index of tab
     * @return
     */
    private ChatArea getChat(int tabIndex) {
        return (ChatArea) ((JScrollPane) tabbedPane.getComponentAt(tabIndex)).getViewport().getView();
    }
}
