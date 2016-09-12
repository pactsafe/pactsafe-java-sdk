package com.pactsafe.api.activity.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Michael Welling on 8/24/16.
 */
public class Payload implements Serializable {

    private static final Logger LOGGER = Logger.getLogger( Payload.class.getName() );

    private String v;
    private String _v;
    private String uid;
    private String sid;
    private String et;
    private String sig;
    private String rev;
    private String pat;
    private String pau;
    private String pad;
    private String pap;
    private String pae;
    private String ref;
    private String btz;
    private String bje;
    private String bfv;
    private String os;
    private String env;
    private String scd;
    private String res;
    private String dim;
    private String srid;
    private String xt;
    private String xtu;
    private String cus;
    private String bua;
    private String tm;
    private String dyn;
    private String _ct;
    private String gkey;
    private Integer gid;
    private String cnf;
    private String cid;
    private String vid;
    private String rdid;
    private String rnd;
    private String addr;
    private String _noerr;

    // Cannot be set by user
    private String _srv;    //server_side
    private String _lib;    //client_library
    private String _libv;   //client_version
    private long nc;        //nonce

    public Payload(ParameterStore parameters) {
        this.v = urlEncode(parameters.getApiVersion());
        this._v = urlEncode(parameters.getLibraryVersion());
        this.uid = urlEncode(parameters.getUuid());
        this.sid = urlEncode(parameters.getSiteId());
        this.et = parameters.getEventType() == null ? null : parameters.getEventType().getEventName();
        this.sig = urlEncode(parameters.getSignerId());
        this.rev = urlEncode(parameters.getRevisions());
        this.pat = urlEncode(parameters.getPageTitle());
        this.pau = urlEncode(parameters.getPageUrl());
        this.pad = urlEncode(parameters.getPageDomain());
        this.pap = urlEncode(parameters.getPagePath());
        this.pae = urlEncode(parameters.getPageEncoding());
        this.ref = urlEncode(parameters.getReferrer());
        this.btz = urlEncode(parameters.getBrowserTimezone());
        this.bje = urlEncode(parameters.getJavaEnabled());
        this.bfv = urlEncode(parameters.getFlashVersion());
        this.os = urlEncode(parameters.getOperatingSystem());
        this.env = urlEncode(parameters.getEnvironment());
        this.scd = urlEncode(parameters.getScreenColorDepth());
        this.res = urlEncode(parameters.getScreenResolution());
        this.dim = urlEncode(parameters.getScreenDimentions());
        this.srid = urlEncode(parameters.getRequest());
        this.xt = urlEncode(parameters.getExternal());
        this.xtu = urlEncode(parameters.getExternalUrl());
        this.cus = urlEncode(parameters.getCustomData());
        this.bua = urlEncode(parameters.getUserAgent());
        this.tm = urlEncode(parameters.getTestMode());
        this.dyn = urlEncode(parameters.getDynamic());
        this._ct = urlEncode(parameters.getCertification_token());
        this.gkey = urlEncode(parameters.getKey());
        this.gid = parameters.getGroup();
        this.cnf = urlEncode(parameters.getConfirmationEmail());
        this.cid = urlEncode(parameters.getContracts());
        this.vid = urlEncode(parameters.getVersions());
        this.rdid = urlEncode(parameters.getRenderId());
        this.rnd = urlEncode(parameters.getRenderData());
        this.addr = urlEncode(parameters.getRemoteAddress());
        this._noerr = urlEncode(parameters.getSilentError());
    }

    public String toString() {

        try {
            Properties prop = new Properties();
            prop.load(Payload.class.getClassLoader().getResourceAsStream("main.properties"));
            _lib = urlEncode(prop.getProperty("pactsafe.current.library"));
            _libv = urlEncode(prop.getProperty("pactsafe.current.version"));
            _srv = urlEncode(true);
            nc = Math.round(2147483647 * Math.random());
        }catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could not read client information from main.properties.");
        }

        String jsonString = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            jsonString = mapper.writeValueAsString(this);
            jsonString = StringUtils.remove(jsonString, "\"");
            jsonString = StringUtils.remove(jsonString, "}");
            jsonString = StringUtils.remove(jsonString, "{");
            jsonString = StringUtils.replace(jsonString, "+", "%20");
            jsonString = StringUtils.replace(jsonString, ":", "=");
            jsonString = StringUtils.replace(jsonString, ",", "&");
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.WARNING, "Could not serialize Activity parameters to payload.");
        }

        return jsonString;
    }

    private String urlEncode(String string) {
        if (string != null) {
            try {
                return URLEncoder.encode(string, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.log(Level.WARNING, "Could not URL Encode string: " + string);
            }
        }
        return null;
    }

    private String urlEncode(List list) {
        if (list != null) {
            try {
                String output = StringUtils.substring(list.toString(),1,list.toString().length()-1);
                output = StringUtils.replace(output, " ", "");
                return URLEncoder.encode(output, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.log(Level.WARNING, "Could not URL Encode list: " + list.toString());
            }
        }
        return null;
    }

    private String urlEncode(Map map) {
        if (map != null) {
            try {
                String output = new ObjectMapper().writeValueAsString(map);
                return URLEncoder.encode(output, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.log(Level.WARNING, "Could not URL Encode map: " + map.toString());
            } catch (JsonProcessingException e) {
                LOGGER.log(Level.WARNING, "Could not process valid JSON for map: " + map.toString());
            }
        }
        return null;
    }

    private String urlEncode(Boolean bool) {
        if (bool == null) return null;
        return bool ? "1" : "0";
    }

    public String getV() {
        return v;
    }

    public String get_v() {
        return _v;
    }

    public String getUid() {
        return uid;
    }

    public String getSid() {
        return sid;
    }

    public String getEt() {
        return et;
    }

    public String getSig() {
        return sig;
    }

    public String getRev() {
        return rev;
    }

    public String getPat() {
        return pat;
    }

    public String getPau() {
        return pau;
    }

    public String getPad() {
        return pad;
    }

    public String getPap() {
        return pap;
    }

    public String getPae() {
        return pae;
    }

    public String getRef() {
        return ref;
    }

    public String getBtz() {
        return btz;
    }

    public String getBje() {
        return bje;
    }

    public String getBfv() {
        return bfv;
    }

    public String getOs() {
        return os;
    }

    public String getEnv() {
        return env;
    }

    public String getScd() {
        return scd;
    }

    public String getRes() {
        return res;
    }

    public String getDim() {
        return dim;
    }

    public String getSrid() {
        return srid;
    }

    public String getXt() {
        return xt;
    }

    public String getXtu() {
        return xtu;
    }

    public String getCus() {
        return cus;
    }

    public String getBua() {
        return bua;
    }

    public String getTm() {
        return tm;
    }

    public String getDyn() {
        return dyn;
    }

    public String get_ct() {
        return _ct;
    }

    public String getGkey() {
        return gkey;
    }

    public Integer getGid() {
        return gid;
    }

    public String getCnf() {
        return cnf;
    }

    public String getCid() {
        return cid;
    }

    public String getVid() {
        return vid;
    }

    public String getRdid() {
        return rdid;
    }

    public String getRnd() {
        return rnd;
    }

    public String getAddr() {
        return addr;
    }

    public String get_noerr() {
        return _noerr;
    }

    public String get_srv() {
        return _srv;
    }

    public String get_lib() {
        return _lib;
    }

    public String get_libv() {
        return _libv;
    }

    public long getNc() {
        return nc;
    }
}
