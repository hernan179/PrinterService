
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class MainFrame extends javax.swing.JFrame {

    private IntegerListModel listModel = null;
    private NumberLister lister = null;

    private void initialize() {
        listModel = new IntegerListModel();
        lstNumbers.setModel(listModel);

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = txtLoopCount.getText().trim();
                int loopCount;
                try {
                    loopCount = Integer.parseInt(text);
                    lister = new NumberLister(loopCount);
                    WaiterDialog waiter = new WaiterDialog(MainFrame.this, lister);
                    lister.execute();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "An integer is required!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        showCount(0);
        setLocationRelativeTo(null);
    }

    private void showCount(int count) {
        lblCount.setText(String.format("Count: %d", count));
    }

    private void whenStopped() {
        if (lister != null) {
            lister.cancel(false);
        }
    }

    private void whenDone() {
        if (lister != null) {
            lister.displayResult();
        }
    }

    private class WaiterDialog extends JDialog {

        private SwingWorker worker = null;
        private PropertyChangeListener workerStateListener = null;

        public WaiterDialog(JFrame parent, SwingWorker worker) {
            super(parent, "Please wait!", true);

            JLabel lblMessage = new JLabel("Loading...");
            JProgressBar prgbrStatus = new JProgressBar();
            prgbrStatus.setIndeterminate(true);

            JButton btnStop = new JButton("Stop");
            btnStop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    whenStopped();
                }
            });

            Box hBox1 = new Box(BoxLayout.LINE_AXIS);
            hBox1.add(lblMessage);
            hBox1.add(Box.createHorizontalGlue());

            Box hBox2 = new Box(BoxLayout.LINE_AXIS);
            hBox2.add(prgbrStatus);
            hBox2.add(Box.createHorizontalStrut(5));
            hBox2.add(btnStop);

            Box vBox = new Box(BoxLayout.PAGE_AXIS);
            vBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            vBox.add(hBox1);
            vBox.add(hBox2);

            getContentPane().add(vBox);
            pack();

            setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(parent);

            workerStateListener = new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    Object value = e.getNewValue();
                    if (value == SwingWorker.StateValue.STARTED) {
                        setVisible(true);
                    } else if (value == SwingWorker.StateValue.DONE) {
                        whenDone();
                        setVisible(false);
                    }
                }
            };
            worker.addPropertyChangeListener(workerStateListener);
        }
    }

    private class NumberLister extends SwingWorker<Void, Void> {

        private int loopCount;
        private List<Integer> integerList = null;

        public NumberLister(int loopCount) {
            this.loopCount = loopCount;
        }

        protected Void doInBackground() throws Exception {
            Random random = new Random();
            integerList = new ArrayList<Integer>();
            int i = 0, count = 0;

            while (!isCancelled()) {
                if (i >= loopCount) {
                    break;
                }
                for (int j = 0; j < 100000; j++) {
                    int value = random.nextInt();
                    if (value >= 0) {
                        integerList.add(value);
                        count++;
                        System.out.printf("Count = %d%n", count);
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ie) {
                }
                i++;
            }
            Collections.sort(integerList);
            return null;
        }

        @Override
        protected void done() {
            System.out.printf("Done; IsEventDispatchThread = %s%n",
                    SwingUtilities.isEventDispatchThread());
        }

        public void displayResult() {
            if (integerList != null) {
                listModel.clearList();
                listModel.addItems(integerList);
                showCount(listModel.getSize());
            }
        }
    }

    private class IntegerListModel extends AbstractListModel {

        private List<Integer> list = null;

        public IntegerListModel() {
            list = new ArrayList<Integer>();
        }

        public void clearList() {
            int lastIndex = list.size() - 1;
            if (lastIndex >= 0) {
                list.clear();
                fireIntervalRemoved(this, 0, lastIndex);
            }
        }

        public void addItems(Collection<Integer> collection) {
            if (collection.size() != 0) {
                int startIndex = list.size();
                list.addAll(collection);
                int endIndex = list.size() - 1;
                fireIntervalAdded(this, startIndex, endIndex);
            }
        }

        public int getSize() {
            return list.size();
        }

        public Object getElementAt(int i) {
            return list.get(i);
        }
    }
    private JButton btnStart;
    private JLabel lblCount;
    private JLabel lblLoopCount;
    private JList lstNumbers;
    private JTextField txtLoopCount;

    /**
     * Initialize all the components in the frame.
     */
    private void initComponents() {
        lstNumbers = new JList();
        lstNumbers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lblLoopCount = new JLabel("Loop Count:");
        txtLoopCount = new JTextField();
        btnStart = new JButton("Start");
        lblCount = new JLabel("Count");

        Box vbox = new Box(BoxLayout.Y_AXIS);
        Box hbox1 = new Box(BoxLayout.X_AXIS);
        Box hbox2 = new Box(BoxLayout.X_AXIS);

        hbox1.add(lblLoopCount);
        hbox1.add(Box.createHorizontalStrut(5));
        hbox1.add(txtLoopCount);
        hbox1.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                txtLoopCount.getPreferredSize().height));

        hbox2.add(lblCount);
        hbox2.add(Box.createHorizontalGlue());
        hbox2.add(btnStart);

        JScrollPane listScroller = new JScrollPane(lstNumbers);
        listScroller.setPreferredSize(new Dimension(200, 220));

        vbox.add(listScroller);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(hbox1);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(hbox2);
        vbox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getContentPane().add(vbox);

        setTitle("SwingWorker Fails");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        initialize();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}