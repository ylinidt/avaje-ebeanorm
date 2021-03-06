package com.avaje.tests.query.other;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.avaje.ebean.BaseTestCase;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.tests.model.map.MpRole;
import com.avaje.tests.model.map.MpUser;

public class TestOneToManyAsMap extends BaseTestCase {

  @Test
  public void test() {

    EbeanServer eServer = Ebean.getServer(null);

    MpUser u = new MpUser();
    eServer.save(u);

    MpUser u2 = eServer.find(MpUser.class, u.getId());
    Assert.assertNotNull(u2);

    u2.setName("Charlie Brown");
    MpRole ourl = new MpRole();
    ourl.setOrganizationId(47L);
    u2.getRoles().put(ourl.getOrganizationId(), ourl);
    eServer.save(u2);

    MpUser u3 = eServer.find(MpUser.class, u.getId());
    Assert.assertEquals("Charlie Brown", u3.getName());

    Map<Long, MpRole> listMap = u3.getRoles();
    Assert.assertEquals(1, listMap.size());

  }

}
