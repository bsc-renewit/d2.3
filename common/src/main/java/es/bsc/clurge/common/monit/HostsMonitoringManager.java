package es.bsc.clurge.common.monit;

/**
 * Created by mmacias on 4/11/15.
 */
public interface HostsMonitoringManager {
	void generateHosts(String[] hostNames);
	Host getHost(String hostname);
}
