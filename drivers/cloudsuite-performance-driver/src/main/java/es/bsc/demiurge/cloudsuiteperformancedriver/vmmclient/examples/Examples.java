/**
 * Copyright (C) 2013-2014  Barcelona Supercomputing Center
 * <p/>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package es.bsc.demiurge.cloudsuiteperformancedriver.vmmclient.examples;

import es.bsc.demiurge.cloudsuiteperformancedriver.vmmclient.vmm.VmManagerClient;

public class Examples {

    public static void main(String[] args) {
        VmManagerClient vmm = new VmManagerClient("http://10.4.0.15:34372/vmmanager");
        System.out.println(vmm.getVms());
        System.out.println(vmm.getImages());
    }

}
