package personalfinance.gui.dialog;

import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import personalfinance.exception.ModelException;
import personalfinance.gui.MainButton;
import personalfinance.gui.MainFrame;
import personalfinance.model.Common;
import personalfinance.settings.HandlerCode;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AddEditDialog extends JDialog {

    protected LinkedHashMap<String, JComponent> components = new LinkedHashMap<>();
    protected LinkedHashMap<String, ImageIcon> icons = new LinkedHashMap<>();
    protected LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    protected Common c;

    public AddEditDialog(MainFrame frame) {
        super(frame, Text.get("ADD"), true);
        setResizable(false); // Запретили редактировать размер окна
    }

    public Common getCommon() {
        return c;
    }

    public void setCommon(Common c) {
        this.c = c;
    }

    public final void showDialog() {
        setDialog();
        setVisible(true);
    }

    public final void closeDialog() {
        setVisible(false);
        this.c = null;
        components.clear();
        icons.clear();
        values.clear();
        dispose(); // очистим оперативную память
    }

    public boolean isAdd() {
        return c == null;
    }

    abstract protected void init();

    abstract protected void setVelues();

    abstract protected Common getCommonFromForm() throws ModelException;

    private void setDialog() {
        init();
        if (isAdd()) setTitle(Text.get("ADD"));
        else {
            setVelues();
            setTitle(Text.get("EDIT"));
        }
        getContentPane().removeAll(); // Очищение панели
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Элементы с вертикальным выравниванием по оси Y
        ((JPanel) getContentPane()).setBorder(Style.BORDER_DIALOG);

        for (Map.Entry<String, JComponent> entry :
                components.entrySet()) {
            String key = entry.getKey();
            JLabel label = new JLabel(Text.get(key));
            label.setIcon(icons.get(key));
            label.setFont(Style.FONT_DIALOG_LABEL);

            JComponent jComponent = entry.getValue();
            if (jComponent instanceof JTextField) {
                jComponent.setPreferredSize(Style.DIMENSION_DIALOG_TEXTFIELD_SIZE);
                if (values.containsKey(key)) ((JTextField) jComponent).setText("" + values.get(key));
            } else if (jComponent instanceof JComboBox) {
                if (values.containsKey(key)) ((JComboBox) jComponent).setSelectedItem("" + values.get(key));
            } else if (jComponent instanceof JDatePickerImpl) {
                if (values.containsKey(key))
                    ((UtilDateModel) ((JDatePickerImpl) jComponent).getModel()).setValue((Date) values.get(key));
            }
            jComponent.setAlignmentX(JComponent.LEFT_ALIGNMENT);
            add(label);
            add(Box.createVerticalStrut(Style.PADDING_DIALOG));
            add(jComponent);
            add(Box.createVerticalStrut(Style.PADDING_DIALOG));

        }
        MainButton ok = new MainButton(Text.get("ADD"), Style.ICON_OK, null, HandlerCode.ADD);
        if (!isAdd()) {
            ok.setActionCommand(HandlerCode.EDIT);
            ok.setText(Text.get("EDIT"));
        }

        MainButton cancel = new MainButton(Text.get("CANCEL"), Style.ICON_CANCEL, null, HandlerCode.CANCEL);

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BorderLayout());
        panelButton.add(ok, BorderLayout.WEST);

        panelButton.add(cancel, BorderLayout.EAST);

        add(panelButton);
        pack();
        setLocationRelativeTo(null);
    }

    protected class CommonComboBox extends JComboBox {

        public CommonComboBox(Object[] objs) {
            super(objs);
            setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    DefaultListCellRenderer renderer = (DefaultListCellRenderer) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    Common c = (Common) value;
                    if (c != null) renderer.setText(c.getValueForComboBox());
                    return renderer;
                }
            });
        }
    }
}
