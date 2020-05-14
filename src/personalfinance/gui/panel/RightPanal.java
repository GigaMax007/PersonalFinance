package personalfinance.gui.panel;

import personalfinance.gui.EnableEditDelete;
import personalfinance.gui.MainFrame;
import personalfinance.gui.Refresh;
import personalfinance.gui.table.TableData;
import personalfinance.gui.toolbar.AbstractToolBar;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

abstract public class RightPanal extends AbstractPanel{

    protected TableData td;

    private String title;
    private ImageIcon icon;
    private JPanel[] panels;

    public RightPanal(MainFrame frame, TableData td, String title, ImageIcon icon, JPanel[] panels) {
        super(frame);
        this.td = td;
        this.title = title;
        this.icon = icon;
        this.panels = panels;
    }

    public RightPanal(MainFrame frame, TableData td, String title, ImageIcon icon, AbstractToolBar tb) {
        this(frame, td, title, icon, new JPanel[] {tb});
    }

    public RightPanal(MainFrame frame, TableData td, String title, ImageIcon icon) {
        this(frame, td, title, icon, new JPanel[] {});
    }

    @Override
    public void refresh() {
        super.refresh();
        if (td != null) td.refresh();
        for (JPanel panel:
                panels) {
            if (panel instanceof Refresh) ((Refresh) panel).refresh();
        }
    }

    private void enableEditDelete() {
        for (JPanel panel :
                panels) {
            if (panel instanceof EnableEditDelete) ((EnableEditDelete) panel).setEnableEditDelete(false);
        }
        frame.getMenu().setEnableEditDelete(false);

        if (td != null) {
            if (td.getSelectedRow() != -1) {
                for (JPanel panel :
                        panels) {
                    if (panel instanceof EnableEditDelete) ((EnableEditDelete) panel).setEnableEditDelete(true);
                }
                frame.getMenu().setEnableEditDelete(true);
            }
        }
    }

    protected void init() {
        enableEditDelete();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel header = new JLabel(Text.get(title));
        header.setFont(Style.FONT_LABEL_HEADER);
        header.setIcon(icon);
        header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(header);

        if (panels.length == 0) add(Box.createVerticalStrut(Style.PADDING_PANAL_EMPTY));

        for (JPanel panel :
                panels) {
            add(panel);
            add(Box.createVerticalStrut(Style.PADDING_PANAL));
        }

        if (td != null) {
            Component table;
            JScrollPane scroll = new JScrollPane(td);
            add(scroll);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            ListSelectionModel selectionModel =td.getSelectionModel();
            selectionModel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    enableEditDelete();
                }
            });
        }
    }
}
