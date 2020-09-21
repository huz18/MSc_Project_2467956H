public class Transaction
{
    private Wallet walletSender;
    private Wallet walletaccept;
    private int transAmount;

    public Transaction(Wallet sender,Wallet accept,int ta)
    {
        this.walletSender = sender;
        this.walletaccept = accept;
        this.transAmount = sender.pay(ta);
        accept.income(this.transAmount);
    }
    public Wallet getWalletSender()
    {
        return this.walletSender;
    }
    public Wallet getWalletaccept()
    {
        return this.walletaccept;
    }
    public int getTransAmount()
    {
        return this.transAmount;
    }
    public String toString()
    {
        return this.walletSender.getWid()+" pay "+this.walletaccept.getWid()+" "+this.transAmount + " Coin";
    }
}
