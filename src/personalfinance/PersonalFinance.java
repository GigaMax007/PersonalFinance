package personalfinance;

import personalfinance.exception.ModelException;
import personalfinance.model.*;
import personalfinance.saveload.SaveData;
import personalfinance.settings.Settings;
import personalfinance.settings.Text;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class PersonalFinance {

    public static void main(String[] args) throws Exception {
        init();
        SaveData sd = SaveData.getInstance();
//        System.out.println(sd.getCurrencies());
        sd.updateCurrencies();
//        sd.save();
        System.out.println(sd.getCurrencies());


//       testModel();
    }

    private static void init() {
        try {
            Settings.init();
            Settings.save();
            Text.init();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Settings.FONT_ROBOTO_LIGHT)); // Регистрируем наш шрифт
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void testModel() throws ModelException {
        Currency c1 = new Currency("Рубль", "RUB", 1, true, true);
        Currency c2 = new Currency("Доллар", "USD", 72, true, false);
        Currency c3 = new Currency("Евро", "EUR", 80, false, false);
        Currency c4 = new Currency("Гривна", "UAH", 3, false, false);
        Currency c5 = new Currency("Азербайджанский манат", "AZN", 40, false, false);

        Account a1 = new Account("Кошелёк", c1, 15340);
        Account a2 = new Account("Карта Visa", c1, 0);
        Account a3 = new Account("Депозит в банке (RUB)", c1, 150_000);
        Account a4 = new Account("Депозит в банке (USD)", c2, 10);

        Article article1 = new Article("Проезд на транспорте");
        Article article2 = new Article("Фриланс");
        Article article3 = new Article("Зарплата");
        Article article4 = new Article("Питание");
        Article article5 = new Article("Проценты по депозиту");
        Article article6 = new Article("ЖКХ");

        ArrayList<Currency> currencies = new ArrayList<>();
        currencies.add(c1);
        currencies.add(c2);
        currencies.add(c3);
        currencies.add(c4);
        currencies.add(c5);

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(a1);
        accounts.add(a2);
        accounts.add(a3);
        accounts.add(a4);

        ArrayList<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);
        articles.add(article5);
        articles.add(article6);

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(a2, article3, 50000, "ООО ТАФ - за декабрь"));
        transactions.add(new Transaction(a2, article2, 500, "Консультация по телефону"));
        transactions.add(new Transaction(a2, article4, -1586, "Майские праздники"));
        transactions.add(new Transaction(a1, article6, -6370, "Квартира за Февраль"));
        transactions.add(new Transaction(a1, article1, -660, "Проезд за Март"));
        transactions.add(new Transaction(a3, article5, 132090));
        transactions.add(new Transaction(a1, article3, 12000, new Date((new Date()).getTime() - (long) 86_400_000 * 30)));
        transactions.add(new Transaction(a3, article5, 186000, new Date((new Date()).getTime() - (long) 86_400_000 * 30)));

        for (int i = 0; i < 50; i++) {
            Article tempArticle;
            Account tempAccount;
            if (Math.random() < 0.5) tempArticle = article1;
            else tempArticle = article4;
            if (Math.random() < 0.5) tempAccount = a1;
            else tempAccount = a2;
            double tempAmount = Math.round(Math.random() * -1000);
            Date tempDate = new Date((long) (new Date().getTime() - (long) 86_400_000 * 30 * Math.random()));
            transactions.add(new Transaction(tempAccount, tempArticle, tempAmount, tempDate));
            
        }

        ArrayList<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(a2, a1, 25000, 25000));
        transfers.add(new Transfer(a2, a3, 2500, 2500));
        transfers.add(new Transfer(a2, a3, 3400, 3400));
        transfers.add(new Transfer(a3, a4, 72, 1));

        for (Account a :
                accounts) {
            a.setAmountFromTransactionsAndTransfers(transactions, transfers);
        }

        SaveData sd = SaveData.getInstance();
        sd.setArticles(articles);
        sd.setAccounts(accounts);
        sd.setCurrencies(currencies);
        sd.setTransactions(transactions);
        sd.setTransfers(transfers);
        sd.save();
        sd.load();
        System.out.println(sd);





    }

}
