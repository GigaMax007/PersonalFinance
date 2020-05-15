package personalfinance.gui;

import personalfinance.gui.menu.MainMenu;
import personalfinance.gui.panel.CurrencyPanel;
import personalfinance.gui.panel.LeftPanel;
import personalfinance.gui.panel.RightPanel;
import personalfinance.gui.toolbar.MainToolBar;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Refresh {

    private final GridBagConstraints constraints;
    private final MainMenu mm;
    private final LeftPanel leftPanel;
    private RightPanel rightPanel;
    private final MainToolBar tb;

    public MainFrame() {
        super(Text.get("PROGRAMM_NAME"));

//        ConfirmDialog.show(this, "Хрень какая-то!!!", "Подтверждение удаления");
//        new AboutDialog().setVisible(true);
//        ArticleAddEditDialog temp = new ArticleAddEditDialog(this);
//        try {
//            temp.setCommon(new Article("Text"));
//        } catch (ModelException e) {
//            e.printStackTrace();
//        }
//        temp.showDialog();
//        new TransactionAddEditDialog(this).showDialog();
//        new TransferAddEditDialog(this).showDialog();
//        new CurrencyAddEditDialog(this).showDialog();
//        CurrencyAddEditDialog temp = new CurrencyAddEditDialog(this);
//        temp.setCommon(SaveData.getInstance().getBaseCurrency());
//        temp.showDialog();

        setResizable(false);
        setIconImage(Style.ICON_MAIN.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Временно

        mm = new MainMenu(this);
        setJMenuBar(mm);

        setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth =2;

        tb = new MainToolBar();
        add(tb, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH;

//        add(new MainDatePicker().getDatePicker(), constraints);
//        add leftpanel
        leftPanel = new LeftPanel(this);
        add(leftPanel, constraints);

        setRightPanel(new CurrencyPanel(this));

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
        mm.refresh();
        leftPanel.refresh();
        rightPanel.refresh();
        pack();
    }

    public MainMenu getMenu() {
        return mm;
    }

    private void setRightPanel(RightPanel panel) {
        if (rightPanel != null) remove(rightPanel); // стираем правую панель если она не null
        constraints.gridy = 1;
        constraints.gridx = 1;
        rightPanel = panel;
        panel.setBorder(Style.BORDER_PANEL);
        add(rightPanel, constraints);
        pack();
    }
}
