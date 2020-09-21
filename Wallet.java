public class Wallet
{
    private String wid;
    private int balance;

    public Wallet(String id)
    {
        this.wid = id;
        this.balance = 0;
    }

    public int pay(int p)
    {
        if(p <= this.balance)
        {
            this.balance -= p;
            return p;
        }
        else
        {
            return 0;
        }
    }

    public int income(int c)
    {
        if(c < 0)
        {
            return 0;
        }
        else
        {
            this.balance += c;
            return c;
        }
    }

    public String getWid()
    {
        return this.wid;
    }

    public int getBalance()
    {
        return this.balance;
    }

    public String toString()
    {
        return this.wid+":"+this.balance+" Coin";
    }
}
