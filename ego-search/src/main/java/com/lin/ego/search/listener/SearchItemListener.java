package com.lin.ego.search.listener;

import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.vo.SearchItem;
import com.lin.ego.search.service.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;

@Component
public class SearchItemListener implements MessageListener {
    @Autowired
    private SearchService searchService;



    @Override
    public void onMessage(Message message) {
        try {

            MapMessage mapMessage = (MapMessage) message;
            String key = mapMessage.getString("key");

            if ("add".equals(key)) {
                String value = mapMessage.getString("value");
                SearchItem searchItem = JsonUtils.jsonToPojo(value, SearchItem.class);

                SolrInputDocument doc = new SolrInputDocument();

                doc.addField("id",searchItem.getId());
                doc.addField("item_title",searchItem.getTitle());
                doc.addField("item_category_name",searchItem.getCategoryName());
                doc.addField("item_price",searchItem.getPrice());
                doc.addField("item_sell_point",searchItem.getSellPoint());
                doc.addField("item_image",searchItem.getImage());

                searchService.addDocument(doc);

            }


        } catch (JMSException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
