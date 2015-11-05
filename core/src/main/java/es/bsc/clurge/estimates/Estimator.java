package es.bsc.clurge.estimates;

import es.bsc.clurge.models.scheduling.DeploymentPlan;
import es.bsc.clurge.models.vms.VmDeployed;

import java.util.List;

/**
 * Created by mmacias on 5/11/15.
 */
public interface Estimator<ReturnType> {
	ReturnType getDeploymentPlanEstimation(List<VmDeployed> vmsDeployed, DeploymentPlan deploymentPlan);
	ReturnType getVmEstimation(VmDeployed vm);
}
