import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BlockChain
{
    private static List<Block> blockchain = new ArrayList<>();
    public static int mineBlock(String prefix)
    {
        Random r = new Random(System.currentTimeMillis());
        int count = 0;
        for(int i = 0; i < 100; ++i)
        {
            String data = Long.toString(System.currentTimeMillis()) + Integer.toString(r.nextInt());
            MessageDigest dg = null;
            byte[] bytes = null;
            try {
                dg = MessageDigest.getInstance("SHA-256");
                bytes = dg.digest(data.getBytes(UTF_8));
            } catch (NoSuchAlgorithmException ex) {
                System.out.println(ex.getMessage());
            }
            StringBuffer buffer = new StringBuffer();
            for (byte b : bytes) {
                buffer.append(String.format("%02x", b));
            }
            if(buffer.toString().startsWith(prefix))
            {
                count += 1;
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        Wallet[] wallets = new Wallet[20];
        for(int i = 0; i < 20; ++i)
        {
            wallets[i] = new Wallet("walletID-"+(i+1));
        }
        Block head = new Block("",System.currentTimeMillis());
        blockchain.add(head);
        Scanner sc = new Scanner(System.in);
        String input = "";
        int money = 0;
        System.out.println("Welcome to Crypto Currency Simulation --Zhihao Hu 2467956H");
        while(true)
        {
            System.out.println("1.Mining");
            System.out.println("2.Add Block");
            System.out.println("3.Add Transaction");
            System.out.println("4.View Wallet");
            System.out.println("5.View Block Chain");
            System.out.println("6.Automatic Simulation");
            System.out.println("7.Exit");
            System.out.println("Input your choose(1-6): ");
            input = sc.nextLine().trim();
            if(input.compareTo("1") == 0)
            {
            	System.out.println("Input Wallet ID: (wallet-number)");
                input = sc.nextLine().trim();
                money = mineBlock("0");
                String[] ids = input.split("-");
                int id = Integer.parseInt(ids[1]);
                if(id >= 1 && id <= 20)
                {
                    wallets[id-1].income(money);
                    System.out.println(wallets[id-1].getWid()+" dig "+money+" Coin\n");
                }
                else
                {
                    System.out.println("This wallet does not exist.\n");
                }
            }
            else if(input.compareTo("2") == 0)
            {
                Block b = new Block(blockchain.get(blockchain.size()-1).getMyHash(),System.currentTimeMillis());
                blockchain.add(b);
                System.out.println("Add Block successful\n");
            }
            else if(input.compareTo("3") == 0)
            {
                if(blockchain.size() <= 1)
                {
                    System.out.println("There are no blocks to add transaction,please add block first\n");
                }
                else
                {
                    System.out.println("Input Transaction Coin: ");
                    input = sc.nextLine().trim();
                    System.out.println("Input Wallet 1 ID: ");
                    String input2 = sc.nextLine().trim();
                    System.out.println("Input Wallet 2 ID: ");
                    String input3 = sc.nextLine().trim();
                    Transaction t = new Transaction(wallets[Integer.parseInt(input2)],wallets[Integer.parseInt(input3)],Integer.parseInt(input));
                    blockchain.get(blockchain.size()-1).addTran(t);
                    System.out.println("Add Transcation successful.\n");
                }
            }
            else if(input.compareTo("4") == 0)
            {
                System.out.println("Wallets: ");
                for(int i = 0; i < 20; ++i)
                {
                    System.out.println(wallets[i].toString());
                }
            }
            else if(input.compareTo("5") == 0)
            {
                System.out.println("Block Chain:");
                if(blockchain.size() <= 1)
                {
                    System.out.println("Empty\n");
                }
                else
                {
                    for(int i = 1; i < blockchain.size(); ++i)
                    {
                        System.out.println(blockchain.get(i).toString());
                    }
                    System.out.println("");
                }
            }
            else if(input.compareTo("6") == 0)
            {
                for(int k = 0; k < 1000; ++k)
                {
                    int[] cpus = new int[20];
                    Random rand = new Random(System.currentTimeMillis());
                    int maxi = 0;
                    int index = -1;
                    int index2 = 0;
                    for(int i = 0; i < 20; ++i)
                    {
                        cpus[i] = rand.nextInt(90)+10;
                        if(cpus[i] > maxi)
                        {
                            maxi = cpus[i];
                            index = i;
                        }
                    }
                    wallets[index].income(10);
                    while(true)
                    {
                        index2 = rand.nextInt(20);
                        if(index != index2)
                        {
                            break;
                        }
                    }
                    int paycoin = rand.nextInt(wallets[index].getBalance());
                    Transaction t = new Transaction(wallets[index],wallets[index2],paycoin);
                    System.out.printf(t.toString()+"\n");
                    blockchain.get(blockchain.size()-1).addTran(t);
                    if(blockchain.get(blockchain.size()-1).getTrans().size() == 100)
                    {
                        Block b = new Block(blockchain.get(blockchain.size()-1).getMyHash(),System.currentTimeMillis());
                        blockchain.add(b);
                        System.out.printf(b.toString()+"\n");
                    }
                }
            }
            else if(input.compareTo("7") == 0)
            {
                System.out.println("Exit blockchain simulation\n");
                break;
            }
            else
            {
                System.out.println("Input Invalid,Try Again.\n");
            }
        }
    }
}
