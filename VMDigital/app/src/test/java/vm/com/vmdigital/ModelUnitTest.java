package vm.com.vmdigital;

import org.junit.Test;
import java.util.Date;
import vm.com.vmdigital.models.Article;
import static org.junit.Assert.assertTrue;

public class ModelUnitTest {

    @Test
    public void dateCorrectFormat() throws Exception {
        Article article = new Article();
        article.setPublishedAt(new Date());
        String dateStr = article.getPublishedAtString();
        assertTrue(dateStr.startsWith("@"));
    }

}
