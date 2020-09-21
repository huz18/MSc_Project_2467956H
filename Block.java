import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Block
{
    private String preHash;
    private String myHash;
    private long timestap;
    private List<Transaction> trans;

    public Block(String phash,long t)
    {
        this.preHash = phash;
        this.timestap = t;
        this.myHash = calculateBlockHash();
        this.trans = new ArrayList<>();
    }

    public String getPreHash()
    {
        return this.preHash;
    }

    public String getMyHash()
    {
        return this.myHash;
    }

    public List<Transaction> getTrans()
    {
        return this.trans;
    }

    public long getTimestap()
    {
        return this.timestap;
    }

    public void addTran(Transaction t)
    {
        this.trans.add(t);
    }

    public String toString()
    {
        String s = "";
        s += ("Block Hash: "+this.myHash+"\n");
        s += ("Pre Block Hash: "+this.preHash+"\n");
        s += ("Time Stamp: "+Long.toString(this.timestap)+"\n");
        /*s += "Transactions: \n";
        for(int i = 0; i < this.trans.size(); ++i)
        {
            s += (this.trans.get(i).toString()+"\n");
        }*/
        return s;
    }

    public String calculateBlockHash() {
        Random r = new Random(System.currentTimeMillis());
        String data = this.preHash + Long.toString(this.timestap) + Integer.toString(r.nextInt());
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
        return buffer.toString();
    }
}
