package com.lin.ego.manager.producer;


import com.lin.ego.base.pojo.JsonUtils;
import com.lin.ego.base.vo.SearchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class ItemProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(final SearchItem searchItem){
        jmsTemplate.send("ego-queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();

                mapMessage.setString("key","add");
                mapMessage.setString("value", JsonUtils.objectToJson(searchItem));

                return mapMessage;

            }

        });
    }

}
