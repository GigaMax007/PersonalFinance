package personalfinance.gui.panel;

import personalfinance.gui.MainFrame;
import personalfinance.model.Currency;
import personalfinance.model.Statistics;
import personalfinance.saveload.SaveData;
import personalfinance.settings.Format;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

import javax.swing.*;
import java.awt.*;

public final class LeftPanel extends AbstractPanel {


    public LeftPanel(MainFrame frame) {
        super(frame);
        init();
    }

    @Override
    protected void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(Style.BORDER_LEFTPANEL);
        JLabel headerBC = new JLabel(Text.get("BALANCE_CURRENCIES"));
        headerBC.setFont(Style.FONT_LABEL_HEADER);
        headerBC.setIcon(Style.ICON_LEFTPANEL_BALANCE_CURRENCIES);
        headerBC.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(headerBC);

        addBalanceCurrency();

        add(Box.createVerticalStrut(Style.PADDING_PANAL_BIG));

        JLabel headerB = new JLabel(Text.get("BALANCE"));
        headerB.setFont(Style.FONT_LABEL_HEADER);
        headerB.setIcon(Style.ICON_LEFTPANEL_BALANCE);
        headerB.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(headerB);

        addBalance();
    }

    private void addBalanceCurrency() {
        for (Currency currency :
                SaveData.getInstance().getCurrencies()) {
            add(Box.createVerticalStrut(Style.PADDING_BALANCE));
            add(new PanelBalanceCurrency(currency, Statistics.getBalanceCurrency(currency)));
        }
    }

    private void addBalance() {
        for (Currency currency :
                SaveData.getInstance().getCurrencies()) {
            add(Box.createVerticalStrut(Style.PADDING_BALANCE));
            add(new PanelBalanceCurrency(currency, Statistics.getBalance(currency)));
        }
    }

    private class PanelBalanceCurrency extends JPanel {

        public PanelBalanceCurrency(Currency currency, double amount) {
            super();
            setLayout(new BorderLayout());
            setBackground(Style.COLOR_LEFTPANEL_BALANCE);
            setBorder(Style.BORDER_PANEL);

            JLabel currencyLabel = new JLabel(currency.getTitle());
            JLabel amountLabel = new JLabel(Format.amount(amount, currency));

            currencyLabel.setFont(Style.FONT_LABEL_LEFTPANEL_CURRENCY);
            amountLabel.setFont(Style.FONT_LABEL_LEFTPANEL_AMOUNT);

            add(currencyLabel, BorderLayout.WEST);
            add(Box.createRigidArea(Style.DIMENSION_PADDING_BALANCE));
            add(amountLabel, BorderLayout.EAST);
        }
    }
}
