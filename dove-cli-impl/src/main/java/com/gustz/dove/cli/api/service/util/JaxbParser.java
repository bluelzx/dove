package com.gustz.dove.cli.api.service.util;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * TODO: JAXB XML parser
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 11, 2015 ]
 */
public abstract class JaxbParser {

    private static final String ENCODING = "utf-8";

    private static ConcurrentMap<Class<?>, JAXBContext> jaxbContextMap = new ConcurrentHashMap<Class<?>, JAXBContext>();

    /**
     * Bean --> XML
     * 
     * @param root
     * @return
     * @throws JAXBException
     */
    public static String toXml(Object root) throws JAXBException {
        return toXml(root, ENCODING);
    }

    /**
     * Bean --> XML
     * 
     * @param root
     * @param encoding
     * @return
     * @throws JAXBException
     */
    public static String toXml(Object root, String encoding) throws JAXBException {
        return toXml(root, encoding, root.getClass());
    }

    /**
     * Bean --> XML
     * 
     * @param root
     * @param encoding
     * @param retType
     * @return
     * @throws JAXBException
     */
    public static String toXml(Object root, String encoding, Class<?> retType) throws JAXBException {
        StringWriter writer = new StringWriter();
        createMarshaller(retType, encoding).marshal(root, writer);
        return writer.toString();
    }

    /**
     * Bean --> XML
     * 
     * @param root
     * @param rootName
     * @param retType
     * @return
     * @throws JAXBException
     */
    public static String toXml(Collection<?> root, String rootName, Class<?> retType) throws JAXBException {
        return toXml(root, rootName, ENCODING, retType);
    }

    /**
     * Bean --> XML
     * 
     * @param root
     * @param rootName
     * @param encoding
     * @param retType
     * @return
     * @throws JAXBException
     */
    public static String toXml(Collection<?> root, String rootName, String encoding, Class<?> retType) throws JAXBException {
        CollectionWrapper wrapper = new CollectionWrapper();
        wrapper.collection = root;
        JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(new QName(rootName),
                CollectionWrapper.class, wrapper);
        //
        StringWriter writer = new StringWriter();
        createMarshaller(retType, encoding).marshal(wrapperElement, new StringWriter());
        return writer.toString();
    }

    /**
     * XML --> Bean
     * 
     * @param xml
     * @param retType
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXml(String xml, Class<T> retType) throws JAXBException {
        return (T) createUnmarshaller(retType).unmarshal(new StringReader(xml));
    }

    /**
     * XML --> Bean
     * 
     * @param xml
     * @param retType
     * @return
     * @throws JAXBException
     * @throws ParserConfigurationException 
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXmlByMap(String xml, Class<T> retType) throws JAXBException, ParserConfigurationException {
        return (T) createUnmarshaller(retType, new InnerMapAdapter()).unmarshal(new StringReader(xml));
    }

    /**
     * XML --> Bean
     * 
     * @param xml
     * @param retType
     * @param adapter
     * @return 
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXml(String xml, Class<T> retType, XmlAdapter<?, ?> adapter) throws JAXBException {
        return (T) createUnmarshaller(retType, adapter).unmarshal(new StringReader(xml));
    }

    /**
     * XML --> Bean
     * 
     * @param ins
     * @param retType
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXml(InputStream ins, Class<T> retType) throws JAXBException {
        return (T) createUnmarshaller(retType).unmarshal(ins);
    }

    /**
     * XML --> Bean
     * 
     * @param ins
     * @param retType
     * @param adapter
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXml(InputStream ins, Class<T> retType, XmlAdapter<?, ?> adapter) throws JAXBException {
        return (T) createUnmarshaller(retType, adapter).unmarshal(ins);
    }

    /**
     * XML --> Bean
     * 
     * @param ins
     * @param retType
     * @return
     * @throws JAXBException
     * @throws ParserConfigurationException 
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXmlByMap(InputStream ins, Class<T> retType) throws JAXBException, ParserConfigurationException {
        return (T) createUnmarshaller(retType, new InnerMapAdapter()).unmarshal(ins);
    }

    /**
     * XML --> Bean
     * 
     * @param reader
     * @param retType
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXml(Reader reader, Class<T> retType) throws JAXBException {
        return (T) createUnmarshaller(retType).unmarshal(reader);
    }

    /**
     * create JAXB marshaller
     * 
     * @param retType
     * @param encoding
     * @param decl
     * @param isFormat
     * @param adapter
     * @return
     * @throws JAXBException
     */
    public static Marshaller createMarshaller(Class<?> retType, String encoding, String decl, boolean isFormat,
            XmlAdapter<?, ?> adapter) throws JAXBException {
        Marshaller marshaller = getJaxbContext(retType).createMarshaller();
        if (isFormat) {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        }
        if (encoding != null && !encoding.isEmpty()) {
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        }
        if (decl != null && !decl.isEmpty()) {
            marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
        }
        marshaller.setAdapter(adapter);
        return marshaller;
    }

    private static Marshaller createMarshaller(Class<?> retType, String encoding) throws JAXBException {
        Marshaller marshaller = getJaxbContext(retType).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        if (encoding != null && !encoding.isEmpty()) {
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        }
        return marshaller;
    }

    private static Unmarshaller createUnmarshaller(Class<?> retType) throws JAXBException {
        return getJaxbContext(retType).createUnmarshaller();
    }

    private static Unmarshaller createUnmarshaller(Class<?> retType, XmlAdapter<?, ?> adapter) throws JAXBException {
        Unmarshaller unm = createUnmarshaller(retType);
        unm.setAdapter(adapter);
        return unm;
    }

    private static JAXBContext getJaxbContext(Class<?> retType) throws JAXBException {
        JAXBContext cxt = null;
        if (jaxbContextMap.containsKey(retType)) {
            cxt = jaxbContextMap.get(retType);
        }
        if (cxt == null) {
            cxt = JAXBContext.newInstance(retType, CollectionWrapper.class);
            jaxbContextMap.putIfAbsent(retType, cxt);
        }
        return cxt;
    }

    private static class CollectionWrapper {

        @XmlAnyElement
        protected Collection<?> collection;
    }

}

class InnerMapAdapter extends XmlAdapter<InnerMapAdapter.InnerAdaptedMap, Map<String, String>> {

    private DocumentBuilder docBuilder;

    public InnerMapAdapter() throws ParserConfigurationException {
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    static class InnerAdaptedMap {
        @XmlAnyElement
        public List<Element> elements = new ArrayList<Element>();
    }

    @Override
    public InnerAdaptedMap marshal(Map<String, String> map) {
        Document doc = docBuilder.newDocument();
        InnerAdaptedMap adaptedMap = new InnerAdaptedMap();
        for (Entry<String, String> _entry : map.entrySet()) {
            Element el = doc.createElement(_entry.getKey());
            el.setTextContent(_entry.getValue());
            adaptedMap.elements.add(el);
        }
        return adaptedMap;
    }

    @Override
    public Map<String, String> unmarshal(InnerAdaptedMap adaptedMap) {
        Map<String, String> _map = new HashMap<String, String>();
        for (Element element : adaptedMap.elements) {
            _map.put(element.getLocalName(), element.getTextContent());
        }
        return _map;
    }

}
