/**
 Copyright (C) 2013-2014  Barcelona Supercomputing Center

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package es.bsc.vmm.ascetic.cloudmiddleware.openstack;

import es.bsc.vmm.core.cloudmiddleware.openstack.OpenStackCredentials;
import es.bsc.vmm.core.cloudmiddleware.openstack.OpenStackGlance;

import es.bsc.vmm.core.cloudmiddleware.openstack.OpenStackJclouds;
import es.bsc.vmm.core.configuration.VmmConfig;
import es.bsc.vmm.core.models.images.ImageToUpload;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class OpenStackGlanceTest {

    private static OpenStackGlance glance;

    @BeforeClass
    public static void setUpClass() {
        VmmConfig conf = VmmConfig.INSTANCE;
        glance = new OpenStackGlance(new OpenStackCredentials(
				conf.getConfiguration().getString(OpenStackJclouds.OS_CONFIG_IP),
				conf.getConfiguration().getInt(OpenStackJclouds.OS_CONFIG_KEYSTONE_PORT),
				conf.getConfiguration().getString(OpenStackJclouds.OS_CONFIG_KEYSTONE_TENANT),
				conf.getConfiguration().getString(OpenStackJclouds.OS_CONFIG_KEYSTONE_USER),
				conf.getConfiguration().getString(OpenStackJclouds.OS_CONFIG_KEYSTONE_PASSWORD),
				conf.getConfiguration().getInt(OpenStackJclouds.OS_CONFIG_GLANCE_PORT),
				conf.getConfiguration().getString(OpenStackJclouds.OS_CONFIG_KEYSTONE_TENANT_ID)));
	}

    //This test only checks that the create and delete operations do not raise exceptions.
    @Test
    public void canCreateAndDelete() throws Exception {
        ImageToUpload imageToUpload = new ImageToUpload("testImage",
                VmmConfig.INSTANCE.getConfiguration().getString("testingImageUrl"));
        String imageId = glance.createImageFromUrl(imageToUpload);
        glance.deleteImage(imageId);
    }

}
