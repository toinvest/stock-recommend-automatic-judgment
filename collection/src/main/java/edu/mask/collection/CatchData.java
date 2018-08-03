package edu.mask.collection;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Date;

public class CatchData {

    private static final String url = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600071.phtml";

    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect(url).get();
        Element element = document.getElementById("FundHoldSharesTable");
        System.out.println(element.child(1).child(1).child(0));

        String sysPath = System.getProperty("user.dir");
        Repository rep = new FileRepository("E:\\learing_code\\stock-recommend-automatic-judgment\\.git");
        System.out.println(sysPath + "/.git");
        System.out.println(rep.getRemoteNames());
        Git git = new Git(rep);
        PersonIdent defaultCommitter = new PersonIdent(git.getRepository());
        PersonIdent committer = new PersonIdent(defaultCommitter, new Date());
        git.commit().setMessage("auto commit1").setCommitter(committer).call();
    }

}
