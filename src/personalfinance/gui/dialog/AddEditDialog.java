package personalfinance.gui.dialog;

import personalfinance.exception.ModelException;
import personalfinance.gui.MainFrame;
import personalfinance.model.Common;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

import javax.swing.*;
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
        dispose();
    }

    public boolean isAdd() {
        return c == null;
    }

    abstract protected void init();

    abstract protected void setVelues();

    abstract protected Common getCommonFromForm() throws ModelException;

    private void setDialog() {
        init();
        if(isAdd()) setTitle(Text.get("ADD"));
        else {
            setVelues();
            setTitle(Text.get("EDIT"));
        }
        getContentPane().removeAll(); // Очищение панели
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
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
            }
        }
    }
}
