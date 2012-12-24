package enjoyCafe.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import enjoyCafe.meta.CafeReviewMeta;
import enjoyCafe.meta.cafeMasterMeta;
import enjoyCafe.model.CafeReview;
import enjoyCafe.util.EscapeUtil;


public class HandleReviewService {

    private CafeReviewMeta crm = new CafeReviewMeta();
    /**
     * レビューデータをポストする。
     * @param input
     */
    public void postData(Map<String, Object> input) {
        // TODO Auto-generated method stub
        //データをbeanにコピーして投稿する
        CafeReview cr = new CafeReview();
        BeanUtil.copy(input, cr);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(cr);
        tx.commit();
        return;
    }

    /**
     * レビューのリストを取得
     * @param input 
     * @return
     */
    public List<HashMap> feedReviewList(Map<String, Object> input) {
        // TODO Auto-generated method stub
        List<Entity> listReview = null;
        List<HashMap> list = new ArrayList<HashMap>();
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(crm.getKind());
        // filter句を作成
        query.addFilter("cafeId", Query.FilterOperator.EQUAL, input.get("cafeId"));
        query.addSort("cafeId");
        query.addSort("updateDate");
        
        //データを取得
        listReview = ds.prepare(query).asList(FetchOptions.Builder.withOffset(0).limit(100));
        
        //返却用配列に放り込む
        for(Entity cm : listReview){
            HashMap<String,String> reviewData = new HashMap<String,String>();
            reviewData.put("key", EscapeUtil.toHtmlString(String.valueOf(cm.getKey().getId())));
            reviewData.put("cafeId", EscapeUtil.toHtmlStringForObject(cm.getProperty("cafeId")));
            reviewData.put("nickName", EscapeUtil.toHtmlStringForObject(cm.getProperty("nickName")));
            reviewData.put("reviewContent", EscapeUtil.toHtmlStringForObject(cm.getProperty("reviewContent")));
            reviewData.put("updateDate", EscapeUtil.toHtmlStringForObject(cm.getProperty("updateDate")));
            list.add(reviewData);
        }
        return list;
    }

}
