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

package es.bsc.vmm.ascetic.modellers.price.ascetic;

import es.bsc.vmm.ascetic.modellers.price.PricingModeller;
import es.bsc.vmm.ascetic.modellers.energy.ascetic.AsceticEnergyModellerAdapter;


import java.util.List;

/**
 * Connector for the pricing modeller developed in the Ascetic project by AUEB.
 *
 * @author Mario Macias (github.com/mariomac), David Ortiz Lopez (david.ortiz@bsc.es)
 */
public class AsceticPricingModellerAdapter implements PricingModeller {

    /*
    Right now, we do not know at the IaaS level for how long a VM is going to be running.
    Anyway, we just need to make fair comparisons. Therefore, selecting the same time for all the VMs is good
    enough for now. It is important to take into account that this ignores the fact that VMs that are given a host
    with more available resources will take less time to complete its execution.
     */

    private static final long FIXED_DURATION_SEC = 3600;
    private static IaaSPricingModeller pricingModeller;

    public AsceticPricingModellerAdapter(AsceticEnergyModellerAdapter energyModeller) {
        pricingModeller = new IaaSPricingModeller(energyModeller.getEnergyModeller());
    }

	/*
    public double getVmCost(int cpus, int ramMb, int diskGb, String hostname) {

    }*/


    // For now, return always 1. This will be changed once we get this information from the PaaS level.
    private int getSchemeIdForVm() {
        return 1;
    }

	@Override
	public double getVMChargesPrediction(int cpus, int ramMb, double diskGb, String hostname) {
		return pricingModeller.getVMChargesPrediction(
				cpus, ramMb, diskGb, getSchemeIdForVm(), FIXED_DURATION_SEC, hostname);
	}

	@Override
	public IaaSPricingModeller getIaaSprovider(int id) {
		return pricingModeller.getIaaSprovider(id);
	}

	@Override
	public EnergyProvider getEnergyProvider() {
		return pricingModeller.getEnergyProvider();
	}

	@Override
	public IaaSPricingModellerPricingScheme initializeScheme(int schemeId) {
		return pricingModeller.initializeScheme(schemeId);
	}

	@Override
	public double getVMPricePerHourPrediction(int CPU, int RAM, double storage, int schemeId, long duration, String hostname) {
		return pricingModeller.getVMPricePerHourPrediction(CPU, RAM, storage, schemeId, duration, hostname);
	}

	@Override
	public double getVMFinalCharges(String VMid, boolean deleteVM) {
		return pricingModeller.getVMFinalCharges(VMid, deleteVM);
	}

	@Override
	public EnergyPrediction getEnergyPredicted(int CPU, int RAM, double storage, long duration, String hostname) {
		return pricingModeller.getEnergyPredicted(CPU, RAM, storage, duration, hostname);
	}

	@Override
	public EnergyPrediction getEnergyPredicted(int CPU, int RAM, double storage, String hostname) {
		return pricingModeller.getEnergyPredicted(CPU, RAM, storage, hostname);
	}

	@Override
	public void initializeVM(String VMid, String hostname, String appId ) {
		pricingModeller.initializeVM(VMid, getSchemeIdForVm(), hostname, appId);
	}

	@Override
	public IaaSPricingModellerBilling getBilling() {
		return pricingModeller.getBilling();
	}

	@Override
	public String getName() {
		return "priceEstimate";
	}

	@Override
	public double getValue(VmAssignmentToHost vma, List<VmDeployed> vmsDeployed, DeploymentPlan deploymentPlan) {
		return getVMChargesPrediction(vma.getVm().getCpus(), vma.getVm().getRamMb(), vma.getVm().getDiskGb(), vma.getHost().getHostname());
	}

	@Override
	void onVmDeployment(VmDeployed vm) {
		Thread thread = new Thread() {
			public void run(){
				//
				try {
					log.debug("Waiting 10 seconds before initializing VM billing. VM ID = " + vmId + "; Hostname = " + hostname);
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pricingModeller.initializeVM(vmId,  hostname, appId);
			}
		};
		thread.start();

	}
	@Override
	void onVmDestruction(VmDeployed vm) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// indicating vm has been stopped
					pricingModeller.getVMFinalCharges(vmId,true);
				} catch (Exception e) {
					log.warn("Error closing pricing Modeler for VM " + vmId + ": " + e.getMessage());
				}
			}
		}).start();
	}
	@Override
	void onVmMigration(VmDeployed vm) {}
	@Override
	void onVmAction(VmDeployed vm, VmAction action) {}

	@Override
	void onPreVmDeployment(Vm vm) {}
}
