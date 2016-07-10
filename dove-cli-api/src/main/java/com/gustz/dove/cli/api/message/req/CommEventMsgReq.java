package com.gustz.dove.cli.api.message.req;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.gustz.dove.cli.api.service.dict.EventTypeDict;

/**
 * 
 * TODO: 公用的事件消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ] 
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommEventMsgReq extends EventMsgReq<CommEventMsgReq> {

    private static final long serialVersionUID = 1L;

    public CommEventMsgReq() {
        super();
    }

    public CommEventMsgReq(EventTypeDict event) {
        super(event);
    }

    public static CommEventMsgReq toBean(EventTypeDict event, String xml) throws JAXBException {
        return new CommEventMsgReq(event).toBean(xml, CommEventMsgReq.class);
    }

}
