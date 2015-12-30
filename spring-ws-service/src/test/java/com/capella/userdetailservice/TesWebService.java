package com.capella.userdetailservice;

/**
 * @author ramesh
 */

import com.spring_ws.person.schemas.PersonResponse;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.*;
import org.springframework.xml.transform.StringSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import java.io.IOException;
import java.io.InputStream;

@ContextConfiguration("classpath:com/capella/spring/ws/service/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TesWebService {
    @Autowired
    ApplicationContext context;
    private MockWebServiceClient mockClient;

    
    @Test
    public void test() throws IOException {
        InputStream ins = TesWebService.class.getClassLoader().getResourceAsStream("xml/request.xml");
        Source requestPayload = new StringSource(IOUtils.toString(ins));
        RequestCreator creator = RequestCreators.withPayload(requestPayload);
        mockClient = MockWebServiceClient.createClient(context);

        InputStream responseIns = TesWebService.class.getClassLoader().getResourceAsStream("xml/request.xml");
        Source responsePayload = new StringSource(IOUtils.toString(responseIns));
        ResponseActions responseActions = mockClient.sendRequest(creator);
        responseActions.andExpect(ResponseMatchers.payload(responsePayload));
    }

    @Test
    public  void marshallTest() throws JAXBException {
        PersonResponse response = new PersonResponse();
        response.setUsername("name");
        response.setPassword("password");

        JAXBContext jaxbContext = JAXBContext.newInstance(PersonResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(response, System.out);

    }
}
