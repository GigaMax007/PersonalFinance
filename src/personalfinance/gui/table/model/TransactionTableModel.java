package personalfinance.gui.table.model;

import personalfinance.model.Transaction;
import personalfinance.saveload.SaveData;
import personalfinance.settings.Format;

import java.util.ArrayList;
import java.util.Arrays;

public class TransactionTableModel extends MainTableModel {

    private static final int DATE = 0;
    private static final int ACCOUNT = 1;
    private static final int ARTICLE = 2;
    private static final int AMOUNT = 3;
    private static final int NOTICE = 4;

    private int count = -1;

    public TransactionTableModel(String[] columns) {
        super(SaveData.getInstance().getFilterTransactions());
        this.columns = new ArrayList<>(Arrays.asList(columns));
    }

    public TransactionTableModel(String[] columns, int count) {
        super(SaveData.getInstance().getTransactionsOnCount(count));
        this.columns = new ArrayList<>(Arrays.asList(columns));
        this.count = count;
    }

    @Override
    protected void updateData() {
        if (count == -1) data = SaveData.getInstance().getFilterTransactions();
        else data = SaveData.getInstance().getTransactionsOnCount(count);
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (data.isEmpty()) return null;
        Transaction transaction = (Transaction) data.get(row);
        switch (column) {
            case DATE:
                return Format.date(transaction.getDate());
            case ACCOUNT:
                return transaction.getAccount().getTitle();
            case ARTICLE:
                return transaction.getArticle().getTitle();
            case AMOUNT:
                return transaction.getAmount();
            case NOTICE:
                return transaction.getNotice();
        }
        return null;
    }
}